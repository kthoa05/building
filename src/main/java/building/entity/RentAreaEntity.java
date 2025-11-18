package building.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentAreaEntity {
    private long id;
    private long buildingId;
    private int value;

}
