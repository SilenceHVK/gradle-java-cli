package me.hvkcoder.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class MySpringApplication {
	public static void main(String[] args) throws UnknownHostException {
		ConfigurableEnvironment env = SpringApplication.run(MySpringApplication.class, args).getEnvironment();
		String envPort = env.getProperty("server.port");
		String envContext = env.getProperty("server.contextPath");
		String externalIP = InetAddress.getLocalHost().getHostAddress();
		String port = envPort == null ? "8080" : envPort;
		String context = envContext == null ? "" : envContext;

		log.info("""
				Access URLs:
				----------------------------------------------------------
				\tLocal-API: http://127.0.0.1:{}/{}\s
				 External-API: http://{}:{}/{}\s
				----------------------------------------------------------"""
			, port, context, externalIP, port, context
		);
	}
}
