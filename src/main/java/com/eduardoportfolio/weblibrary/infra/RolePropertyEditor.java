package com.eduardoportfolio.weblibrary.infra;

import java.beans.PropertyEditorSupport;


import com.eduardoportfolio.weblibrary.dao.RoleDao;
import com.eduardoportfolio.weblibrary.models.Role;

//Class that deal and treat the data from the view (userForm) passed by checkboxes
public class RolePropertyEditor extends PropertyEditorSupport {
	
	private RoleDao roleDao;
	
	public RolePropertyEditor(RoleDao roleDao){
		this.roleDao = roleDao;
	}

	@Override
	public String getAsText() {
		return ((Role) getValue()).getName().toString();
	}

	@Override
	public void setAsText(String name) throws IllegalArgumentException {
		Role role = roleDao.getRole(name);
		setValue(role);
	}
	
}
