package kozin.first.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kozin.first.domain.entity.Member;
import kozin.first.domain.service.LoginService;
import kozin.first.web.form.LoginForm;
import kozin.first.web.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static kozin.first.web.SessionConst.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new LoginForm());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") @Validated LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("errors {}", bindingResult.getAllErrors());
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, loginMember);

        return "redirect:"+redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
