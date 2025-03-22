package xyz.sadiulhakim.listener;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListener {

	@Async
	@EventListener
	public void serverStarted(WebServerInitializedEvent event) {
		System.out.printf("Application is running on port: %s%n",event.getWebServer().getPort());
	}
}
