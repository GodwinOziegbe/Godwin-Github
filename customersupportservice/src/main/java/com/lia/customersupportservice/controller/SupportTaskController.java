package com.lia.customersupportservice.controller;

import com.lia.customersupportservice.Entities.Customer;
import com.lia.customersupportservice.Entities.Priority;
import com.lia.customersupportservice.Entities.Status;
import com.lia.customersupportservice.Entities.SupportTask;
import com.lia.customersupportservice.services.CustomerService;
import com.lia.customersupportservice.services.SupportTaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class SupportTaskController {


    private SupportTaskService supportTaskService;
    private CustomerService customerService;

    @GetMapping("GET/api/v1/tasks")
    public List<SupportTask> fetchAllTasks() {

        return supportTaskService.getAllTasks();
    }

    @GetMapping("GET/api/v1/tasks/customerId")
    public List<SupportTask> fetchCustomerTasks(@RequestParam String customerId) {

        return supportTaskService.getMyTasks(customerId);
    }

    @PostMapping("POST/api/v1/task")
    public String addingTask(@RequestParam String comment, String customerId, String priorityString, String statusString) {
        Status status;
        Priority priority;
        Customer customer = new Customer();

        for (Customer value : customerService.getAllCustomers()) {
            if (customerId.equals(value.getId())) {
                customer = value;
                break;
            }
        }
        if (comment.length() > 1000) {
            return "comment should not be more than 100 characters";
        }
        if (customer.getId() == null) {
            return "customer Id not found!";
        }
        priorityString = priorityString.toUpperCase();
        statusString = statusString.toUpperCase();
        priority = Priority.valueOf(priorityString);
        status = Status.valueOf(statusString);
        SupportTask supportTask = new SupportTask(comment, customer, priority, status);

        return supportTaskService.addTask(supportTask);
    }

    @PutMapping("UPDATE/api/v1/task")
    public String updatingTask(@RequestParam String id, String comment, String priorityString, String statusString) {

        if (comment.length() > 1000) {
            return "comment should not be more than 1000 characters";
        } else {

            return supportTaskService.updateTask(id, comment, priorityString, statusString);

        }

    }

    @DeleteMapping("DELETE/api/v1/tasks/customerId")
    public String deletingTask(@RequestParam String customerId) {
        return supportTaskService.deleteMyTasks(customerId);
    }
}


