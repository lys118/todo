package com.task.todo.entity;

import com.task.todo.type.TodoStatus;
import com.task.todo.type.TodoType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @Enumerated(EnumType.STRING)
    private TodoType todoType;

    private LocalDate localDate;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }
}
