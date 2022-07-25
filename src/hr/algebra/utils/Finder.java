package hr.algebra.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public class Finder {

    public String isValid(String s){
        String result = s;

        try {
            new PeopleUnmarshaller().runEmployeeUnmarshaller();
            result = "Valid file";
        } catch (JAXBException e) {
            result = "Invalid file";
        } catch (SAXParseException e) {
            result = "Parse error";
        } catch (SAXException e) {
            result = "Parse error";
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public String getTempByCity(String city) throws IOException {

        WeatherParser.parse();
        try {
            Document doc = WeatherParser.createDocument(new File("Weather.xml"));

            XPathFactory xpathFactory = XPathFactory.newInstance();

            XPath xpath = xpathFactory.newXPath();

            String temp = WeatherParser.getTemp(doc, xpath, city);

            return temp;


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return "Error wrong City name";
        }
    }


}
