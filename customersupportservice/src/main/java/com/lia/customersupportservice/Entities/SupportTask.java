package com.lia.customersupportservice.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SupportTask {

    @Id
    private String id;
    private String comment;
    private Customer customer;
    private Priority priority;
    private Status status;


    public SupportTask(String comment, Customer customer, Priority priority, Status status) {
        this.comment = comment;
        this.customer = customer;
        this.priority = priority;
        this.status = status;
    }

    public SupportTask(String id, String comment, Priority priority, Status status) {
        this.id = id;
        this.comment = comment;
        this.priority = priority;
        this.status = status;
    }

    public SupportTask() {
    }


}
