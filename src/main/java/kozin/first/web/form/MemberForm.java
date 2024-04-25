package kozin.first.web.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
