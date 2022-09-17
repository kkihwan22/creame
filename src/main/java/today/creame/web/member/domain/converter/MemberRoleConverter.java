package today.creame.web.member.domain.converter;

import today.creame.web.member.domain.MemberRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

@Converter
public class MemberRoleConverter implements AttributeConverter<MemberRole, String> {

    @Override
    public String convertToDatabaseColumn(MemberRole attribute) {
        return null;
    }

    @Override
    public MemberRole convertToEntityAttribute(String dbData) {
        return null;
    }
}
