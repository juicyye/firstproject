package kozin.first.domain.service;

import kozin.first.domain.entity.Member;
import kozin.first.domain.repository.MemberRepository;
import kozin.first.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId).stream()
                .filter(m -> m.getPassword().equals(password))
                .findAny().orElse(null);

    }
}
