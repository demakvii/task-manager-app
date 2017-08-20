package com.techlabs.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sub_tasks")
public class SubTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subtask_id")
	private int subTaskId;

	@Column(name = "subtask_name")
	private String name;
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



	@Column(name = "subtask_descr")
	private String description;
	@Column(name = "subtask_start_date")
	private Date startDate;
	@Column(name = "subtask_end_date")
	private Date endDate;
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	@Column(name = "subtask_status")
	private boolean status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_id")
	private Task taskOwner;

	public SubTask() {

	}

	public SubTask(String name, String description, boolean status,Date startDate,Date endDate) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	

	public void setTaskOwner(Task taskOwner) {
		this.taskOwner = taskOwner;
	}

	public String getName() {
		return name;
	}

	
	public String getDescription() {
		return description;
	}

	
	public boolean getStatus() {
		return status;
	}

	
	public Date getStartDate() {
		return startDate;
	}

	

	public int getSubTaskId() {
		return subTaskId;
	}

	

}
