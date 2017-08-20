package com.techlabs.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "task_id")
	private int taskId;
	@Column(name = "task_name")
	private String name;
	@Column(name = "task_descr")
	private String description;
	@Column(name = "task_start_date")
	private Date startDate;
	@Column(name = "task_end_date")
	private Date endDate;


	@Column(name = "task_status")
	private boolean status;

	
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_name")
	private User owner;

	@OneToMany(mappedBy = "taskOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval=true)
	private Set<SubTask> subTasks;

	public Task() {

	}

	public Task(String name, String description, boolean status, Date startDate,Date endDate) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public boolean getStatus() {
		return status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<SubTask> getSubTaskList() {
		return subTasks;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}	
	
}
