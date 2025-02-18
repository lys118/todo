package com.task.todo.controller;

import com.task.todo.dto.TodoDto;
import com.task.todo.service.TodoService;
import com.task.todo.type.TodoType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "할일 조회",
            description = "todoType이 general일경우 localhost는 작성x today일 경우는 작성필요" +
                    "general은 평소에 할일 조회 today는 일자별로 할일 조회")
    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> getList(
            @RequestParam("todoType") TodoType todoType,
            @RequestParam(value = "localDate", required = false, defaultValue = "") String localDateStr) {
        LocalDate localDate = localDateStr.isEmpty() ? null : LocalDate.parse(localDateStr);
        return ResponseEntity.ok(todoService.getList(todoType,localDate));
    }

    @Operation(summary = "할일 작성",
            description = "title, content는 필수값 작성이므로 todoSatus는 기본값으로 TODO" +
                    "todoType은 평소인지 날짜인지에 따라서 GENERAL,TODAY로 구분")
    @PostMapping
    public ResponseEntity<TodoDto.Response> addTodo(@Valid @RequestBody TodoDto.PostRequest postRequest){
        return ResponseEntity.ok(todoService.addTodo(postRequest));
    }

    @Operation(summary = "할일 내용수정",
            description = "title, content는 필수값 작성")
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> updateTodo(
            @PathVariable("todoId") Long todoId,
            @Valid @RequestBody TodoDto.PutRequest putRequest){
        return ResponseEntity.ok(todoService.updateTodo(todoId,putRequest));
    }
    @Operation(summary = "할일 상태변경",
            description = "할일 id와 바꿀상태를 가져와서 수정합니다.TODO,DONE")
    @PatchMapping("/{todoId}")
    public ResponseEntity<String> updateStatusTodo(
            @PathVariable("todoId") Long todoId,
            @Valid @RequestBody TodoDto.PatchRequest patchRequest){
        todoService.updateTodoStatus(todoId,patchRequest);
        return ResponseEntity.ok("todoStatus update success");
    }
    @Operation(summary = "할일 삭제",
            description = "할일 id로 삭제합니다.")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("todo delete success");
    }
}
