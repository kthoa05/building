package building.repository;

import building.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBuildingRepositorySpringJPA extends JpaRepository<BuildingEntity, Long> {
}
