package codestates.soloprojectdesign.service;

import codestates.soloprojectdesign.domain.Member;
import codestates.soloprojectdesign.domain.dto.RequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    //전체 조회
    public Page<Member> findAll(int page, int size) {
        return new PageImpl<>(
                List.of(new Member()), PageRequest.of(page - 1, size, Sort.by("id").descending()), 10);
    }

    //조건 조회
    public Page<Member> findFiltered(RequestDto requestDto, int page, int size) {
        return new PageImpl<>(
                List.of(new Member()), PageRequest.of(page - 1, size, Sort.by("id").descending()), 10);
    }
}
