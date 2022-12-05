package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private String host;
    private String database;
    private String user;
    private String password;

    public ConnectionFactory(){
        this.host = "localhost";
        this.database = "bd_sweepstake";
        this.user = "mateusdev";
        this.password = "qwe123";
    }

    public Connection getConnection() {
        String url = String.format(
                "jdbc:mysql://%s/%s?verifyServerCertificate=false&useSSL=true",
                this.host, this.database);
        try {
            return DriverManager.getConnection(url, this.user, this.password);
        } catch (SQLException ex) {
            System.out.println("An Error Occurred");
            ex.printStackTrace();
        }
        return null;
    }
}
