package com.task.todo.dto;

import com.task.todo.type.TodoStatus;
import com.task.todo.type.TodoType;
import lombok.Getter;

import java.time.LocalDate;

public class TodoDto {
    @Getter
    public static class GetRequest{
        private TodoType todoType;
        private LocalDate localDate;
    }
    @Getter
    public static class Response{
        private Long id;
        private String title;
        private String content;
        private TodoStatus todoStatus;
        private TodoType todoType;
    }
    @Getter
    public static class PostRequest{
        private String title;
        private String content;
        private TodoType todoType;
        private LocalDate localDate;
    }

    @Getter
    public static class PutRequest{
        private String title;
        private String content;
    }

    @Getter
    public static class PatchRequest{
        private TodoStatus todoStatus;
    }
}
