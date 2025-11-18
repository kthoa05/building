package building.repository;

import building.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface IBuildingRepository {
    List<BuildingEntity> getAllBuilding(Map<Object, Object> request, List<String> typeCode);
}
