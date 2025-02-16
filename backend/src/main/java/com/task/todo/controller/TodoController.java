package com.task.todo.controller;

import com.task.todo.dto.TodoDto;
import com.task.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> getList(){
        return ResponseEntity.ok(todoService.getList());
    }

    @PostMapping
    public ResponseEntity<String> addTodo(@RequestBody TodoDto.PostRequest postRequest){
        todoService.addTodo(postRequest);
        return ResponseEntity.ok("todo add success");
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<String> updateTodo(
            @PathVariable("todoId") Long todoId,
            @RequestBody TodoDto.PutRequest putRequest){
        todoService.updateTodo(todoId,putRequest);
        return ResponseEntity.ok("todo update success");
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<String> updateStatusTodo(
            @PathVariable("todoId") Long todoId,
            @RequestBody TodoDto.PatchRequest patchRequest){
        todoService.updateTodoStatus(todoId,patchRequest);
        return ResponseEntity.ok("todoStatus update success");
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("todo delete success");
    }
}
