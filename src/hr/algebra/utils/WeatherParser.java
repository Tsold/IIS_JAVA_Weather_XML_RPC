package hr.algebra.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class WeatherParser {


public static void parse() throws IOException {

    URLConnection connection = new URL("https://vrijeme.hr/hrvatska_n.xml").openConnection();
    InputStream is = connection.getInputStream();

    String text = new BufferedReader(
            new InputStreamReader(is, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining("\n"));

    java.io.FileWriter fw = new java.io.FileWriter("Weather.xml");
    fw.write(text);
    fw.close();

}

    public static String getTemp(Document doc, XPath xpath, String name) {
        String result = null;
        try {
            XPathExpression expr = xpath.compile("/Hrvatska/Grad[GradIme='"+name+"']/Podatci/Temp");
            result = (String) expr.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static Document createDocument(File file) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        return document;
    }


}
