package today.creame.web.sms.infra.feign;

import lombok.Getter;
import lombok.ToString;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import today.creame.web.config.FeignConfig;

import java.util.Map;

@FeignClient(
        name = "CoolSmsClient",
        url = "${services.external.sms.host}",
        configuration = {FeignConfig.class}
)
public interface CoolSmsClient {

    @PostMapping("/messages/v4/send")
    ResponseEntity<SimpleMessageResponse> sendOne(
            @RequestHeader("Authorization") String value, @RequestBody Map<String, SimpleMessageRequest> request);

    @Getter @ToString
    class SimpleMessageRequest {
        private String to;
        private String from;
        private String text;

        public SimpleMessageRequest(String to, String from, String text) {
            this.to = to;
            this.from = from;
            this.text = text;
        }
    }

    @Getter @ToString
    class SimpleMessageResponse {
        private String groupId;
        private String to;
        private String from;
        private String type;
        private String statusMessage;
        private String country;
        private String messageId;
        private String statusCode;
        private String accountId;

        public SimpleMessageResponse(String groupId, String to, String from, String type, String statusMessage, String country, String messageId, String statusCode, String accountId) {
            this.groupId = groupId;
            this.to = to;
            this.from = from;
            this.type = type;
            this.statusMessage = statusMessage;
            this.country = country;
            this.messageId = messageId;
            this.statusCode = statusCode;
            this.accountId = accountId;
        }
    }
}