package codestates.soloprojectdesign.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyType {

    private String code;
    private String name;

    //    SEOUL("001", "SEOUL"),
//    GYEONGGI("002", "GYEONGGI"),
//    BUSAN("003", "BUSAN"),
//    INCHEON("004", "INCHEON");
//
//    private final String code;
//    private final String name;
//
//    CompanyType(String code, String name) {
//        this.code = code;
//        this.name = name;
//    }
}
