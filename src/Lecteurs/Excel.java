package Lecteurs;

import java.sql.*;


public class Excel {
    private Connection conn;

    public Excel() {
        super();
    }

    private void connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.hxtt.sql.excel.ExcelDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:excel:/C:/Users/alice/Desktop/Fac/ID/Mediateur/data/Source3.xls"); // Alice
        } catch (SQLException ex) {
            System.err.println("Lecteurs.Excel Erreur de connexion � la base de donn�es.");
        }
    }

    public void request_select() throws SQLException, ClassNotFoundException {
        Statement requete = null;
        ResultSet resultat=null;
        ResultSetMetaData rsmd = null;

        this.connexion();

        try {
            requete = conn.createStatement();
            resultat= requete.executeQuery("Select * from [2006$]");
            rsmd = resultat.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(resultat.next())
            {
                /*int nCols = resultat.getColumnCount();
                /*resultat.getString()*/
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultat.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        this.deconnexion();
    }

    private void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Lecteurs.Excel Erreur de deconnexion � la base de donn�es.");
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Excel l_excel = new Excel();
        l_excel.connexion();
        l_excel.request_select();
        l_excel.deconnexion();

    }
}
