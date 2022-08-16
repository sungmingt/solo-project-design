package codestates.soloprojectdesign.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {
    private Long id;
    private String name;
//    private String password;
    private String gender;
    private String company_name;
    private String company_type;
    private String company_location;

    public Member(Long id, String name, String gender, String company_name, String company_type, String company_location) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.company_name = company_name;
        this.company_type = company_type;
        this.company_location = company_location;
    }
}
