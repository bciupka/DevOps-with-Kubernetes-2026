import { queryOptions } from "@tanstack/react-query";
import { getTodos } from "@/features/todos/api";

export const todosQueryKey = ["todos"] as const;

export const todosQueryOptions = queryOptions({
  queryFn: getTodos,
  queryKey: todosQueryKey,
});
