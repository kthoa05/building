package building.converter;

import building.builder.BuildingSearchBuilder;
import building.utils.BuildingUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder convertToBuildingSearchBuilder(Map<Object, Object> request, List<String> typeCode){
        return new BuildingSearchBuilder.Builder()
                .setNameBuilding(BuildingUtils.TClass.convertToObject(request, "name", String.class))
                .setNumberOfBasement(BuildingUtils.TClass.convertToObject(request, "numberOfBasement", Integer.class))
                .setDistrictId(BuildingUtils.TClass.convertToObject(request, "districtId", Integer.class))
                .setManagerPhoneNumber(BuildingUtils.TClass.convertToObject(request, "managerPhoneNumber", String.class))
                .setManagerName(BuildingUtils.TClass.convertToObject(request, "managerName", String.class))
                .setLevel(BuildingUtils.TClass.convertToObject(request, "level", String.class))
                .setStreet(BuildingUtils.TClass.convertToObject(request, "street", String.class))
                .setWard(BuildingUtils.TClass.convertToObject(request, "ward", String.class))
                .setRentAreaFrom(BuildingUtils.TClass.convertToObject(request, "rentAreaFrom", Double.class))
                .setRentAreaTo(BuildingUtils.TClass.convertToObject(request, "rentAreaTo", Double.class))
                .setRentPriceFrom(BuildingUtils.TClass.convertToObject(request, "rentPriceFrom", Double.class))
                .setRentPriceTo(BuildingUtils.TClass.convertToObject(request, "rentPriceTo", Double.class))
                .setStaffId(BuildingUtils.TClass.convertToObject(request, "staffId", Integer.class))
                .setPosition(BuildingUtils.TClass.convertToObject(request, "position", String.class))
                .setTypeCode(typeCode)
                .build();
    }
}
