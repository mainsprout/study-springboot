package com.example.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> { // 제네릭 타입을 사용하여 DTO와 EN(Entity) 타입을 유연하게 처리한다.
    // 엔티티 객체들을 DTO 객체로 변환해서 자료구조에 담아야 한다.

    // DTO 객체들을 저장할 리스트 필드. (엔티티 데이터가 DTO로 변환된 후, 이 리스트에 저장된다)
    private List<DTO> dtoList;

    private int totalPage;

    private int page;
    private int size;

    private int start, end;

    private boolean prev, next;

    private List<Integer> pageList;

    // 생성자(DTO 변환 작업 수행)
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream()
                .map(fn) // 엔티티 -> DTO 변환
                .collect(Collectors.toUnmodifiableList()); // 변경 불가능한 리스트로 저장

        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd : totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
