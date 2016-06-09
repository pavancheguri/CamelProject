package com.infy;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class JMSProcessorHeader implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		Message msg = exchange.getIn();
		String body = msg.getBody(String.class);
		System.out.println("--------------HEADERS------------------");
		msg.getHeaders().forEach((key,value)->System.out.println(key+":::"+value));
		System.out.println("----------------PROPERTIES----------------");
		exchange.getProperties().forEach((key,value)->System.out.println(key+":::"+value));
		System.out.println("--------------------------------");
		if(body.matches("(?s).*\\bInfosys\\b.*")){ // Pattern to find Infosys in the body
			msg.setHeader("Priority", "1");
		}
		System.out.println("--------------HEADERS------------------");
		System.out.println("Priority Value: "+msg.getHeader("Priority"));
		System.out.println("--------------------------------");
	}

}
