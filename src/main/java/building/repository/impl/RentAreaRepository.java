package building.repository.impl;

import building.entity.RentAreaEntity;
import building.repository.IRentAreaRepository;
import building.utils.BuildingUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentAreaRepository implements IRentAreaRepository {
    @Override
    public List<RentAreaEntity> getValueById(Long id) {
        List<RentAreaEntity> result = new ArrayList<>();
        String sql = "SELECT * FROM RENTAREA WHERE BUILDINGID =  ? ";
        try(Connection conn = BuildingUtils.ConnectDB.getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(rs.getInt("VALUE"));
                result.add(rentAreaEntity);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
        return result;
    }
}
