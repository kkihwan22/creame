package today.creame.web.member.domain;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import today.creame.web.member.exception.NotFoundMemberException;

import java.util.*;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest
class MemberTokenJpaRepositoryTest {
    private final Logger log = LoggerFactory.getLogger(MemberJpaRepositoryTest.class);

    @Autowired
    private MemberTokenJpaRepository memberTokenJpaRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private String refreshToken = "aAdfw1i043533432";

    @Test
    void test_success() {
        Member findMember = memberJpaRepository
                .findFirstByOrderById()
                .orElseThrow(NotFoundMemberException::new);
        log.debug("findMember:{}", findMember);

        MemberToken memberToken = new MemberToken(null, findMember, refreshToken);
        memberTokenJpaRepository.save(memberToken);
        memberTokenJpaRepository.flush();


        Optional<MemberToken> memberTokenByRefreshToken = memberTokenJpaRepository.findMemberTokenByRefreshToken(refreshToken);

        assertNotNull(memberTokenByRefreshToken.get());
    }

}