package ra.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    /*
    * 1. URL của CSDL cần kết nối đến:
    *   - address Server cài CSDL: 192.10.0.6 (localhost)
    *   - Database name (EcommerceDB)
    *   - port: 3306
    * 2. Driver của CSDL MySQL
    * 3. UserName của database
    * 4. Password của database
    * */
    private static final String URL = "jdbc:mysql://localhost:3306/ecommercedb";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234$";
    //Phương thức mở kết nối và tạo đối tượng Connection để làm việc với db
    public static Connection openConnection(){
        Connection conn = null;
        try {
            //1. set Driver cho DriverManager
            Class.forName(DRIVER);
            //2. Khởi tạo đối tượng Connection từ DriverManager
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //3. Trả về đối tượng Connection
        return conn;
    }
    //Phương thức cho phép đóng Connection, CallableStatement
    public static void closeConnection(Connection conn, CallableStatement callSt){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (callSt!=null){
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
