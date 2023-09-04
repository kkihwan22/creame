package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;

@Getter
public class M2netRewardRequest {

    private int amt;

    public M2netRewardRequest(int amt) {
        this.amt = amt;
    }
}
