package com.tao.taskproject.service;

import com.tao.taskproject.repository.TaskEntity;
import com.tao.taskproject.repository.TaskProjectRepo;
import com.tao.taskproject.repository.UserEntity;
import com.tao.taskproject.repository.UserProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskProjectService{

    @Autowired
    private UserProjectRepo userProjectRepo;

    @Autowired
    private TaskProjectRepo taskProjectRepo;

    public TaskEntity createTask(TaskEntity taskEntity) {
        return taskProjectRepo.save(taskEntity);
    }

    public TaskEntity findTaskById(int taskId){
        Optional<TaskEntity> taskEntity = taskProjectRepo.findById(taskId);
        if(taskEntity.isPresent()){
            return taskEntity.get();
        }
        return null;
    }

    public void deleteTaskById(int taskId){
        taskProjectRepo.deleteById(taskId);
    }

    public List<TaskEntity> getAllTasks() {
        return taskProjectRepo.findAll();
    }

    public UserEntity assignTask(int taskId, UserEntity user){
        TaskEntity taskEntity = taskProjectRepo.findById(taskId).get();
        Optional<UserEntity> updateUser = userProjectRepo.findById(1);//(user.getUserId());
        if(updateUser.isPresent()){
            updateUser.get().setTask(taskEntity);
            userProjectRepo.save(updateUser.get());
        }
        return updateUser.get();
    }

    public List<TaskEntity> getTaskByUserId(int userId){

        return null;
    }

    public List<TaskEntity> getTaskByStatus(String status){
        return taskProjectRepo.findByStatus(status);
    }

    public List<UserEntity> getAllUsers() {
        return userProjectRepo.findAll();
    }

}
