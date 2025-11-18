package building.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingEntity {
    //request
    private long id;
    private String nameBuilding;
    private int floorArea;
    private int districtId;
    private String ward;
    private String street;
    private int numberOfBasement;
    private String position;
    private String level;
    private double rentAreaTo;
    private double rentAreaFrom;
    private double rentPriceTo;
    private double rentPriceFrom;
    private String managerName;
    private String managerPhoneNumber;
    private int staffId;
    private int userId;
    private double rentPrice;
    private double brokeageFee;
    private double emptyArea;
    private String serviceFee;
}
