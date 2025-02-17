package com.task.todo.controller;

import com.task.todo.dto.TodoDto;
import com.task.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> getList(){
        return ResponseEntity.ok(todoService.getList());
    }

    @PostMapping
    public ResponseEntity<TodoDto.Response> addTodo(@RequestBody TodoDto.PostRequest postRequest){
        return ResponseEntity.ok(todoService.addTodo(postRequest));
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> updateTodo(
            @PathVariable("todoId") Long todoId,
            @RequestBody TodoDto.PutRequest putRequest){
        return ResponseEntity.ok(todoService.updateTodo(todoId,putRequest));
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
