package Lecteurs;

import java.sql.*;


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
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@miage03.dmiage.u-paris10.fr:1521:miage", "thaonzo", "apprentis2014PW");
            //this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "pottier", "biill483");

        } catch (SQLException ex) {
            System.err.println("Erreur de connexion � la base de donn�es.");
        }
    }

    private void request() {

        Statement requete = null;
        ResultSet resultat=null;
        ResultSetMetaData rsmd = null;

        try {
            requete = conn.createStatement();
            resultat= requete.executeQuery("SELECT * FROM ENSEIGNE");
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
        //requete.executeUpdate   : update , insert, create

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
        l_oracle.request();
        l_oracle.deconnexion();

    }
}
