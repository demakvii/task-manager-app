package com.techlabs.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techlabs.dao.UserDao;
import com.techlabs.dto.SubTaskDto;
import com.techlabs.dto.TaskDto;
import com.techlabs.dto.UserDto;
import com.techlabs.entity.SubTask;
import com.techlabs.entity.Task;
import com.techlabs.entity.User;

@Service
public class TaskManager {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public User isValidUser(UserDto userDto) {
		User user = userDao.getUserByUserName(userDto.getUserName());
		if (user != null) {
			if (user.getPassword().equals(userDto.getPassword()))
				return user;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Set<Task> getTaskList(String userName) {
		return userDao.getUserByUserName(userName).getTaskList();
	}

	@Transactional(readOnly = true)
	public Task getTaskById(String userName, int taskId) {
		Set<Task> taskList = userDao.getUserByUserName(userName).getTaskList();
		Task returnTask = null;
		for (Task task : taskList) {
			if (task.getTaskId() == taskId) {
				returnTask = task;

			}
		}

		return returnTask;
	}

	@Transactional(readOnly = true)
	public SubTask getSubTaskById(String userName, int taskId, int subTaskId) {
		Set<SubTask> subTaskList = getTaskById(userName, taskId)
				.getSubTaskList();
		SubTask returnSubTask = null;
		for (SubTask subTask : subTaskList) {
			if (subTask.getSubTaskId() == subTaskId)
				returnSubTask = subTask;
		}
		return returnSubTask;
	}

	@Transactional
	public void changePassword(String userName,String newPass){
		User owner = userDao.getUserByUserName(userName);
		owner.setPassword(newPass);
	}
	
	@Transactional
	public void addTaskToList(String userName, TaskDto taskDto) {
		User owner = userDao.getUserByUserName(userName);
		Task task = new Task(taskDto.getName(), taskDto.getDescription(),
				taskDto.getStatus(), taskDto.getStartDate(),taskDto.getEndDate());
		task.setOwner(owner);
		(owner.getTaskList()).add(task);
	}

	@Transactional
	public void addSubTaskToList(String userName, int taskId,
			SubTaskDto subTaskDto) {
		SubTask task = new SubTask(subTaskDto.getName(),
				subTaskDto.getDescription(), subTaskDto.getStatus(),
				subTaskDto.getStartDate(),subTaskDto.getEndDate());
		Task taskOwner = getTaskById(userName, taskId);
		task.setTaskOwner(taskOwner);
		taskOwner.getSubTaskList().add(task);
	}

	@Transactional
	public void deleteTaskById(String userName, int taskId) {
		User owner = userDao.getUserByUserName(userName);
		Task task = getTaskById(userName, taskId);
		owner.getTaskList().remove(task);
	}

	@Transactional
	public void deleteSubTaskById(String userName, int taskId, int subTaskId) {
		SubTask subTask = getSubTaskById(userName, taskId, subTaskId);
		Task taskOwner = getTaskById(userName, taskId);
		taskOwner.getSubTaskList().remove(subTask);
	}

	@Transactional
	public void updateTask(String userName, TaskDto taskDto) {
		User owner = userDao.getUserByUserName(userName);
		for (Task task : owner.getTaskList()) {
			if (task.getTaskId() == taskDto.getTaskId()) {
				task.setName(taskDto.getName());
				task.setDescription(taskDto.getDescription());
				task.setStartDate(taskDto.getStartDate());
				task.setEndDate(taskDto.getEndDate());
				task.setStatus(taskDto.getStatus());
			}
		}
	}

	@Transactional
	public void updateSubTask(String userName, int taskId, SubTaskDto subTaskDto) {
		Task taskOwner = getTaskById(userName, taskId);
		for (SubTask subTask : taskOwner.getSubTaskList()) {
			if (subTask.getSubTaskId() == subTaskDto.getSubTaskId()) {
			subTask.setName(subTaskDto.getName());
			subTask.setDescription(subTaskDto.getDescription());
			subTask.setStartDate(subTaskDto.getStartDate());
			subTask.setEndDate(subTaskDto.getEndDate());
			subTask.setStatus(subTaskDto.getStatus());
		}
		}

	}

}
