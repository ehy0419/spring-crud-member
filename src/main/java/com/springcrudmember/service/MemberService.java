package com.springcrudmember.service;

import com.springcrudmember.dto.MemberRequest;
import com.springcrudmember.dto.MemberResponse;
import com.springcrudmember.entity.Member;
import com.springcrudmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse save(MemberRequest memberRequest) {
        Member savedMember = memberRepository.save(new Member(memberRequest.getName()));
        return new MemberResponse(savedMember.getId(), savedMember.getName());
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = new ArrayList<>();

        for (Member member : members) {
            MemberResponse memberResponse = new MemberResponse(
                    member.getId(),
                    member.getName()
            );
            memberResponses.add(memberResponse);
        }
        return memberResponses;
    }

    @Transactional
    public MemberResponse update(Long memberId, MemberRequest memberRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("멤버 id: " + memberId + " 존재하지 않습니다.")
        );
        member.updateName(memberRequest.getName());
        return new MemberResponse(
                member.getId(),
                memberRequest.getName());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        boolean exists = memberRepository.existsById(memberId);
        if (!exists) {
            throw new IllegalArgumentException("해당하는 멤버 id는 존재하지 않습니다.");
        }
        memberRepository.deleteById(memberId);
    }

    @Transactional(readOnly = true)
    public MemberResponse findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("멤버 id: " + memberId + " 존재하지 않습니다.")
        );
        return new MemberResponse(
                member.getId(),
                member.getName());
    }
}
