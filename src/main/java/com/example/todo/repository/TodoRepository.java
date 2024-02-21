package com.example.todo.repository;

import java.util.ArrayList;

import com.example.todo.model.Todo;

public interface TodoRepository {

    ArrayList<Todo> getTodos();

    Todo getTodoById(int id);

    Todo addTodo(Todo todos);

    Todo updateTodo(int id, Todo todos);

    void deleteTodo(int id);
}
