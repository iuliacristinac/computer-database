package com.excilys.console;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.webservice.IComputerDBWS;

@Component
public class ComputerDBClient {

	@Autowired
	private IComputerDBWS webservice;
	
	public ComputerDBClient() {
		URL url = null;	
		try {
			url = new URL("http://localhost:8083/ws/cdbWebService?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qname = new QName("http://webservice.excilys.com/", "ComputerDBWSService");
		Service service = Service.create(url, qname);
		webservice = service.getPort(IComputerDBWS.class);
	}

	public IComputerDBWS getWebservice() {
		return webservice;
	}
}

