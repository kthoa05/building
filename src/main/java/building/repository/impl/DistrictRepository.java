package building.repository.impl;

import building.entity.DistrictEntity;
import building.repository.IDistrictRepository;
import building.utils.BuildingUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DistrictRepository implements IDistrictRepository {

    public DistrictEntity getDistrictById(long id){
        DistrictEntity result = new DistrictEntity();
        String sql = "SELECT * FROM DISTRICT WHERE ID = ? ";
        try(Connection conn = BuildingUtils.ConnectDB.getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String name = rs.getString("NAME");
                String code = rs.getString("CODE");
                result.setDistrictId(id);
                result.setName(name);
                result.setCode(code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
