package today.creame.web.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberNotificationJpaRepository extends JpaRepository<MemberNotificationPreference, Long> {
}
