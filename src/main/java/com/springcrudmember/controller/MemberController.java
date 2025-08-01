package com.springcrudmember.controller;

import com.springcrudmember.dto.MemberRequest;
import com.springcrudmember.dto.MemberResponse;
import com.springcrudmember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponse createMember(
            @RequestBody MemberRequest memberRequest
    ) {
        return memberService.save(memberRequest);
    }

    @GetMapping("/members")
    public List<MemberResponse> getMembers() {
        return memberService.findMembers();
    }

    @GetMapping("/members/{memberId}")
    public MemberResponse getMember(
            @PathVariable Long memberId
    ) {
        return memberService.findMember(memberId);
    }

    @PutMapping("/members/{memberId}")
    public MemberResponse updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequest memberRequest
    ) {
        return memberService.update( memberId, memberRequest );
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }
}
