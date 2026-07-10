import { useMutation, useQueryClient } from "@tanstack/react-query";
import { createTodo } from "@/features/todos/api";
import { todosQueryKey } from "@/features/todos/queries";

export function useCreateTodoMutation() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: createTodo,
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: todosQueryKey,
      }),
  });
}
