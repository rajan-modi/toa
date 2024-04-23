package com.tao.taskproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskProjectRepo extends JpaRepository<TaskEntity,Integer> {
    public List<TaskEntity> findByStatus(String status);
}
