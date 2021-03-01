package com.example.jpa.service.member;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.jpa.domain.member.Member;
import com.example.jpa.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        final List<Member> findMembers = memberRepository.findByName(member.getName());
        checkState(CollectionUtils.isEmpty(findMembers), "이미 가입된 회원입니다.");
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Member getMember(Member member) {
        return memberRepository.findById(member.getId());
    }

}
