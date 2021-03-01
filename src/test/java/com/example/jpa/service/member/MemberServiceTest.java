package com.example.jpa.service.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.domain.member.Member;
import com.example.jpa.repository.member.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        final Member member = new Member();
        member.setName("김준");

        //when
        final Long id = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(id));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예약() {
        //given
        final Member member1 = new Member();
        member1.setName("이준");

        final Member member2 = new Member();
        member2.setName("이준");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");
    }

}