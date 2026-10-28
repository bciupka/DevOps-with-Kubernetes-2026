package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.dto.TodoCreateDto;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.dto.TodoDto;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodos() {
        return ResponseEntity.ok(todoService.listTodos());
    }

    @PostMapping
    public ResponseEntity<TodoDto> postTodo(@RequestBody @Valid TodoCreateDto inputTodoDto) {
        log.info("Received todo: {}", inputTodoDto.description());
        TodoDto resTodoDto = todoService.addTodo(inputTodoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resTodoDto);
    }
}
