package com.task.todo.repository;

import com.task.todo.entity.Todo;
import com.task.todo.type.TodoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    @Query("SELECT t FROM Todo t WHERE t.todoType = :todoType AND (:localDate IS NULL OR t.localDate = :localDate)")
    List<Todo> findByTodoTypeAndLocalDate(
            @Param("todoType") TodoType todoType,
            @Param("localDate") LocalDate localDate);

}
