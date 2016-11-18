package Lecteurs;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class XML {

    public void lire_XML (String path_fichier) throws SAXException, IOException, ParserConfigurationException{

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document doc = builderFactory.newDocumentBuilder().parse(new FileInputStream(path_fichier));

        //Question 1 : liste des étudiants dont la provenance n'est pas la France
        System.out.println("Retournez le nombre d’étudiants dont le pays de Provenance n’est pas la ’France’.");
        NodeList liste_etudiant = doc.getElementsByTagName("Etudiant");
        int nbre_res=0;
        for (int i = 0; i<liste_etudiant.getLength(); i++)
        {
            if(liste_etudiant.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                final Element etudiant = (Element) liste_etudiant.item(i);

                //Trouver Provenance, nom  et numéro étudiant
                final Element nom = (Element) etudiant.getElementsByTagName("nom").item(0);
                final Element numEtu = (Element) etudiant.getElementsByTagName("NumEt").item(0);
                final Element provenance = (Element) etudiant.getElementsByTagName("Provenance").item(0);

                StringBuilder res= new StringBuilder();
                if(!provenance.getTextContent().equals("France"))
                {
                    res.append("[");
                    res.append("Nom : ").append(nom.getTextContent());
                    res.append(", Numéro Etudiant : ").append(numEtu.getTextContent());
                    res.append(", Provenance : ").append(provenance.getTextContent());
                    res.append("]");
                    nbre_res++;
                }
                System.out.println(res.toString());
            }
        }
        System.out.println("Il y a " +nbre_res+" étudiant(s) donc la provenance n'est pas la France");
        // Fin Question 1

        // Question 2 : Afficher le nombre des cours par type
        NodeList section_cours = doc.getElementsByTagName("Cours");
        Map type_cours = new HashMap<String, Integer>();
        int tmp = section_cours.getLength();
        //On commence à partir du "second" car le premier correspond à la section Cours
        for(int j=1; j<section_cours.getLength(); j++) {
            final Element e = (Element) section_cours.item(j);
            String nom_type = e.getElementsByTagName("Type").item(0).getTextContent();
            Integer nbre;
            if (type_cours.containsKey(nom_type)) {
                nbre = (Integer) type_cours.get(nom_type);
                nbre = nbre + 1;
                type_cours.put(nom_type, nbre);
            } else {
                nbre = 1;
                type_cours.put(nom_type, nbre);
            }
        }
        System.out.println(type_cours.toString());
        //Fin Question 2

        //Question 3
        class Inscription
        {
            private int id_cours;
            private int note;

            public Inscription(int id_cours,  int note) {
                this.id_cours = id_cours;
                this.note = note;
            }

            @Override
            public String toString() {
                return "Inscription{" +
                        "id_cours=" + id_cours +
                        ", note=" + note +
                        '}';
            }
        }

        class Cours
        {
            private int id_cours;
            private String type_cours;
            int note_max;

            public Cours(int id_cours, String type_cours) {
                this.id_cours = id_cours;
                this.type_cours = type_cours;
            }

            public Cours(int id_cours, String type_cours, int note) {
                this.id_cours = id_cours;
                this.type_cours = type_cours;
                this.note_max = note;
            }

            @Override
            public String toString() {
                return "Cours{" +
                        "id_cours=" + id_cours +
                        ", type_cours='" + type_cours + '\'' +
                        ", note_max=" + note_max +
                        '}';
            }
        }
        //On récupère toutes les inscriptions
        NodeList liste_node_inscription = doc.getElementsByTagName("Inscription");
        List<Inscription> liste_inscription = new ArrayList<>();

        HashMap<Integer, Integer> l_note_cours = new HashMap<>();
        //Pour chaque inscription
        for(int i=1; i<liste_node_inscription.getLength(); i++)
        {
            final Element note = (Element) liste_node_inscription.item(i);
            //On récupère l'ID du cours, et la note obtenue
            int id_cours = Integer.parseInt(note.getElementsByTagName("ID_cours").item(0).getTextContent());
            int note_cours = Integer.parseInt(note.getElementsByTagName("Note_cours").item(0).getTextContent());

            liste_inscription.add(new Inscription(id_cours, note_cours));
        }

        section_cours = doc.getElementsByTagName("Cours");
        Map<Integer, Cours> m_cours = new HashMap<>();
        // On commence à 1 parce que la 0 correspond à la section cours
        for(int j=1; j<section_cours.getLength(); j++) {
            final Element e = (Element) section_cours.item(j);
            String nom_type = e.getElementsByTagName("Type").item(0).getTextContent();
            Integer id_cours = Integer.parseInt(e.getElementsByTagName("ID_cours").item(0).getTextContent());
            m_cours.put(id_cours, new Cours(id_cours, nom_type));
        }

        // Note maximum pour chaque cours (par id de cours)
        for (Inscription aListe_inscription : liste_inscription) {
            Integer id_c = aListe_inscription.id_cours;
            Integer note = aListe_inscription.note;

            // on récupère la note maximum précédente
            Cours c = m_cours.get(id_c);
            Integer note_max_svg = c.note_max;
            if (note_max_svg < note) {
                c.note_max = note;
                m_cours.put(aListe_inscription.id_cours, c);
            }
        }
        System.out.println(m_cours.toString());
        // m_note contient la note maximale pour chaque TYPE de cours
        HashMap<String, Integer> m_note = new HashMap<>();
        for (Map.Entry<Integer, Cours> note: m_cours.entrySet())
        {
            String nom_type = note.getValue().type_cours;
            int note_cours = note.getValue().note_max;

            //Si le type de cours n'est pas encore dans la liste, on l'ajoute
            if (!m_note.containsKey(nom_type)) {
                m_note.put(nom_type, note_cours);
            }
            // Sinon
            else
            {
                // On l'ajoute pour modifier sa valeur seulement si la note dans la liste est plus basse que celle étudiée. 
                if(note_cours > m_note.get(nom_type)) {
                    m_note.put(nom_type, note_cours);
                }
            }


        }
        System.out.println(m_note);
        //Fin Question 3
    }

}
