package com.lia.customersupportservice.Repository;

import com.lia.customersupportservice.Entities.SupportTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface SupportTaskRepository extends MongoRepository<SupportTask, String> {

    List<SupportTask> findSupportTaskByCustomer_Id(String customerId);
}
