package kozin.first.domain.repository;

import kozin.first.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByLoginId(String LogIdId);
}
