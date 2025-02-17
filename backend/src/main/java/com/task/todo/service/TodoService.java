package com.task.todo.service;

import com.task.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto.Response> getList();
    TodoDto.Response addTodo(TodoDto.PostRequest postRequest);
    TodoDto.Response updateTodo(Long todoId, TodoDto.PutRequest putRequest);
    void updateTodoStatus(Long todoId, TodoDto.PatchRequest patchRequest);
    void deleteTodo(Long todoId);
}
