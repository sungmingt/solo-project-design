package codestates.soloprojectdesign.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Company {

    private long id;
    private String name;
    private CompanyType type;
    private CompanyLocation location;
}
