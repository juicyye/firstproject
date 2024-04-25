package kozin.first.web.controller;

import kozin.first.domain.entity.Member;
import kozin.first.domain.service.MemberService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String memberView(Model model) {
        model.addAttribute("member", new MemberForm());
        log.info("member join");
        return "members/createMemberForm";
    }

    @PostMapping("/join")
    public String createMember(@Validated @ModelAttribute("member") MemberForm form, BindingResult bindingResult, RedirectAttributes rttr) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getAllErrors().toString());
            return "members/createMemberForm";
        }
        Member member = new Member(form.getName(), form.getLoginId(), form.getPassword());
        memberService.save(member);
        rttr.addAttribute("status", true);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String memberList(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
