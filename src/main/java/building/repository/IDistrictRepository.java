package building.repository;

import building.entity.DistrictEntity;

public interface IDistrictRepository {
    DistrictEntity getDistrictById(long id);
}
