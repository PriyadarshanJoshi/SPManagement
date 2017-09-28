package com.neosoft.SportsClubManagementWeb;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.annotation.HandlesTypes;

import org.apache.catalina.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.NonEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

import com.sun.faces.config.ConfigureListener;
import com.sun.faces.config.FacesInitializer;

@SpringBootApplication
public class SportsClubManagementWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsClubManagementWebApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SportsClubManagementWebApplication.class);
	}

	@Bean
	public ServletRegistrationBean facesServletRegistration() {

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		servletRegistrationBean.addUrlMappings("/faces/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}

	@Bean
	@ConditionalOnMissingBean
	public RequestContextListener getRequestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	@ConditionalOnMissingBean(NonEmbeddedServletContainerFactory.class)
	public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		tomcat.addContextCustomizers(new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				// register FacesInitializer
				context.addServletContainerInitializer(new FacesInitializer(),
						getServletContainerInitializerHandlesTypes(FacesInitializer.class));

				// add configuration from web.xml
				context.addWelcomeFile("index.xhtml");

				// register additional mime-types that Spring Boot doesn't register
				context.addMimeMapping("eot", "application/vnd.ms-fontobject");
				context.addMimeMapping("ttf", "application/x-font-ttf");
				context.addMimeMapping("woff", "application/x-font-woff");
			}
		});

		return tomcat;
	}

	@SuppressWarnings("rawtypes")
	private Set<Class<?>> getServletContainerInitializerHandlesTypes(
			Class<? extends ServletContainerInitializer> sciClass) {
		HandlesTypes annotation = sciClass.getAnnotation(HandlesTypes.class);
		if (annotation == null) {
			return Collections.emptySet();
		}

		Class[] classesArray = annotation.value();
		Set<Class<?>> classesSet = new HashSet<Class<?>>(classesArray.length);
		for (Class clazz : classesArray) {
			classesSet.add(clazz);
		}

		return classesSet;
	}

}
