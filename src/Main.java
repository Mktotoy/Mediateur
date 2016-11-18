import Lecteurs.Excel;
import Lecteurs.Oracle;
import Lecteurs.XML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
        System.out.println("3 select");
/*
        Oracle l_oracle = new Oracle();

        System.out.println("Oracle");
        l_oracle.request_select(1);
        l_oracle.request_select(2);
        l_oracle.request_select(3);
        System.out.println("Fin Oracle");

*/
        System.out.println("Excel");
        Excel l_excel = new Excel();
        l_excel.request_select(1);
        l_excel.request_select(2);
        l_excel.request_select(3);
        System.out.println("Fin Excel");

/*
        System.out.println("XML");
        XML l_xml = new XML();
        //l_xml.lire_XML("C:/Users/thaonzo/Documents/2016_2017/ID/Mediateur/data/Univ_BD_3.xml");
        l_xml.lire_XML("C:/Users/alice/Desktop/Fac/ID/Mediateur/data/Univ_BD_3.xml");
        System.out.println("Fin XML");
        */
    }
}
