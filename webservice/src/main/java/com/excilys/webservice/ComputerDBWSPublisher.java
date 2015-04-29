package com.excilys.webservice;

import javax.xml.ws.Endpoint;

public class ComputerDBWSPublisher {
	
	public static void main(String[] args) {

		 Endpoint.publish("http://localhost:8083/ws/cdbWebService", new ComputerDBWS());
	}
}
