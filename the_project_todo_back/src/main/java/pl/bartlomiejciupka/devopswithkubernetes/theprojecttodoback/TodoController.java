package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoService.listTodos());
    }

    @PostMapping
    public ResponseEntity<Todo> postTodo(@RequestBody @Valid Todo inputTodo) {
        Todo resTodo = todoService.addTodo(inputTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resTodo);
    }
}
