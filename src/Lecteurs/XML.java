package Lecteurs;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class XML {

    public void lire_XML (String path_fichier) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        org.w3c.dom.Document doc = builderFactory.newDocumentBuilder().parse(new FileInputStream(path_fichier));
        Node node ;
        Element E, E_1;

        //liste des etudiants
        NodeList etudiants = ((org.w3c.dom.Document) doc).getElementsByTagName("Etudiant");
        NodeList L ;

        for (int index =0 ; index < etudiants.getLength(); index++)
        {
            // un etudiant
            E = (Element) etudiants.item(index);

            L =  E.getElementsByTagName("NumEt");
            E_1 = (Element) L.item(0); // un seul noeud NumEt
            System.out.println("num etudiant "+E_1.getTextContent());

            L =  E.getElementsByTagName("nom");
            E_1 = (Element) L.item(0);
            System.out.println("num etudiant "+E_1.getTextContent());
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        XML l_xml = new XML();
        try {
            //l_xml.lire_XML("C:/Users/thaonzo/Documents/2016_2017/ID/Mediateur/data/Univ_BD_3.xml");
            l_xml.lire_XML("C:/Users/alice/Desktop/Fac/ID/Mediateur/data/Univ_BD_3.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }

}
