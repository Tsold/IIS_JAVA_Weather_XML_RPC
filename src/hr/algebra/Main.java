package hr.algebra;

import hr.algebra.utils.Finder;
import hr.algebra.utils.WeatherParser;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, XmlRpcException {




        System.out.println("Starting XML-RPC Server...");
        WebServer server=new WebServer(8080);

        XmlRpcServer xmlServer=server.getXmlRpcServer();
        PropertyHandlerMapping phm=new PropertyHandlerMapping();
        phm.addHandler("Main", Finder.class );
        xmlServer.setHandlerMapping(phm);

        XmlRpcServerConfigImpl serverConfig=(XmlRpcServerConfigImpl)xmlServer.getConfig();
        serverConfig.setContentLengthOptional(false);
        serverConfig.setEnabledForExtensions(true);

        server.start();
        System.out.println("Server running...");
        System.out.println("Waiting for requests...");




    }
}
