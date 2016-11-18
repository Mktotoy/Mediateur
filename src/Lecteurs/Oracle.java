package Lecteurs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Oracle {
    private Connection conn;

    public Oracle() {
        super();
    }

    private void connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
            //this.conn = DriverManager.getConnection("jdbc:oracle:thin:@miage03.dmiage.u-paris10.fr:1521:miage", "thaonzo", "apprentis2014PW");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "pottier", "biill483");

        } catch (SQLException ex) {
            System.err.println("Erreur de connexion � la base de donn�es.");
        }
    }

    private void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Erreur de deconnexion � la base de donn�es.");
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Oracle l_oracle = new Oracle();
        l_oracle.connexion();
        l_oracle.deconnexion();

    }
}
