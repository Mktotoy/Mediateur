package Lecteurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Access {
    private Connection conn;

    public Access() {
        super();
    }

    public void connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:odbc:MS Lecteurs.Access Database", "", "");
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            System.err.println("Erreur de connexion à la base de données.");
        }
    }

    public void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Erreur de deconnexion à la base de données.");
        }
    }
}
