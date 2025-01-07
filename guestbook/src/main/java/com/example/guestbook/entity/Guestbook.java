package com.example.guestbook.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 이 클래스가 JPA의 엔티티임을 선언
@Getter
@Builder // 빌더 패턴을 사용할 수 있도록 지원(객체 생성 시 생성자의 매개변수를 명시적으로 지정할 수 있다)
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 자동으로 생성(JPA에서 프록시 객체 생성을 위해 기본 생성자가 필요)
@ToString // 객체의 필드 값을 문자열로 반환하는 toString() 메서드를 자동으로 생성
public class Guestbook extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
