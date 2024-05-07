package kozin.first.api;

import jakarta.validation.Valid;
import kozin.first.domain.entity.Member;
import kozin.first.domain.repository.dto.MemberDto;
import kozin.first.domain.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api2")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/members")
    public Result members(){
        List<MemberDto> members = memberService.findAll().stream()
                .map(m -> new MemberDto(m.getId(), m.getName()))
                .collect(Collectors.toList());
        return new Result(members);
    }

    @PostMapping("/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid MemberDto memberDto){
        Member member = new Member();
        member.setName(memberDto.getName());
        Member saveMember = memberService.save(member);
        return new CreateMemberResponse(saveMember.getId());

    }

    @PutMapping("/members/{id}")
    public UpdateMemberResponse updateMember(@PathVariable Long id, @RequestBody @Valid MemberDto memberDto){
        memberService.update(id, memberDto.getName());
        Member findMember = memberService.findById(id);
        return new UpdateMemberResponse(id, findMember.getName());

    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }
}
