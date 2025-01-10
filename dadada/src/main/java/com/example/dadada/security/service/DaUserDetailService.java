package com.example.dadada.security.service;

import com.example.dadada.entity.Member;
import com.example.dadada.repository.MemberRepository;
import com.example.dadada.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DaUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

        Optional<Member> result = memberRepository.findByEmail(username, false);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check Email or Social");
        }

        Member member = result.get();
        AuthMemberDTO authMember = new AuthMemberDTO(
                member.getName(),
                member.getPw(),
                member.isFromSocial(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority
                                ("ROLE_" + role.name())).collect(Collectors.toSet()));

        authMember.setName(member.getName());
        authMember.setFromSocial(member.isFromSocial());

        return authMember;
    }
}
