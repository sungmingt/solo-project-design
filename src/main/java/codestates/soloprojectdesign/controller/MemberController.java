package codestates.soloprojectdesign.controller;

import codestates.soloprojectdesign.domain.Member;
import codestates.soloprojectdesign.domain.dto.RequestDto;
import codestates.soloprojectdesign.domain.dto.ResponseDto;
import codestates.soloprojectdesign.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //전체 조회
    @GetMapping
    public ResponseEntity findAll(@RequestParam int page,
                                  @RequestParam int size) {
        Page<Member> memberPage = memberService.findAll(page, size);
        List<Member> content = memberPage.getContent();

        return new ResponseEntity(new ResponseDto(content, memberPage), OK);
    }

    //특정 회원 조회
    @PostMapping
    public ResponseEntity findFiltered(@RequestParam int page,
                                       @RequestParam int size,
                                       @RequestBody RequestDto requestDto) {
        Page<Member> memberPage = memberService.findFiltered(requestDto, page, size);
        List<Member> content = memberPage.getContent();

        return new ResponseEntity(new ResponseDto(content, memberPage), OK);
    }
}
