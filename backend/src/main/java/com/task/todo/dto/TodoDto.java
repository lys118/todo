package com.task.todo.dto;

import com.task.todo.type.TodoStatus;
import com.task.todo.type.TodoType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

public class TodoDto {

    @Getter
    public static class Response{
        private Long id;
        private String title;
        private String content;
        private TodoStatus todoStatus;
        private TodoType todoType;
        private LocalDate localDate;
    }
    @Getter
    public static class PostRequest{
        @NotEmpty(message = "Title is required")
        @Size(max = 20, message = "Title must be less than 20 characters")
        private String title;

        @NotEmpty(message = "Content is required")
        @Size(max = 100, message = "Content must be less than 100 characters")
        private String content;

        @NotNull(message = "TodoStatus is required")
        private TodoStatus todoStatus;

        @NotNull(message = "TodoType is required")
        private TodoType todoType;
        private LocalDate localDate;
    }

    @Getter
    public static class PutRequest{
        @NotEmpty(message = "Title is required")
        @Size(max = 20, message = "Title must be less than 20 characters")
        private String title;
        @NotEmpty(message = "Content is required")
        @Size(max = 100, message = "Content must be less than 100 characters")
        private String content;
    }

    @Getter
    public static class PatchRequest{
        @NotNull(message = "TodoStatus is required")
        private TodoStatus todoStatus;
    }
}
