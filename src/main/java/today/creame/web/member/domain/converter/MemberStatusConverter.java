package today.creame.web.member.domain.converter;

import today.creame.web.member.domain.MemberStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
