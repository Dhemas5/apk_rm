package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mahar
 */
public class Koneksi_dua {

    private static final String URL = "jdbc:mysql://localhost:3306/app_rm"; // Pastikan "MySql" diubah menjadi "mysql"
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Sesuaikan jika ada password

    public static Connection con() {
        Connection connection = null;
        try {
            // Pastikan driver MySQL di-load otomatis
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi Berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return connection;
    }
}
