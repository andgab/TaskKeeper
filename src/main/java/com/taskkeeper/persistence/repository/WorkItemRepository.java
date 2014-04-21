package com.taskkeeper.persistence.repository;

import com.taskkeeper.persistence.domain.WorkItem;

import org.springframework.data.repository.CrudRepository;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
  WorkItem findById(Long id);

}