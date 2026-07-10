import { type Todo } from "@/features/todos/schema";
import { apiClient } from "@/lib/api-client";

export async function getTodos(): Promise<Todo[]> {
  const res = await apiClient.get<Todo[]>("/todos");
  return res.data;
}

export async function createTodo(todo: Todo): Promise<Todo> {
  const res = await apiClient.post<Todo>("/todos", todo);
  return res.data;
}
