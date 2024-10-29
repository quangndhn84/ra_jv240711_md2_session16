package ra.bussiness;

import ra.entity.Categories;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CategoriesBussiness {
    //Danh sách danh mục
    public static List<Categories> findAll() {
        //1. Tạo đối tương Connection
        //2. Tạo đôi tượng CallableStatement từ đối tượng Connection
        //3. Gọi và thực thi procedure
        //4. Hứng dữ liệu và xử lý dữ liệu
        //5. Đóng kết nối
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_categories()}");
            /*
             * executeQuery(): Thực thi procedure cho câu lệnh select
             * executeUpdate(): thực thi procedure cho câu lệnh insert, update, delete không có tham số trả ra
             * execute(): thực thi procedure cho câu lệnh insert, update, delete có tham số trả ra
             * */
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<>();
            while (rs.next()) {
                Categories catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalog_id"));
                catalog.setCatalogName(rs.getString("catalog_name"));
                catalog.setDescription(rs.getString("Catalog_description"));
                catalog.setStatus(rs.getBoolean("catalog_status"));
                listCategories.add(catalog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    //Thêm mới danh mục
    public static boolean save(Categories catalog) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_categories(?,?,?)}");
            //1. Set giá trị cho các tham số vào
            callSt.setString(1, catalog.getCatalogName());
            callSt.setString(2, catalog.getDescription());
            callSt.setBoolean(3, catalog.isStatus());
            //2. Đăng ký kiểu dữ liệu cho các tham số ra
            //3. Thưc hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Categories findById(int catalogId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Categories catalog = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_categories_by_id(?)}");
            callSt.setInt(1, catalogId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalog_id"));
                catalog.setCatalogName(rs.getString("catalog_name"));
                catalog.setDescription(rs.getString("Catalog_description"));
                catalog.setStatus(rs.getBoolean("catalog_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return catalog;
    }

    public static boolean update(Categories catalog) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_categories(?,?,?,?)}");
            //1. Set giá trị cho các tham số vào
            callSt.setInt(1, catalog.getCatalogId());
            callSt.setString(2, catalog.getCatalogName());
            callSt.setString(3, catalog.getDescription());
            callSt.setBoolean(4, catalog.isStatus());
            //2. Đăng ký kiểu dữ liệu cho các tham số ra
            //3. Thưc hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean delete(int catalogId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_categories(?)}");
            //1. Set giá trị cho các tham số vào
            callSt.setInt(1, catalogId);
            //2. Đăng ký kiểu dữ liệu cho các tham số ra
            //3. Thưc hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static int staticticCategories(boolean status) {
        Connection conn = null;
        CallableStatement callSt = null;
        int cntCategories = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call statictic_categories(?,?)}");
            //1. Set giá trị cho các tham số vào
            callSt.setBoolean(1, status);
            //2. Đăng ký kiểu dữ liệu cho các tham số ra
            callSt.registerOutParameter(2, Types.INTEGER);
            //3. Thưc hiện gọi procedure
            callSt.execute();
            //4. Lấy giá trị của các tham số trả ra
            cntCategories = callSt.getInt(2);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return cntCategories;
    }
}
