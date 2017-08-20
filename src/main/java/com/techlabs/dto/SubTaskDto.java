package com.techlabs.dto;

import java.sql.Date;

public class SubTaskDto {
	private int subTaskId;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private boolean status;
	
	private TaskDto taskDto;
	public SubTaskDto(){}
	public SubTaskDto(int subTaskId, String name, String description,
			Date startDate,Date endDate, boolean status, TaskDto taskDto) {
		this.subTaskId = subTaskId;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.taskDto = taskDto;
	}

	public Date getEndDate() {
		return endDate;
	}
	public TaskDto getTaskDto() {
		return taskDto;
	}

	public void setTaskDto(TaskDto taskDto) {
		this.taskDto = taskDto;
	}

	public int getSubTaskId() {
		return subTaskId;
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
	
	
}
