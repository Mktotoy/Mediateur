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
            Class.forName("com.hxtt.sql.excel.ExcelDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:excel:/C:/Users/alice/Desktop/Fac/ID/Mediateur/src/Source3.xls");
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
