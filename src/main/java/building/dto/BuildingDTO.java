package building.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {
    private String nameBuilding;
    private String address;
    private int numberOfBasement;
    private String managerName;
    private String managerPhoneNumber;
    private double floorArea;
    private String rentArea;
    private double emptyArea;
    private double rentPrice;
    private String serviceFee;
    private double brokeageFee;
}
