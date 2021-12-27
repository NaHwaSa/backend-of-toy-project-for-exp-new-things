package com.nahwasa.toy.expnewthings.backend.service;

import com.nahwasa.toy.expnewthings.backend.model.TodoEntity;
import com.nahwasa.toy.expnewthings.backend.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);

        repository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public String testService() {
        TodoEntity entity = new TodoEntity();
        entity.setTitle("study junit");
        repository.save(entity);

        TodoEntity savedEntity = repository.getById(entity.getId());
        return savedEntity.getId() + ", " + savedEntity.getTitle();
    }
}
