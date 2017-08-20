package com.techlabs.dto;

import java.sql.Date;
import java.util.Set;

public class TaskDto {
	private int taskId;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private boolean status;

	private UserDto userDto;

	private Set<SubTaskDto> subTaskList;

	public TaskDto() {

	}

	public TaskDto(int taskId, String name, String description, Date startDate,Date endDate,
			boolean status, UserDto userDto, Set<SubTaskDto> subTaskList) {
		this.taskId = taskId;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.userDto = userDto;
		this.subTaskList = subTaskList;
	}

	public Date getEndDate() {
		return endDate;
	}


	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public int getTaskId() {
		return taskId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public boolean getStatus() {
		return status;
	}

	public Set<SubTaskDto> getSubTaskList() {
		return subTaskList;
	}
}
