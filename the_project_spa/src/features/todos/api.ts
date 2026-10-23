import { type Todo, type TodoCreate } from "@/features/todos/schema";
import { apiClient } from "@/lib/api-client";

export async function getTodos(): Promise<Todo[]> {
  const res = await apiClient.get<Todo[]>("/todos");
  return res.data;
}

export async function createTodo(todo: TodoCreate): Promise<Todo> {
  const res = await apiClient.post<Todo>("/todos", todo);
  return res.data;
}
