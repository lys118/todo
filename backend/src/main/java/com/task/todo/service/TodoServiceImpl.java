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
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;
    //공통처리
    private Todo findById(Long todoId){
        return todoRepository.findById(todoId).orElseThrow(
                () -> new NoSuchElementException(todoId+"를 찾을 수 없습니다")
        );
    }
    //조회
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
        Todo todo = findById(todoId);
        todo.updateTitle(putRequest.getTitle());
        todo.updateContent(putRequest.getContent());

        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo,TodoDto.Response.class);
    }

    //상태 수정
    @Transactional
    @Override
    public void updateTodoStatus(Long todoId,TodoDto.PatchRequest patchRequest) {
        Todo todo = findById(todoId);
        todo.updateTodoStatus(patchRequest.getTodoStatus());
        todoRepository.save(todo);
    }

    //할일 삭제
    @Transactional
    @Override
    public void deleteTodo(Long todoId) {
        Todo todo = findById(todoId);
        todoRepository.delete(todo);
    }
}
