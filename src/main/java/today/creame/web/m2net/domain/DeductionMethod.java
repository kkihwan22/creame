package today.creame.web.m2net.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import today.creame.web.matching.domain.PaidType;

public enum DeductionMethod {

    PRE("Y", "선불", PaidType.PRE),
    POST("N", "후불", PaidType.POST),
    ALL("P", "", null);

    @Getter
    private String code;
    @Getter
    private String label;
    @Getter
    private PaidType paidType;

    DeductionMethod(String code, String label, PaidType paidType) {
        this.code = code;
        this.label = label;
        this.paidType = paidType;
    }

    private static final Map<String, DeductionMethod> map =
        Arrays.stream(DeductionMethod.values()).collect(Collectors.toMap(DeductionMethod::getCode, Function.identity()));

    public static DeductionMethod get(String code) {
        return Optional.ofNullable(map.get(code)).orElseThrow(() -> new IllegalArgumentException("Not exist code : [" + code + "]"));
    }
}