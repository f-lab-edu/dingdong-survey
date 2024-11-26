package com.example.dingdong_survey;

import com.example.dingdong_survey.config.WebConfig;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {
        // 내장 톰캣 인스턴스 생성
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // 톰캣 컨텍스트 생성
        Context context = tomcat.addContext("", new File(".").getAbsolutePath());

        // Spring 애플리케이션 컨텍스트 생성
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(WebConfig.class);

        // DispatcherServlet 생성 및 등록
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
        Tomcat.addServlet(context, "dispatcher", dispatcherServlet).setLoadOnStartup(1);

        // 서블릿 매핑
        context.addServletMappingDecoded("/", "dispatcher");
        tomcat.getConnector();

        // 톰캣 서버 시작
        tomcat.start();
        tomcat.getServer().await();
    }
}
