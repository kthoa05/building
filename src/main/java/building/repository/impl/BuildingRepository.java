package building.repository.impl;

import building.entity.BuildingEntity;
import org.springframework.stereotype.Repository;
import building.repository.IBuildingRepository;
import building.utils.BuildingUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepository implements IBuildingRepository {

    public StringBuilder joinTable(Map<Object, Object> request, List<String> typeCode) {
        StringBuilder join = new StringBuilder();
        //todo: rent area, staff id
        String rentAreaTo = (String) request.get("rentAreaTo");
        String rentAreaFrom = (String) request.get("rentAreaFrom");
        if (rentAreaFrom != null || rentAreaTo != null) {
            join.append(" INNER JOIN RENTAREA RA ON B.ID = RA.BUILDINGID");
        }

        String staffId = (String) request.get("staffId");
        if (staffId != null) {
            join.append(" INNER JOIN ASSIGNMENTBUILDING AB ON B.ID = AB.BUILDINGID ");
            join.append(" INNER JOIN USER U ON AB.STAFFID = U.ID ");
            join.append(" INNER JOIN ASSIGNMENTCUSTOMER AMC ON ACM.STAFFID = U.ID ");
        }

        //type
        if (typeCode != null && typeCode.size() > 0) {
            join.append(" INNER JOIN BUILDINGRENTTYPE BRT ON BRT.BUILDINGID = B.ID ");
            join.append(" INNER JOIN RENTTYPE RT ON BRT.RENTTYPEID = RT.ID ");
        }
        return join;
    }


    public StringBuilder queryNormal(Map<Object, Object> request) {
        StringBuilder sql = new StringBuilder();
        for (Map.Entry<Object, Object> res : request.entrySet()) {
            String key = (String) res.getKey();
            String val = (String) res.getValue();
            if (!(key.equalsIgnoreCase("districtId") || key.equalsIgnoreCase("typeCode") || key.startsWith("rentArea") || key.startsWith("rentPrice") || key.equalsIgnoreCase("staffId"))) {
                boolean isNumber = BuildingUtils.Numberutils.isNumber(key);
                if (isNumber) {
                    sql.append(" AND b." + key + " = " + val + " ");
                } else {
                    sql.append(" AND B." + key + " LIKE '%" + val + "%' ");
                }
            }
        }
        return sql;
    }

    public StringBuilder querySpecial(Map<Object, Object> request, List<String> typeCode) {
        StringBuilder sql = new StringBuilder();
        /**
         rent area
         */
        double rentAreaTo = BuildingUtils.ParseUtils.convertStringToNumber((String) request.get("rentAreaTo")).doubleValue();
        double rentAreaFrom = BuildingUtils.ParseUtils.convertStringToNumber((String) request.get("rentAreaFrom")).doubleValue();

        if (rentAreaFrom != 0) {
            sql.append(" AND RA.VALUE >= " + rentAreaFrom);
        }

        if (rentAreaTo != 0) {
            sql.append(" AND RA.VALUE <= " + rentAreaTo);
        }

        /**
         rent price
         */
        double rentPriceTo = BuildingUtils.ParseUtils.convertStringToNumber((String) request.get("rentPriceTo")).doubleValue();
        double rentPriceFrom = BuildingUtils.ParseUtils.convertStringToNumber((String) request.get("rentPriceFrom")).doubleValue();

        if (rentPriceFrom != 0) {
            sql.append(" AND RENTPRICE >= " + rentPriceFrom);
        }

        if (rentPriceTo != 0) {
            sql.append(" AND RENTPRICE <= " + rentPriceTo);
        }

        /**
         staff id
         */
        String staffId = (String) request.get("staffId");
        if (staffId != null) {
            sql.append(" AND ACM.STAFFID = " + staffId);
        }

        /**
         type code
         */
        if (typeCode != null && typeCode.size() > 0) {
            sql.append(" AND RT.CODE IN (");
            sql.append(typeCode.stream().map(code -> "'" + code + "'").collect(Collectors.joining(",")));
            sql.append(")");
        }
        return sql;
    }

    public List<BuildingEntity> getAllBuilding(Map<Object, Object> request, List<String> typeCode) {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM BUILDING B ");
        sql.append(joinTable(request, typeCode));
        sql.append(" WHERE 1 = 1 ");
        sql.append(queryNormal(request));
        sql.append(querySpecial(request, typeCode));
        System.out.println("sql: " + sql);

        try (Connection conn = BuildingUtils.ConnectDB.getConnect(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                /**
                 private String nameBuilding;
                 private String address;
                 private int numberOfBasement;
                 private String managerName;
                 private String sdt;
                 private double floorArea;
                 private double emptyArea;
                 private double rentPrice;
                 private double brokeageFee;
                 private double feeMG;
                 */
                BuildingEntity building = new BuildingEntity();
                building.setId(rs.getInt("ID"));
                building.setNameBuilding(rs.getString("b.NAME"));
                building.setWard(rs.getString("b.WARD"));
                building.setStreet(rs.getString("b.STREET"));
                building.setNumberOfBasement(rs.getInt("b.NUMBEROFBASEMENT"));
                building.setManagerName(rs.getString("b.MANAGERNAME"));
                building.setManagerPhoneNumber(rs.getString("b.MANAGERPHONENUMBER"));
                building.setFloorArea(rs.getInt("b.FLOORAREA"));
                building.setBrokeageFee(rs.getDouble("b.BROKERAGEFEE"));
                building.setRentPrice(rs.getDouble("b.RENTPRICE"));
                building.setDistrictId(rs.getInt("b.DISTRICTID"));
                building.setServiceFee(rs.getString("b.SERVICEFEE"));

                result.add(building);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
