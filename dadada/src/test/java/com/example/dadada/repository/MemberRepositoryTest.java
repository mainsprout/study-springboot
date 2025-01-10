package com.example.dadada.repository;

import com.example.dadada.entity.Member;
import com.example.dadada.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertTest(){
        LocalDate birthdate = LocalDate.of(2003, 01, 01);

        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder()
                    .email("hong" + i + "@gmail.com")
                    .name("사용자"+i)
                    .birthDate(birthdate)
                    .pw(passwordEncoder.encode("1111"))
                    .fromSocial(false)
                    .build();

            member.addRoleSet(MemberRole.MEMBER);

            memberRepository.save(member);
        });
    }

}