package today.creame.web.member.social.provider.apple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import today.creame.web.member.social.exception.ApplePrivateKeyParserFailException;
import today.creame.web.member.social.exception.NotFoundApplePrivateKeyException;
import today.creame.web.member.social.feign.AppleClient;
import today.creame.web.member.social.feign.io.TokenRequest;
import today.creame.web.member.social.feign.io.apple.AppleTokenInfoResponse;
import today.creame.web.member.social.feign.io.apple.AppleTokenResponse;
import today.creame.web.member.social.provider.SocialProviderService;
import today.creame.web.member.social.provider.io.ProviderProfileResult;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class AppleService implements SocialProviderService {
    @Value("${apple.client.team_id}")
    private String appleTeamId;

    @Value("${apple.client.id}")
    private String appleClientId;

    @Value("${apple.client.key}")
    private String appleKey;

    @Value("${apple.redirect_uri}")
    private String appleRedirectUri;

    @Value("${apple.base_url}")
    private String appleBaseUrl;

    @Value("${apple.key_path}")
    private String appleKeyPath;

    private final AppleClient appleClient;
//    private final AppleProfileClient appleProfileClient;

    @Override
    public String generateUrl() {
        StringBuilder sb = new StringBuilder(appleBaseUrl + "/auth/authorize");
        sb.append("?client_id=" + appleClientId);
        sb.append("&redirect_uri=" + appleRedirectUri);
        sb.append("&response_type=code");

        return sb.toString();
    }

    @Override
    public String getToken(String code, String state) {
        TokenRequest request = new TokenRequest(appleClientId, createClientSecret(), code, state, null);
        ResponseEntity<AppleTokenResponse> appleTokenResponseResponseEntity = appleClient.getToken(request);
        if(OK.equals(appleTokenResponseResponseEntity.getStatusCode()) && Objects.isNull(appleTokenResponseResponseEntity.getBody().getError())) {
            return appleTokenResponseResponseEntity.getBody().getId_token();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public ProviderProfileResult getInfo(String token) {
        AppleTokenInfoResponse appleTokenInfoResponse = decodeFromIdToken(token);
        return new ProviderProfileResult(appleTokenInfoResponse.getEmail(), appleTokenInfoResponse.getName());
    }

    private AppleTokenInfoResponse decodeFromIdToken(String idToken) {
        try {
            String[] check = idToken.split("\\.");
            Base64.Decoder decoder = Base64.getDecoder();
            String payload = new String(decoder.decode(check[1]));
            ObjectMapper mapper = new ObjectMapper();
            AppleTokenInfoResponse appleTokenInfoResponse = mapper.readValue(payload, AppleTokenInfoResponse.class);

            return appleTokenInfoResponse;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String createClientSecret() {
        final int expiration = 1000 * 60 * 5;

        return Jwts.builder()
                .setHeaderParam("kid", appleKey)
                .setHeaderParam("alg", "ES256")
                .setIssuer(appleTeamId)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발행 시간 - UNIX 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료 시간
                .setAudience(appleBaseUrl)
                .setSubject(appleClientId)
                .signWith(SignatureAlgorithm.ES256, getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        ClassPathResource classPathResource = new ClassPathResource(appleKeyPath);

        if(!classPathResource.exists()){
            throw new NotFoundApplePrivateKeyException();
        }

        try {
            final Reader pemReader = new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8);

            final PEMParser pemParser = new PEMParser(pemReader);
            final JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
            final PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();

            return converter.getPrivateKey(object);

        } catch (Exception e) {
            throw new ApplePrivateKeyParserFailException();
        }
    }
}
