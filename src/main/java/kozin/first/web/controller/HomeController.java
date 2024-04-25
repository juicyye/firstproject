package kozin.first.web.controller;

import kozin.first.domain.entity.Member;
import kozin.first.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import org.aspectj.asm.IModelFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static kozin.first.web.SessionConst.LOGIN_MEMBER;

@RequiredArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@SessionAttribute(name = LOGIN_MEMBER, required = false) Member member, Model model) {
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}
