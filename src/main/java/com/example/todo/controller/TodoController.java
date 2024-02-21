package com.example.todo.controller;

import java.util.ArrayList;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

   @Autowired
   public TodoH2Service service;

   @GetMapping("/todos")
   public ArrayList<Todo> getTodos() {
      return service.getTodos();
   }

   @GetMapping("/todos/{id}")
   public Todo getTodoById(@PathVariable("id") int id) {
      return service.getTodoById(id);
   }

   @PostMapping("/todos")
   public Todo addTodo(@RequestBody Todo todos) {
      return service.addTodo(todos);
   }

   @PutMapping("/todos/{id}")
   public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todos) {
      return service.updateTodo(id, todos);
   }

   @DeleteMapping("/todos/{id}")
   public void deleteTodo(@PathVariable("id") int id) {
      service.deleteTodo(id);
   }
}
