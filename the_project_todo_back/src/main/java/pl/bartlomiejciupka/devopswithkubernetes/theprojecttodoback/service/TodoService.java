package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.service;

import org.springframework.stereotype.Service;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.dto.TodoCreateDto;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.dto.TodoDto;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.entity.Todo;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.repository.TodoRepo;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<TodoDto> listTodos() {
        List<Todo> todos = todoRepo.findAll();
        return todos.stream().map(this::mapToDto).toList();
    }

    public TodoDto addTodo(TodoCreateDto inputTodoDto) {
        Todo newTodo = new Todo(inputTodoDto.description());
        Todo saved = todoRepo.save(newTodo);
        return new TodoDto(saved.getId(), saved.getDescription());
    }

    private TodoDto mapToDto(Todo todo) {
        return new TodoDto(todo.getId(), todo.getDescription());
    }
}
