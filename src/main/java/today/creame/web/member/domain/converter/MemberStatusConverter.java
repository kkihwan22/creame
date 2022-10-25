package today.creame.web.member.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.member.domain.MemberStatus;

@Converter
public class MemberStatusConverter implements AttributeConverter<MemberStatus, String> {

    @Override
    public String convertToDatabaseColumn(MemberStatus attribute) {
        return attribute.name();
    }

    @Override
    public MemberStatus convertToEntityAttribute(String dbData) {
        return MemberStatus.valueOf(dbData);
    }
}
