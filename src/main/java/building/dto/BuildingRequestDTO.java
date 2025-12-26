package building.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingRequestDTO {
    private String name;
    private String ward;
    private String street;
    private int districtId;
}
