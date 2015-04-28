package com.excilys.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ComputerDBClient {
	
	public static void main(String[] args) throws MalformedURLException {

		URL url = new URL("http://localhost:8081/ws/cdbService?wsdl");

		QName qname = new QName("http://webservice.excilys.com/", "ComputerDatabaseService");
		Service service = Service.create(url, qname);
		IComputerDBWS webservice = service.getPort(IComputerDBWS.class);
		System.out.println(webservice.getByIdComputer(10));
	}

}
