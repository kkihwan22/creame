package today.creame.web.member.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest
class MemberJpaRepositoryTest {
    private final Logger log = LoggerFactory.getLogger(MemberJpaRepositoryTest.class);

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    void test_success() {
        long count = memberJpaRepository.count();

        Member registerMember = new Member(
                "user1@creame.co.kr",
                "1234",
                "first user",
                "01043210987",
                MemberStatus.ACTIVE,
                SignupType.EMAIL);

        memberJpaRepository.save(registerMember);

        registerMember.addRole(new MemberRole(null, MemberRoleCode.USER));
        memberJpaRepository.flush();

        assertEquals(count+1, memberJpaRepository.count());

        Member findMember = memberJpaRepository.findById(registerMember.getId())
                .orElse(null);

        assertNotNull(findMember);

        List<MemberRole> roles = findMember.getRoles();

        assertEquals(1, roles.size());
        assertEquals(MemberRoleCode.USER, roles.get(0).getCodeName());
    }

    @Test
    void test_email_duplication() {

        Member firstMember = new Member(
                "user1@creame.co.kr",
                "1234",
                "first user",
                "01043210987",
                MemberStatus.ACTIVE,
                SignupType.EMAIL);

        memberJpaRepository.save(firstMember);

        Member secondMember = new Member(
                "user1@creame.co.kr",
                "1234",
                "second user",
                "01043210987",
                MemberStatus.ACTIVE,
                SignupType.EMAIL);

        Exception exception =
            assertThrows(
                DataIntegrityViolationException.class,
                () -> memberJpaRepository.save(secondMember)
            );

        // memberJpaRepository.flush();
        log.debug("error message: {}", exception.getMessage());
        assertNotNull(exception);
    }

    @Test
    void test_update() {

        Member m = new Member(
            "user1000@creame.co.kr",
            "1234",
            "first user",
            "01041234444",
            MemberStatus.ACTIVE,
                SignupType.EMAIL);
        Member mSave = memberJpaRepository.save(m);
        assertEquals(m, mSave);
    }
}