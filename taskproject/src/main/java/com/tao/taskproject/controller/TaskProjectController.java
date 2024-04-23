package com.tao.taskproject.controller;

import com.tao.taskproject.repository.TaskEntity;
import com.tao.taskproject.repository.UserEntity;
import com.tao.taskproject.service.TaskProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskProjectController {

    @Autowired
    private TaskProjectService taskProjectService;

    //1 Endpoint: POST /api/tasks
    @PostMapping("/tasks")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity taskEntity){
        return new ResponseEntity<>(taskProjectService.createTask(taskEntity), HttpStatus.CREATED);
    }

    //2 Endpoint: PUT /api/tasks/{taskId}
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("taskId") int taskId, @RequestBody TaskEntity updatedTaskEntity) {
        TaskEntity taskData = taskProjectService.findTaskById(taskId);
        if (Objects.nonNull(taskData)) {
            taskData.setTitle(updatedTaskEntity.getTitle());
            taskData.setDescription(updatedTaskEntity.getDescription());
            taskData.setDueDate(updatedTaskEntity.getDueDate());
            taskData.setStatus(updatedTaskEntity.getStatus());
            return new ResponseEntity<>(taskProjectService.createTask(taskData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //3 Endpoint: DELETE /api/tasks/{taskId}
    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable("taskId") int taskId) {
        taskProjectService.deleteTaskById(taskId);
    }

    //4 Endpoint: Get All Tasks:
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return new ResponseEntity<>(taskProjectService.getAllTasks(), HttpStatus.OK);
    }

    //5 Endpoint: POST /api/tasks/{taskId}/assign
    @PostMapping("/tasks/{taskId}/assign")
    public ResponseEntity<UserEntity> assignTask(@PathVariable("taskId") int taskId, @RequestBody UserEntity userEntity){
        return new ResponseEntity<>(taskProjectService.assignTask(taskId, userEntity), HttpStatus.OK);
    }

    //6 Endpoint: GET /api/users/{userId}/tasks
    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<List<TaskEntity>> createTask(@PathVariable("userId") int userId){
        return new ResponseEntity<>(taskProjectService.getTaskByUserId(userId), HttpStatus.OK);
    }

    //9 Endpoint: GET /api/tasks/status/{status}
    @GetMapping("/tasks/status/{status}")
    public ResponseEntity<List<TaskEntity>> getTaskByStatus(@PathVariable("status") String status){
        return new ResponseEntity<>(taskProjectService.getTaskByStatus(status), HttpStatus.OK);
    }




    //Get All User:
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(taskProjectService.getAllUsers(), HttpStatus.OK);
    }

}
