package com.infy;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.BasicConfigurator;

public class BasicRouting {
	public static void main(String[] args) throws Exception{
		//BasicConfigurator.configure();
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes( new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("file:data/inputLocation?recursive=true&noop=true")
				.to("file:data/outputLocation");
			}
		});
		
		context.start();
		Thread.sleep(1000);
		context.stop();
	}
}
