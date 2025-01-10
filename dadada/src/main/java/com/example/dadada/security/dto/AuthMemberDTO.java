package com.example.dadada.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {

    private String email;
    private String name;
    private boolean fromSocial;

    public AuthMemberDTO(
            String name,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities){
        super(name, password, authorities);
        this.email = email;
        this.fromSocial = fromSocial;
    }
}
