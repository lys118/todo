package com.task.todo.dto;

import lombok.Getter;

public class TodoDto {
    @Getter
    public static class Request{
        private String id;
        private String title;
        private String content;
    }
}
