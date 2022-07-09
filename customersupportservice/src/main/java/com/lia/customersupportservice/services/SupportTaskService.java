package com.lia.customersupportservice.services;

import com.lia.customersupportservice.Entities.Priority;
import com.lia.customersupportservice.Entities.Status;
import com.lia.customersupportservice.Entities.SupportTask;
import com.lia.customersupportservice.Repository.SupportTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SupportTaskService {

    private SupportTaskRepository supportTaskRepository;

    public List<SupportTask> getAllTasks() {
        return supportTaskRepository.findAll();
    }

    public List<SupportTask> getMyTasks(String customerId) {
        return supportTaskRepository.findSupportTaskByCustomer_Id(customerId);
    }

    public String addTask(SupportTask supportTask) {
        supportTaskRepository.save(supportTask);
        return "success";
    }


    public String updateTask(String id, String comment, String priorityString, String statusString) {
        Status status;
        Priority priority;
        for (SupportTask value : supportTaskRepository.findAll()) {
            if (id.equals(value.getId())) {
                priorityString = priorityString.toUpperCase();
                statusString = statusString.toUpperCase();
                priority = Priority.valueOf(priorityString);
                status = Status.valueOf(statusString);
                value.setComment(comment);
                value.setPriority(priority);
                value.setStatus(status);

            supportTaskRepository.save(value);
                return "success";


            }
        }
        return "support task Id not found";
    }

    public String deleteMyTasks(String customerId) {
        int count = 0;
        for (SupportTask value : supportTaskRepository.findAll()) {
            if (customerId.equals(value.getCustomer().getId())) {
                supportTaskRepository.delete(value);
                count++;
            }
        }
        if (count > 0) {//checks to see if customer id exits in the system
            return "all tasks deleted for customer id: " + customerId;
        } else {
            return "customer id not found!";
        }
    }
}