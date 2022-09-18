package today.creame.web.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTokenJpaRepository extends JpaRepository<MemberToken, Long> {

    List<MemberToken> findMemberTokenByRefreshToken(String refreshToken);
}
