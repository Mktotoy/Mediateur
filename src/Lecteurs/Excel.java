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

    public void request_select(int question) throws SQLException, ClassNotFoundException {
        Statement requete = null;
        ResultSet resultat=null;


        this.connexion();

        try {
            requete = conn.createStatement();
            if (question == 1) {
                System.out.println("Retournez le nombre d’étudiants dont le pays de Provenance n’est pas la ’France’.");
                resultat= requete.executeQuery("Select DISTINCT(ID), Nom, Prenom, Statut, Provenance from [2006$] JOIN [2007$] WHERE Provenance <> 'France' AND Statut = 'etudiant'");
                affiche_res(resultat);
            }
            else if (question == 2) {
                System.out.println("Affichez le nombre des cours par Type (CM, TD ou TP)");
                resultat = requete.executeQuery("select DISTINCT(ID), Type_cours, count(*) from [2006$] JOIN [2007$] WHERE Statut ='etudiant' GROUP BY Type_Cours");
                affiche_res(resultat);
            }
            else if (question == 3) {
                System.out.println("Affichez la note maximale des cours par Type (CM, TD ou TP)");
                resultat = requete.executeQuery("SELECT Type_cours, MAX(Note) FROM [2006$] GROUP BY Type_cours");
                affiche_res(resultat);
            }

            affiche_res(resultat);


        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        this.deconnexion();
    }

    private void affiche_res(ResultSet resultat) throws SQLException {
        ResultSetMetaData rsmd = null;
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

    private void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Lecteurs.Excel Erreur de deconnexion � la base de donn�es.");
        }
    }

}
