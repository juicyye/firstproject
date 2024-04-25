package kozin.first.domain.service;

import kozin.first.domain.entity.Member;
import kozin.first.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    @Transactional
    public Member save(Member member) {
        validateDuplicatePassword(member);
        return memberRepository.save(member);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    private void validateDuplicatePassword(Member member) {
        Optional<Member> any = memberRepository.findByLoginId(member.getLoginId())
                .stream().filter(m -> m.getPassword().equals(member.getPassword())).findAny();

        if(any.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }




}
