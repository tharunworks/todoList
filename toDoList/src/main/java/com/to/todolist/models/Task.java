package com.to.todolist.models;

import com.to.todolist.mappers.JpaJsonConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TASK_DETAILS")
    @Convert(converter = JpaJsonConverter.class)
    private Map<String, String> taskDetails;

    @Column(name = "IS_COMPLETED")
    private boolean completed;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    @CreationTimestamp
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @UpdateTimestamp
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, String> getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(Map<String, String> taskDetails) {
        this.taskDetails = taskDetails;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Task() {
        //Do Nothing
    }
}
