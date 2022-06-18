package com.eduardoportfolio.weblibrary.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class SpringServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	//Load classes before Spring MVC Servlet, inside a Listener, that is read when the server goes up, is the
	//ContextLoadListener
	protected Class<?>[] getRootConfigClasses() {
	//We put the security filter here, because it runs before the Spring MVC Servlet
		return new Class [] {SecurityConfiguration.class, AppWebConfiguration.class, 
																JpaConfiguration.class
																};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class [] {};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String [] {"/"};
	}
	
	@Override
	//Upload treatment. We can choose the temporary storage location (while receiving file), 
	//maximum size file, maximum request size, and so on. It receives a Dynamic Object that allow 
	//us register our configuration object of MultipartConfigElement type. We use in parallel with 
	//MultipartRsolver in the AppWebConfiguration class, to work with files.
	//With the parameter "", indicates that the web server will decide which location to use
	protected void customizeRegistration(Dynamic registration){
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	//We set the correctly profile, to run our application in development environment, because the profile "test"
	//in dataSource is exclusive for test environment
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
	}
	
	//Keep the EntityManager open, so, we don't need to use 'join' with 'fetch' anymore. We have
	//to plan our queries and leave this Lazy load usage to the last option, because the N+1 queries
	//problem, because, with this, many queries will be loaded without our control.
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new OpenEntityManagerInViewFilter() };
	}

}
