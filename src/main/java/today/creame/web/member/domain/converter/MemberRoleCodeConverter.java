package today.creame.web.member.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import today.creame.web.member.domain.MemberRoleCode;

@Converter
public class MemberRoleCodeConverter implements AttributeConverter<MemberRoleCode, String> {

    @Override
    public String convertToDatabaseColumn(MemberRoleCode attribute) {
        return attribute.name();
    }

    @Override
    public MemberRoleCode convertToEntityAttribute(String dbData) {
        return MemberRoleCode.valueOf(dbData);
    }
}
