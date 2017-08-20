package com.techlabs.dto;

import java.util.Set;

public class UserDto {
	private String userName;
	private String password;
	
	private Set<TaskDto> taskList ;
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(String userName, String password,Set<TaskDto> taskList) {
		this.userName = userName;
		this.password = password;
		this.taskList = taskList;
	}

	public String getUserName() {
		return userName;
	}

	

	public String getPassword() {
		return password;
	}

	public Set<TaskDto> getTaskList() {
		return taskList;
	}

	
}
