package building.service.impl;

import building.converter.BuildingDTOConverter;
import building.dto.BuildingDTO;
import building.entity.BuildingEntity;
import building.entity.DistrictEntity;
import building.entity.RentAreaEntity;
import building.repository.IRentAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import building.repository.IBuildingRepository;
import building.repository.IDistrictRepository;
import building.service.IBuildingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    IBuildingRepository buildingRepository;

    @Autowired
    BuildingDTOConverter buildingDTOConverter;

    @Override
    public List<BuildingDTO> getAllBuilding(Map<Object, Object> request, List<String> typeCode) {
        List<BuildingDTO> result = new ArrayList<>();
        List<BuildingEntity> response = buildingRepository.getAllBuilding(request, typeCode);
        response.forEach(res -> {
            result.add(buildingDTOConverter.convertToBuildingDTO(res));
        });
        return result;
    }
}