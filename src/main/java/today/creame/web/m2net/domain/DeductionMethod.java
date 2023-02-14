package today.creame.web.m2net.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;

public enum DeductionMethod {

    PRE("Y", "선불"),
    POST("N", "후불"),
    ALL("P", "");

    @Getter
    private String code;
    @Getter
    private String label;

    DeductionMethod(String code, String label) {
        this.code = code;
        this.label = label;
    }

    private static final Map<String, DeductionMethod> map =
        Arrays.stream(DeductionMethod.values()).collect(Collectors.toMap(DeductionMethod::getCode, Function.identity()));

    public DeductionMethod get(String code) {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new IllegalArgumentException("Not exist code : [" + code + "]"));
    }
}