package com.task.todo.service;

import com.task.todo.dto.TodoDto;
import com.task.todo.type.TodoType;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    List<TodoDto.Response> getList(TodoType todoType, LocalDate localDate);
    TodoDto.Response addTodo(TodoDto.PostRequest postRequest);
    TodoDto.Response updateTodo(Long todoId, TodoDto.PutRequest putRequest);
    void updateTodoStatus(Long todoId, TodoDto.PatchRequest patchRequest);
    void deleteTodo(Long todoId);
}
