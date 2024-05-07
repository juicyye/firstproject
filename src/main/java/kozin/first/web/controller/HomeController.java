package kozin.first.web.controller;

import kozin.first.domain.entity.Member;
import kozin.first.web.SessionConst;
import kozin.first.web.argumentresolver.Login;
import kozin.first.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.asm.IModelFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static kozin.first.web.SessionConst.LOGIN_MEMBER;

@RequiredArgsConstructor
@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home(@Login Member member, Model model) {
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }

   /* @GetMapping("/")
    public String home1(@SessionAttribute(value = LOGIN_MEMBER,required = false) MemberForm form, Model model) {
        if (form == null) {
            return "home";
        }
        model.addAttribute("member", form);
        return "loginHome";
    }*/
}
