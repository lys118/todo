package com.task.todo.service;

import com.task.todo.dto.TodoDto;
import com.task.todo.entity.Todo;
import com.task.todo.repository.TodoRepository;
import com.task.todo.type.TodoType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TodoDto.Response> getList(TodoType todoType, LocalDate localDate) {
        return todoRepository.findByTodoTypeAndLocalDate(todoType,localDate)
                .stream().map(todo -> modelMapper.map(todo, TodoDto.Response.class)
        ).toList();
    }

    //할일 추가
    @Transactional
    @Override
    public TodoDto.Response addTodo(TodoDto.PostRequest postRequest) {
        Todo todo = modelMapper.map(postRequest, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo,TodoDto.Response.class);
    }

    //내용 수정
    @Transactional
    @Override
    public TodoDto.Response updateTodo(Long todoId,TodoDto.PutRequest putRequest) {
        Todo todo = todoRepository.findById(todoId).orElseThrow();
        todo.updateTitle(putRequest.getTitle());
        todo.updateContent(putRequest.getContent());

        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo,TodoDto.Response.class);
    }

    //상태 수정
    @Transactional
    @Override
    public void updateTodoStatus(Long todoId,TodoDto.PatchRequest patchRequest) {
        Todo todo = todoRepository.findById(todoId).orElseThrow();
        todo.updateTodoStatus(patchRequest.getTodoStatus());
        todoRepository.save(todo);
    }

    //할일 삭제
    @Transactional
    @Override
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow();
        todoRepository.delete(todo);
    }
}
