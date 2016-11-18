package Lecteurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Excel {
    private Connection conn;

    public Excel() {
        super();
    }

    public void connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:odbc:Lecteurs.Excel Files", "", "");
        } catch (SQLException ex) {
            System.err.println("Lecteurs.Excel Erreur de connexion � la base de donn�es.");
        }
    }

    public void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Lecteurs.Excel Erreur de deconnexion � la base de donn�es.");
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Excel l_excel = new Excel();
        l_excel.connexion();
        l_excel.deconnexion();

    }
}
