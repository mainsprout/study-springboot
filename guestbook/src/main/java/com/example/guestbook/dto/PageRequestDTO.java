package com.example.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    // 화면에서 전달되는 page라는 파라미터와 size라는 파라미터를 수집하는 역할을 한다.

    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        // JPA는 페이지 번호가 0부터 시작하므로 -1을 해준다.
        return PageRequest.of(page - 1, size, sort);
    }
}

