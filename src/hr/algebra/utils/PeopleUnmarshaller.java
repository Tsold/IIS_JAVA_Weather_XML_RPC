package hr.algebra.utils;

import hr.algebra.model.ListOfPeople;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class PeopleUnmarshaller {


    public void runEmployeeUnmarshaller() throws JAXBException, SAXException {

        JAXBContext context = JAXBContext.newInstance(ListOfPeople.class);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("people.xsd"));

        Unmarshaller unmarshaller = context.createUnmarshaller();

        unmarshaller.setSchema(schema);
      //  unmarshaller.setEventHandler(new PeopleValidationEventHandler());

        unmarshaller.unmarshal(new File("local file"));


    }
}


