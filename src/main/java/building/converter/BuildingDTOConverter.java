package building.converter;

import building.config.ModelMapperConfig;
import building.dto.BuildingDTO;
import building.entity.BuildingEntity;
import building.entity.DistrictEntity;
import building.entity.RentAreaEntity;
import building.repository.IBuildingRepository;
import building.repository.IDistrictRepository;
import building.repository.IRentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BuildingDTOConverter {
    @Autowired
    IDistrictRepository districtRepository;

    @Autowired
    IRentAreaRepository rentAreaRepository;

    @Autowired
    ModelMapper modelMapper;

    public BuildingDTO convertToBuildingDTO(BuildingEntity buildingEntity){
//        DistrictEntity district = districtRepository.getDistrictById(buildingEntity.getDistrictId());
        DistrictEntity district = buildingEntity.getDistrict();
        String address = Stream.of(buildingEntity.getStreet(), buildingEntity.getWard(), district.getName())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(","));

        List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.getValueById(buildingEntity.getId());
        String rentAreaValue = rentAreaEntityList.stream()
                .map(val -> String.valueOf(val.getValue()))
                .collect(Collectors.joining(","));
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setAddress(address);
        buildingDTO.setRentArea(rentAreaValue);
        return buildingDTO;
    }
}
