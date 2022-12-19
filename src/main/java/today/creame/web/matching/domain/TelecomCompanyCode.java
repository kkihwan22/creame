package today.creame.web.matching.domain;

import static java.util.Optional.ofNullable;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public enum TelecomCompanyCode {

    TEL_O60("060"), TEL_070("070");

    @Getter
    private String label;

    TelecomCompanyCode(String label) {
        this.label = label;
    }

    private static final Map<String, TelecomCompanyCode> map
        = Stream.of(TelecomCompanyCode.values()).collect(Collectors.toMap(TelecomCompanyCode::getLabel, Function.identity()));

    public static TelecomCompanyCode of(String label) {
        if (label != null) {
            return ofNullable(map.get(label))
                .orElseThrow(IllegalArgumentException::new);
        }
        return null;
    }
}
