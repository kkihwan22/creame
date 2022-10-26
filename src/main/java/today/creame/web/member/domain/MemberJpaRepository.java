package today.creame.web.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findFirstByOrderById();

    Optional<Member> findByEmail(String email);

    @Query("select m from Member m join fetch m.roles where m.email = :email")
    Optional<Member> findByUserDetails(String email);

    Long countMemberByEmail(String email);
    Long countMemberByNickname(String nickname);
    Long countMemberByPhoneNumber(String phoneNumber);
}
