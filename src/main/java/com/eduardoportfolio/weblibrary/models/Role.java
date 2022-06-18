package com.eduardoportfolio.weblibrary.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 *
 */

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = -444201384629079806L;
	@Id
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
	
	

