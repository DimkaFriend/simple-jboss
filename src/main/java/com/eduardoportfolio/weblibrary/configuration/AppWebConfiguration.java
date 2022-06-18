package com.eduardoportfolio.weblibrary.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//import com.eduardoportfolio.weblibrary.infra.AmazonFileSaver;
import com.google.common.cache.CacheBuilder;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackages = {"com.eduardoportfolio.weblibrary.configuration"
								,"com.eduardoportfolio.weblibrary.controllers"
								,"com.eduardoportfolio.weblibrary.dao"
								,"com.eduardoportfolio.weblibrary.models"
								,"com.eduardoportfolio.weblibrary.security"
								,"com.eduardoportfolio.weblibrary"})
//WebMvcConfigurerAdapter extended to use the addInterceptors method 
public class AppWebConfiguration extends WebMvcConfigurerAdapter{
	
//	@Bean
//	//Tells Spring that he has to decide which view resolver to use based in the Accept
//	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
//		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
//		resolvers.add(internalResourceViewResolver());
//		resolvers.add(new JsonViewResolver());
//		resolvers.add(getMarshallingXmlViewResolver());
//		
//		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//		resolver.setViewResolvers(resolvers);
//		resolver.setContentNegotiationManager(manager);
//		return resolver;
//	}
//	
//	@Bean
//	public CustomXMLViewResolver getMarshallingXmlViewResolver() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		marshaller.setClassesToBeBound(Product.class);
//		//XStreamMarshaller marshaller = new XStreamMarshaller();
//		//HashMap<String, Class<?>> keys = new HashMap<String,Class<?>>();
//		//keys.put("product", Product.class);
//		//keys.put("price", Price.class);
//		//marshaller.setAliases(keys);
//		return new CustomXMLViewResolver(marshaller);
//	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		//Exposes all managed beans through EL
		//resolver.setExposeContextBeansAsAttributes(true);
		//Expose only a specific managed bean through EL
		resolver.setExposedContextBeanNames("shoppingCart");
		return resolver;
	}
	
	@Bean (name="messageSource")
	//Indicate to Spring the location of the file with the messages that is created. The method
	//has to have messageSource name. We can annotate this name in the Bean.
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		//Typical development environment configuration, because we don't want refresh the server
		//always when this file change
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	@Bean
	//In this Bean, we tell Spring always use this date format. This method has to have this name
	public FormattingConversionService mvcConversionService(){
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		//Some conversions of this class Calendar>Long/Long>Calendar/Date>Calendar/Calendar>Date
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		//Here we inform which format that we want
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		//Finally, we register all converters in the FormattingConversionService object type
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	@Bean
	//The interface MultipartResolver is where define methods necessary for the initial treatment 
	//of a request with multipart/form-data send mode, could be also CommonsMultipartResolver interface 
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}

	@Bean
	//This object type offers many methods, that we can use to realize many types of request
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	//Class responsible for effectively hold the objects that have to be cache
	//The ConcurrentMapCacheManager is a simple implementation, we have another ones
	//@Bean
	//public CacheManager cacheManager(){
	//	return new ConcurrentMapCacheManager();
	//}
	
	//Guava Cache implementation from Google
	@Bean
	public CacheManager cacheManager(){
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100)
															.expireAfterAccess(5, TimeUnit.MINUTES);
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(builder);
		return cacheManager;
	}

	//Override method from WebMvcConfigurerAdapter, to change idiom, based on the passed parameters
	// /products?locale=pt or /products?locale=en_US passed in a link on header.jsp
	//The LocaleChangeInterceptor verify if it was used a locale parameter in the request, if positive, it change
	//the idiom automatically
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	//Returns a localeResolver implementation of this interface, and we use the strategy to hold the preferred
	//language in a cookie (could be SessionLocaleResolver)
	@Bean
	public LocaleResolver localeResolver(){
		return new CookieLocaleResolver();
	}

	//When we invoke enable() method in the DefaultServletHandlerConfigurer class, we tell SpringMvc
	//that, in the case that he don't resolve the address, he has to delegate the call, to the 
	//servlet container in use. Without this, when we try to access the static resource by URL, 
	//that we has allowed the address to the Spring Security in the configure(WebSecurity) method,
	//he return the status 404, because he try to find a controller with the URL
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
	
//	@Bean
//	public MailSender mailSender(){
//		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
//		javaMailSenderImpl.setHost("smtp.gmail.com");
//		javaMailSenderImpl.setPassword("password");
//		javaMailSenderImpl.setPort(587);
//		javaMailSenderImpl.setUsername("myLogin");
//		Properties mailProperties = new Properties();
//		mailProperties.put("mail.smtp.auth", true);
//		mailProperties.put("mail.smtp.starttls.enable", true);
//		javaMailSenderImpl.setJavaMailProperties(mailProperties);
//		
//		return javaMailSenderImpl;
//	}
}
