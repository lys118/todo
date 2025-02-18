package com.task.todo.service;

import com.task.todo.dto.TodoDto;
import com.task.todo.entity.Todo;
import com.task.todo.repository.TodoRepository;
import com.task.todo.type.TodoStatus;
import com.task.todo.type.TodoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TodoServiceImpl todoService;

    //Type이 GENERAL일 경우 조회 테스트
    @Test
    void testGetListWithGeneralType() {
        //given
        TodoType todoType = TodoType.GENERAL;
        LocalDate localDate = null;
        Todo todo = Todo.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(todoType)
                .localDate(localDate)
                .build();
        TodoDto.Response response = TodoDto.Response.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(todoType)
                .localDate(localDate)
                .build();

        when(todoRepository.findByTodoTypeAndLocalDate(
                todoType,localDate)).thenReturn(List.of(todo));

        when(modelMapper.map(any(Todo.class), eq(TodoDto.Response.class)))
                .thenReturn(response);

        //when
        List<TodoDto.Response> result = todoService.getList(todoType,localDate);

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(todoRepository, times(1)).findByTodoTypeAndLocalDate(todoType, localDate);
    }

    //Type이 TODAY일 경우 조회 테스트
    @Test
    void testGetListWithTodayType() {
        //given
        TodoType todoType = TodoType.TODAY;
        LocalDate localDate = LocalDate.now();
        Todo todo = Todo.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(todoType)
                .localDate(localDate)
                .build();
        TodoDto.Response response = TodoDto.Response.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(todoType)
                .localDate(localDate)
                .build();
        when(todoRepository.findByTodoTypeAndLocalDate(
                todoType,localDate)).thenReturn(List.of(todo));

        when(modelMapper.map(any(Todo.class), eq(TodoDto.Response.class)))
                .thenReturn(response);

        //when
        List<TodoDto.Response> result = todoService.getList(todoType,localDate);

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(todoRepository, times(1)).findByTodoTypeAndLocalDate(todoType, localDate);
    }

    @Test
    void addTodo() {
        //given
        TodoDto.PostRequest postRequest = TodoDto.PostRequest.builder()
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        Todo targetTodo = Todo.builder()
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        Todo savedTodo = Todo.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        TodoDto.Response response = TodoDto.Response.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();

        when(modelMapper.map(postRequest,Todo.class)).thenReturn(targetTodo);
        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);
        when(modelMapper.map(savedTodo, TodoDto.Response.class)).thenReturn(response);

        //when
        TodoDto.Response result = todoService.addTodo(postRequest);

        //then
        assertNotNull(result);
        assertEquals(postRequest.getTitle(), result.getTitle());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void testUpdateTodo() {
        // given
        TodoDto.PutRequest putRequest = TodoDto.PutRequest.builder()
                .title("new title")
                .content("new content")
                .build();
        Todo targetTodo = Todo.builder()
                .id(1L)
                .title("Target title")
                .content("Target content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        Todo savedTodo = Todo.builder()
                .id(1L)
                .title("new title")
                .content("new content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        TodoDto.Response response = TodoDto.Response.builder()
                .id(1L)
                .title("new title")
                .content("new content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        when(todoRepository.findById(1L)).thenReturn(Optional.of(targetTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);
        when(modelMapper.map(savedTodo, TodoDto.Response.class)).thenReturn(response);

        // when
        TodoDto.Response result = todoService.updateTodo(1L, putRequest);

        // then
        assertNotNull(result);
        assertEquals("new title", result.getTitle());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void testUpdateTodoStatus() {
        //given
        Todo todo = Todo.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        TodoDto.PatchRequest patchRequest= TodoDto.PatchRequest.builder()
                 .todoStatus(TodoStatus.DONE)
                 .build();
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        //when
        todoService.updateTodoStatus(1L,patchRequest);

        //then
        assertEquals(TodoStatus.DONE, todo.getTodoStatus());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void testDeleteTodo() {
        //given
        Todo todo = Todo.builder()
                .id(1L)
                .title("Test title")
                .content("Test content")
                .todoStatus(TodoStatus.TODO)
                .todoType(TodoType.GENERAL)
                .localDate(null)
                .build();
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        //when
        todoService.deleteTodo(1L);

        //then
        verify(todoRepository, times(1)).delete(todo);

    }
}