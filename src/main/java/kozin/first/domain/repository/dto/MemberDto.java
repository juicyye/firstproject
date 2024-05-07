package kozin.first.domain.repository.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String name;

    public MemberDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
