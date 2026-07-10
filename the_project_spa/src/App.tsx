import { useQuery } from "@tanstack/react-query";
import { todosQueryOptions } from "@/features/todos/queries";
import { Spinner } from "@/components/ui/spinner";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Item, ItemContent, ItemGroup } from "@/components/ui/item";
import { Controller, useForm } from "react-hook-form";
import { type Todo, todoSchema } from "@/features/todos/schema";
import { zodResolver } from "@hookform/resolvers/zod";
import { Field, FieldError } from "@/components/ui/field";
import { useCreateTodoMutation } from "@/features/todos/mutations";

function App() {
  const form = useForm<Todo>({
    resolver: zodResolver(todoSchema),
    defaultValues: {
      desc: "",
    },
  });

  const createTodoMutation = useCreateTodoMutation();

  function onSubmit(todo: Todo) {
    createTodoMutation.mutate(todo, {
      onSuccess: () => form.reset(),
    });
  }

  const { data: todos, isPending, isError } = useQuery(todosQueryOptions);
  if (isPending)
    return (
      <main className="h-screen w-screen text-center flex justify-center items-center">
        <Spinner className="size-20 text-primary" />
      </main>
    );
  if (isError) return <h1>Problem</h1>;

  return (
    <main className="min-h-screen bg-background text-foreground">
      <div className="flex flex-col items-center justify-center min-h-screen">
        <h1 className="text-3xl font-semibold">Todo App</h1>
        <img
          src="/api/image"
          alt="Random Image"
          width={200}
          height={200}
          className="rounded-2xl my-5"
        />
        <form
          onSubmit={form.handleSubmit(onSubmit)}
          className="flex gap-2 my-3 w-lg"
        >
          <Controller
            name="desc"
            control={form.control}
            render={({ field, fieldState }) => (
              <Field data-invalid={fieldState.invalid}>
                <Input
                  {...field}
                  aria-invalid={fieldState.invalid}
                  placeholder="Enter a new todo"
                />
                <div className="h-4">
                  <FieldError className="text-xs" errors={[fieldState.error]} />
                </div>
              </Field>
            )}
          />
          <Button type="submit" disabled={createTodoMutation.isPending}>
            Send
          </Button>
        </form>
        <h2 className="font-bold my-5 text-2xl">Todos</h2>
        <ItemGroup className="w-xl gap-2">
          {todos.map((todo, index) => {
            return (
              <Item
                key={index}
                variant="outline"
                className="border-l-6 border-primary hover:bg-muted"
              >
                <ItemContent>{todo.desc}</ItemContent>
              </Item>
            );
          })}
        </ItemGroup>
      </div>
    </main>
  );
}

export default App;
