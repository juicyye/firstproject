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

    public MemberForm(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    public MemberForm() {
    }
}
