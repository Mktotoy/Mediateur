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
            //this.conn = DriverManager.getConnection("jdbc:oracle:thin:@miage03.dmiage.u-paris10.fr:1521:miage", "thaonzo", "apprentis2014PW");
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "pottier", "biill483");

        } catch (SQLException ex) {
            System.err.println("Erreur de connexion a la base de donnees.");
        }
    }

    public void request_select(int question) throws SQLException, ClassNotFoundException {

        this.connexion();
        Statement requete = null;
        ResultSet resultat=null;
        ResultSetMetaData rsmd = null;

        try {
            requete = conn.createStatement();
            if (question == 1) {
                System.out.println("Retournez le nombre d’étudiants dont le pays de Provenance n’est pas la ’France’.");
                resultat = requete.executeQuery("select * from ETUDIANT WHERE PROVENANCE <> 'fr'");
            }
            else if (question == 2) {
                System.out.println("Affichez le nombre des cours par Type (CM, TD ou TP)");
                resultat = requete.executeQuery("select type, count(*) from COURS GROUP BY type");
            }
            else if (question == 3) {
                System.out.println("Affichez la note maximale des cours par Type (CM, TD ou TP)");
                resultat = requete.executeQuery("select c.type, max(note_cours) from inscription i , cours c" +
                        " where c.numcours=i.numcours" +
                        " group by c.type");
            }
            rsmd = resultat.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(resultat.next())
            {
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
            System.err.println("Erreur de deconnexion a la base de donnees.");
        }
    }
}
