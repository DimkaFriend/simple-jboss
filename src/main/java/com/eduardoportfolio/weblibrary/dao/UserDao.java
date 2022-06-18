package com.eduardoportfolio.weblibrary.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.eduardoportfolio.weblibrary.controllers.AuthControllerRest;
import com.eduardoportfolio.weblibrary.models.Users;

@Repository
public class UserDao implements UserDetailsService {

	@Autowired
	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
	@PersistenceContext
	private EntityManager manager;
	
	Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	public boolean existsByUsername(String username) {
		logger.info(username);
		String jpql = "select u from Users u where u.login = :login";
//		localContainerEntityManagerFactoryBean.getObject().createEntityManager().createQuery(jpql, Users.class);
		logger.info("manager.createQuery ");
		List<Users> users = manager.createQuery(jpql, Users.class)
				.setParameter("login", username).getResultList();
		if(users.isEmpty()){
			return false;
		}
		return true;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		//We don't need to retrieve the password, because we don't want to deal with the hash before make the
		//query, we just search for the logging, and the Spring will verify is the saved password confers with
		//the password, passed in the form,
		String jpql = "select u from Users u where u.wlogin = :login";
		
		List<Users> users = manager.createQuery(jpql, Users.class)
														.setParameter("login", userName).getResultList();
		if(users.isEmpty()){
			throw new UsernameNotFoundException ("The user "+ userName + "is not found !");
		}
		
		return users.get(0);
	}
	
	public UserDetails loadUserById(Long id) {
		String jpql = "select u from Users u where u.id = :id";
				
				List<Users> users = manager.createQuery(jpql, Users.class)
																.setParameter("id", id).getResultList();
				if(users.isEmpty()){
					throw new UsernameNotFoundException ("The user "+ id + "is not found !");
				}
				
				return users.get(0);
	}
	
	public void createUser(Users user) throws Exception {
		String jpql = "select u from Users u where login = :login";
		
		List<Users> users = manager.createQuery(jpql, Users.class)
														.setParameter("login", user.getLogin()).getResultList();
		if(!users.isEmpty()){
			throw new Exception("The user "+ user.getLogin() + "is not found !");
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		manager.persist(user);
	}
	
	public void save(Users user){
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		manager.persist(user);
	}
	
	public void delete(Users user){
		manager.remove(user);
	}
}
