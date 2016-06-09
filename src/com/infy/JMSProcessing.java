package com.infy;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class JMSProcessing {
	public static void main(String[] args)  {

		try {
			CamelContext context = new DefaultCamelContext();

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( "tcp://localhost:61616");

			context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory) ); // Need to add spring JAR's here

			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
					from("file:data/input?noop=true").process(new JMSProcessorHeader()).to("jms:Orders");
				}
			});

			context.start();
			System.out.println("Started the Apache Camel!");

			Thread.sleep(10000);

			context.stop();
			System.out.println("Stopped the Apache Camel!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
