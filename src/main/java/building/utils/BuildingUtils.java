package building.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class BuildingUtils {

    public static class ConnectDB {
        public static String url = "jdbc:mysql://localhost:3306/estatebasic";
        public static String user = "root";
        public static String pwd = "sapassword";

        public static Connection getConnect() {
            try {
                Connection conn = DriverManager.getConnection(url, user, pwd);
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class ParseUtils {
        public static Number convertStringToNumber(String s){
            try{
                NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
                return nf.parse(s);
            }catch (Exception ex){
                return 0;
            }
        }
    }

    public static class Numberutils {
        public static boolean isNumber(String s){
            Number n = ParseUtils.convertStringToNumber(s);
            if (n.doubleValue() != 0){
                return true;
            }
            return false;
        }
    }

    public static class TClass {
        public static <T> T convertToObject(Map<Object, Object> params, String key, Class<T> tClass){
            Object val = params.getOrDefault(key, null);

            if (val != null){
                if (tClass.getTypeName().equalsIgnoreCase("java.lang.Integer")){
                    val = val != "" ? Integer.valueOf(val.toString()) : null;
                }else if (tClass.getTypeName().equalsIgnoreCase("java.lang.Long")){
                    val = val != "" ? Long.valueOf(val.toString()) : null;
                }else if (tClass.getTypeName().equalsIgnoreCase("java.lang.String")){
                    val = val != "" ? val.toString() : null;
                } else if (tClass.getTypeName().equalsIgnoreCase("java.lang.Double")){
                    val = val != "" ? Double.valueOf(val.toString()) : null;
                }
                return tClass.cast(val);
            }
            return null;
        }
    }
}
