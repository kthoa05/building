package building.repository;

import building.entity.RentAreaEntity;

import java.util.List;

public interface IRentAreaRepository {
    List<RentAreaEntity> getValueById(Long id);
}
