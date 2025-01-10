package com.example.dadada.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{

    @Id
    private String email;

    private String pw;

    private String name;

    private LocalDate birthDate;

    private int age;

    private boolean fromSocial;

    @PrePersist
    @PreUpdate
    public void calculateAge() {
        if (birthDate != null) {
            this.age = Period.between(birthDate, LocalDate.now()).getYears();
        }
    }

    @ElementCollection
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addRoleSet(MemberRole memberRole){
        roleSet.add(memberRole);
    }

}
