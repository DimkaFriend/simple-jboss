package com.eduardoportfolio.weblibrary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eduardoportfolio.weblibrary.security.JwtAuthenticationFilter;


//Has to be placed above the class that is responsible for effectively controls the access rules. This annotation
//load another components as well, like SecurityExpressionHandler (Evaluate specific languages of security control)
//and WebInvocationPrivilegeEvaluator (responsible to verify if the user has access for the address)
@EnableWebSecurity
//This class inherits all the infrastructure ready to begin our security configurations 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//Interface that helps us to better search the user to apply the rules, it is passed through the 
	//AuthenticationManagerBuilder class. We have to implement this interface in our UserDao model
	@Autowired
	UserDetailsService users;
		
	//Overload of the configure method thats receive a AuthenticationManagerBuilder that allow us associate
	//a new UserDetailService in our Spring Security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//The HttpSecurity object is the start point to customize our authentication and authorization rules.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		//The invocation order is very important, first we do the restrictions, later we release all
//		http.authorizeRequests()
//		//It says, beside user is logged, he has to have ADMIN role to have the permission
//		.antMatchers("/products/form").hasRole("ADMIN")
//		.antMatchers("/shopping/**").permitAll()
//		.antMatchers(HttpMethod.POST, "/register").hasRole("ADMIN")
//		.antMatchers(HttpMethod.GET,"/register/**").permitAll()
//		//All the rest is only released with user authenticated
//		.anyRequest().authenticated()
//		//With .loginPage, we redirect to our login page. We need call permitall() to tell that  this address is
//		//allowed for everyone
//		.and().formLogin().loginPage("/login").permitAll().and().antMatcher("/signup").anonymous()
//		//For default, the Spring Security only allow that the logout has to be made by Post Method. It is to
//		//force the CSRF token to be passed. We can use logoutRequestMatcher to allow to be accessed by a GET URL 
//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		//The invocation order is very important, first we do the restrictions, later we release all
//		http.authorizeRequests().antMatchers("/login","/signup").permitAll().antMatchers(HttpMethod.POST, "/signup").permitAll();//authorizeRequests().antMatchers("/login","/signup").anonymous()
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET,"/rest/login").permitAll();

		
	}

	//With this method with WebSecurity object parameter, we can tell Spring Security to ignore
	//any access to a URL that begins with /resources/**, we made this to access the static resources
	//together with configureDefaultServletHandling in the AppWeb
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
