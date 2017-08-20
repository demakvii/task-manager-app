package com.techlabs.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_password")
	private String password;

	@OneToMany(mappedBy = "owner",cascade=CascadeType.ALL,fetch = FetchType.EAGER ,orphanRemoval=true)
	private Set<Task> tasks;

	public User() {
	}

	public Set<Task> getTaskList() {
		return tasks;
	}

	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	

}
