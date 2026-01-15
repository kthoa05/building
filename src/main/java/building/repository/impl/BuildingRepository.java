package building.repository.impl;

import building.builder.BuildingSearchBuilder;
import building.entity.BuildingEntity;
import org.springframework.stereotype.Repository;
import building.repository.IBuildingRepository;
import building.utils.BuildingUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepository implements IBuildingRepository {

    public StringBuilder joinTable(BuildingSearchBuilder request) {
        StringBuilder join = new StringBuilder();
        //todo: rent area, staff id
        Double rentAreaTo = request.getRentAreaTo();
        Double rentAreaFrom = request.getRentAreaFrom();
        if (rentAreaTo != null || rentAreaFrom != null) {
            join.append(" INNER JOIN RENTAREA RA ON B.ID = RA.BUILDINGID");
        }

        Integer staffId = request.getStaffId();
        if (staffId != null) {
            join.append(" INNER JOIN ASSIGNMENTBUILDING AB ON B.ID = AB.BUILDINGID ");
            join.append(" INNER JOIN USER U ON AB.STAFFID = U.ID ");
            join.append(" INNER JOIN ASSIGNMENTCUSTOMER AMC ON AMC.STAFFID = U.ID ");
        }

        List<String> typeCode = request.getTypeCode();
        if (typeCode != null && typeCode.size() > 0) {
            join.append(" INNER JOIN BUILDINGRENTTYPE BRT ON BRT.BUILDINGID = B.ID ");
            join.append(" INNER JOIN RENTTYPE RT ON BRT.RENTTYPEID = RT.ID ");
        }
        return join;
    }


    public StringBuilder queryNormal(BuildingSearchBuilder request) {
        StringBuilder sql = new StringBuilder();
        Class builder = BuildingSearchBuilder.class;
        Field[] fields = builder.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            Object fieldValue = null;
            try {
                fieldValue = field.get(request);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String val = fieldValue != null ? fieldValue.toString() : null;
            if (val != null) {
                if (!(key.equalsIgnoreCase("districtId") || key.equalsIgnoreCase("typeCode") || key.startsWith("rentArea") || key.startsWith("rentPrice") || key.equalsIgnoreCase("staffId"))) {
                    boolean isNumber = BuildingUtils.Numberutils.isNumber(val);
                    if (isNumber) {
                        sql.append(" AND b." + key + " = " + val + " ");
                    } else {
                        sql.append(" AND B." + key + " LIKE '%" + val + "%' ");
                    }
                }
            }
        }
        return sql;
    }

    public StringBuilder querySpecial(BuildingSearchBuilder request) {
        StringBuilder sql = new StringBuilder();
        /**
         rent area
         */
        Double rentAreaTo = request.getRentAreaTo();
        Double rentAreaFrom = request.getRentAreaFrom();

        if (rentAreaFrom != null) {
            sql.append(" AND RA.VALUE >= " + rentAreaFrom);
        }

        if (rentAreaTo != null) {
            sql.append(" AND RA.VALUE <= " + rentAreaTo);
        }

        /**
         rent price
         */
        Double rentPriceTo = request.getRentPriceTo();
        Double rentPriceFrom = request.getRentPriceFrom();

        if (rentPriceFrom != null) {
            sql.append(" AND RENTPRICE >= " + rentPriceFrom);
        }

        if (rentPriceTo != null) {
            sql.append(" AND RENTPRICE <= " + rentPriceTo);
        }

        /**
         staff id
         */
        Integer staffId = request.getStaffId();
        if (staffId != null) {
            sql.append(" AND AMC.STAFFID = " + staffId);
        }

        /**
         type code
         */
        List<String> typeCode = request.getTypeCode();
        if (typeCode != null && typeCode.size() > 0) {
            sql.append(" AND RT.CODE IN (");
            sql.append(typeCode.stream().map(code -> "'" + code + "'").collect(Collectors.joining(",")));
            sql.append(")");
        }
        return sql;
    }

    public List<BuildingEntity> getAllBuilding(BuildingSearchBuilder request) {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM BUILDING B ");
        sql.append(joinTable(request));
        sql.append(" WHERE 1 = 1 ");
        sql.append(queryNormal(request));
        sql.append(querySpecial(request));
        System.out.println("sql: " + sql);

        try (Connection conn = BuildingUtils.ConnectDB.getConnect(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
