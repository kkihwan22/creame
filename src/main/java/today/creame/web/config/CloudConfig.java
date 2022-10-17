package today.creame.web.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.PropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class CloudConfig {
    private final Logger log = LoggerFactory.getLogger(CloudConfig.class);

    @Bean
    public BasicAWSCredentials credentials() {
        CredentialsProperties properties = credentialsProperties();
        log.debug("access-key:{}, secret-key:{}", properties.accessKey, properties.secretKey);
        return new BasicAWSCredentials(properties.accessKey, properties.secretKey);
    }

    @Bean
    public AWSStaticCredentialsProvider awsStaticCredentialsProvider() {
        return new AWSStaticCredentialsProvider(credentials());
    }

    @Bean
    public CredentialsProperties credentialsProperties() {
        return new CredentialsProperties();
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(this.awsStaticCredentialsProvider())
            .build();
    }


    @ConfigurationProperties(prefix = "cloud.aws.credentials")
    @Setter @Getter
    @ToString
    @EqualsAndHashCode
    class CredentialsProperties {
        public String accessKey;
        public String secretKey;
    }
}
