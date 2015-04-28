package com.excilys.webservice;

import javax.xml.ws.Endpoint;

public class ComputerDBVSMain {
	
	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8081/cdbWebService", new ComputerDBWS());
	}
}
