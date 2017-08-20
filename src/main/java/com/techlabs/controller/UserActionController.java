package com.techlabs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.techlabs.dto.SubTaskDto;
import com.techlabs.dto.TaskDto;
import com.techlabs.dto.UserDto;
import com.techlabs.entity.User;
import com.techlabs.service.TaskManager;

@Controller
public class UserActionController {

	@Autowired
	TaskManager manager;

	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(
			@RequestBody UserDto userDto, HttpServletRequest req,
			HttpServletResponse resp) {
		User user = manager.isValidUser(userDto);
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (user != null) {
			req.getSession().setAttribute("owner", user);
			respMsg.put("success", true);
			respMsg.put("data", "Authenticate Successfull");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Authenticate Unsuccessfull");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTasksList(
			HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> respMsg = new HashMap<String, Object>();
		User owner = (User) req.getSession().getAttribute("owner");
		if (owner != null) {
			respMsg.put("success", true);
			respMsg.put("data", manager.getTaskList(owner.getUserName()));

		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Invalid Session");
		}
		return respMsg;
	}

	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> logout(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (req.getSession(false).getAttribute("owner") != null) {
			req.getSession(false).invalidate();
			respMsg.put("success", true);
			respMsg.put("data", "Successfully Logout");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getTaskById(
			@PathVariable("taskId") int taskId, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession(false).getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			respMsg.put("success", true);
			respMsg.put("data",
					manager.getTaskById(owner.getUserName(), taskId));
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/{taskId}/{subTaskId}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getSubTaskById(
			@PathVariable("taskId") int taskId,
			@PathVariable("subTaskId") int subTaskId, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			respMsg.put("success", true);
			respMsg.put("data", manager.getSubTaskById(owner.getUserName(),
					taskId, subTaskId));
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/addTask", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addTask(
			@RequestBody TaskDto taskDto, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			manager.addTaskToList(owner.getUserName(), taskDto);
			respMsg.put("success", true);

			respMsg.put("data", "Task Added Successfully");

		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/{taskId}/addSubTask", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addSubTask(
			@RequestBody SubTaskDto subTaskDto,
			@PathVariable("taskId") int taskId, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			manager.addSubTaskToList(owner.getUserName(), taskId, subTaskDto);
			respMsg.put("success", true);

			respMsg.put("data", "Task Added Successfully");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteTaskById(
			@PathVariable("taskId") int taskId, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			manager.deleteTaskById(owner.getUserName(), taskId);
			respMsg.put("success", true);

			respMsg.put("data", "Task Deleted Successfully");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/{taskId}/{subTaskId}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> deleteSubTaskById(
			@PathVariable("taskId") int taskId,
			@PathVariable("subTaskId") int subTaskId, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			manager.deleteSubTaskById(owner.getUserName(), taskId, subTaskId);
			respMsg.put("success", true);

			respMsg.put("data", "SubTask Deleted Successfully");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> updateTask(
			@RequestBody TaskDto updateTaskDto,
			@PathVariable("taskId") int taskId, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			manager.updateTask(owner.getUserName(), updateTaskDto);
			respMsg.put("success", true);

			respMsg.put("data", "Task Updated Successfully");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/tasks/{taskId}/{subTaskId}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> updateSubTask(
			@RequestBody SubTaskDto updateSubTask,
			@PathVariable("taskId") int taskId, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			manager.updateSubTask(owner.getUserName(), taskId, updateSubTask);
			respMsg.put("success", true);

			respMsg.put("data", "SubTask Updated Successfully");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

	@RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changePassword(
			@RequestBody UserDto userDto, HttpServletRequest req,
			HttpServletResponse resp) {
		User owner = (User) req.getSession().getAttribute("owner");
		Map<String, Object> respMsg = new HashMap<String, Object>();
		if (owner != null) {
			manager.changePassword(owner.getUserName(), userDto.getPassword());
			req.getSession(false).invalidate();
			respMsg.put("success", true);
			respMsg.put("data", "Password Updated Successfully");
		} else {
			respMsg.put("success", false);
			respMsg.put("data", "Something went wrong");
		}
		return respMsg;
	}

}
