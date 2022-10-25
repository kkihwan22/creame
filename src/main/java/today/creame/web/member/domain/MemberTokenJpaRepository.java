package today.creame.web.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTokenJpaRepository extends JpaRepository<MemberToken, Long> {

    Optional<MemberToken> findMemberTokenByRefreshToken(String refreshToken);
}
