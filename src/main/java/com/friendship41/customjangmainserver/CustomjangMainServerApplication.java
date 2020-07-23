package com.friendship41.customjangmainserver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class CustomjangMainServerApplication implements ServletContextInitializer {
  public static void main(String[] args) {
    SpringApplication.run(CustomjangMainServerApplication.class, args);
  }

  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    servletContext.getSessionCookieConfig().setName("clientsession");
  }
}
