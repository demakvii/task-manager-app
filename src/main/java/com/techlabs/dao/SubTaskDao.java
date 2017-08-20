package com.techlabs.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.techlabs.entity.SubTask;
import com.techlabs.entity.Task;

@Repository
public interface SubTaskDao {
	public Set<SubTask> getSubTaskList(Task ownerTask);

	public void addSubTaskToList(Task ownerTask,SubTask subTask);

	public void deleteSubTaskById(int subTaskId);

	public SubTask getSubTaskById(Task ownerTask,int subTaskId);

	public void updateSubTask(SubTask updateSubTask);
}
