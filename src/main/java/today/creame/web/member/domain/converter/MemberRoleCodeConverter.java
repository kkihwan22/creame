package today.creame.web.member.domain.converter;

import today.creame.web.member.domain.MemberRole;
import today.creame.web.member.domain.MemberRoleCode;
import today.creame.web.member.domain.MemberStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
