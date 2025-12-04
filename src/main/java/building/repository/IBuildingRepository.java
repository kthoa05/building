package building.repository;

import building.builder.BuildingSearchBuilder;
import building.entity.BuildingEntity;

import java.util.List;

public interface IBuildingRepository {
    List<BuildingEntity> getAllBuilding(BuildingSearchBuilder request);
}
