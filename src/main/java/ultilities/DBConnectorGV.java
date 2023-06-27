package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Hello
 */
public class DBConnectorGV {

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "TEAM3KTPM";
    private static final boolean USING_SSL = true;

    private static String CONNECT_STRING;

    
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            StringBuilder connectStringBuilder = new StringBuilder();
            connectStringBuilder.append("jdbc:sqlserver://")
                    .append(SERVER).append(":").append(PORT).append(";")
                    .append("databaseName=").append(DATABASE_NAME).append(";")
                    .append("user=").append(USERNAME).append(";")
                    .append("password=").append(PASSWORD).append(";");
            
            if (USING_SSL) {
                connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
            }
            
            CONNECT_STRING = connectStringBuilder.toString();
            System.out.println("Connect String co dang: " + CONNECT_STRING);
        } catch (ClassNotFoundException ex) {
            System.out.println("Không tìm thấy SQLServer Driver");
        }
    }
    
    public synchronized static Connection getConnection() throws Exception {
        return DriverManager.getConnection(CONNECT_STRING);
    }
    
    public static void main(String[] args) throws Exception {
        String version = DBConnectorGV.getConnection().getMetaData().getDatabaseProductVersion();
        System.out.println(version);
    }

}
