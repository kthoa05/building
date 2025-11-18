package building.service;

import building.dto.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> getAllBuilding(Map<Object, Object> request, List<String> typeCode);
}
