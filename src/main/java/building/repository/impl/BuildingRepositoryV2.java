package building.repository.impl;

import building.builder.BuildingSearchBuilder;
import building.entity.BuildingEntity;
import building.repository.IBuildingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Primary
public class BuildingRepositoryV2 implements IBuildingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> getAllBuilding(BuildingSearchBuilder request) {
        String sql = "FROM BuildingEntity";
        Query query = entityManager.createQuery(sql, BuildingEntity.class);
        return query.getResultList();
    }
}
