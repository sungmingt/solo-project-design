package codestates.soloprojectdesign.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestDto {

    //검색 조건에 사용

    //검색 대상 key(업종), value(건설업)
    private String key;
    private String value;
}
