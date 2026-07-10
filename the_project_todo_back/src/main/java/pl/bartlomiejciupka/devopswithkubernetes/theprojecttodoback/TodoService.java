package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();

    public synchronized List<Todo> listTodos() {
        return List.copyOf(todos);
    }

    public synchronized Todo addTodo(Todo inputTodo) {
        todos.add(inputTodo);
        return inputTodo;
    }
}
