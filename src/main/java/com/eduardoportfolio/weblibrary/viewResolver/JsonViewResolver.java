package com.eduardoportfolio.weblibrary.viewResolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

	@Override
	//Iterates for all keys in the ModelAndView and generates the Json for each found object
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		//We don't need to give a parameter with the view name, because we won't redirect to any file 
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		//We can define only which key in the modelAndView have to generate Json
		view.setModelKey("products");
		return view;
	}

}
