package codestates.soloprojectdesign.domain.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ResponseDto<T> {

    private List<T> data;
    private PageInfo pageInfo;

    public ResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber(),
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
