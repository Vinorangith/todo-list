package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoH2Service implements TodoRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Todo> getTodos() {
        List<Todo> todoList = db.query("select * from todolist", new TodoRowMapper());
        ArrayList<Todo> list = new ArrayList<>(todoList);
        return list;
    }

    @Override
    public Todo getTodoById(int id) {
        try {
            Todo todos = db.queryForObject("select * from todolist where id=?", new TodoRowMapper(), id);
            return todos;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo todos) {
        db.update("insert into todolist(todo,status,priority) values(?,?,?)", todos.getTodo(),
                todos.getStatus(), todos.getPriority());
        Todo savedTodo = db.queryForObject("select * from todolist where todo=? and status=? and priority=?",
                new TodoRowMapper(), todos.getTodo(), todos.getStatus(), todos.getPriority());
        return savedTodo;
    }

    @Override
    public Todo updateTodo(int id, Todo todos) {
        if (todos.getTodo() != null) {
            db.update("update todolist set todo = ? where id = ?", todos.getTodo(), id);
        }
        if (todos.getStatus() != null) {
            db.update("update todolist set status = ? where id = ?", todos.getStatus(), id);
        }
        if (todos.getPriority() != null) {
            db.update("update todolist set priority = ? where id = ?", todos.getPriority(), id);
        }
        return getTodoById(id);
    }

    @Override
    public void deleteTodo(int id) {
        db.update("delete from todolist where id=?", id);
    }

}
