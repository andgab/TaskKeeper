package com.taskkeeper.persistence.repository;

import com.taskkeeper.persistence.domain.WorkItemComment;

import org.springframework.data.repository.CrudRepository;

public interface WorkItemCommentRepository extends CrudRepository<WorkItemComment, Long> {
	WorkItemComment findById(Long id);

}