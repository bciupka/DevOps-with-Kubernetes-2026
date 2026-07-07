import { Input } from "@/components/ui/input.tsx";
import { Button } from "@/components/ui/button.tsx";
import { Item, ItemContent, ItemGroup } from "@/components/ui/item.tsx";

function App() {
  const todos: string[] = [
    "Learn Kubernetes",
    "Deploy app",
    "Configure volumes",
  ];

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
        <form onSubmit={(e) => e.preventDefault()} className="flex gap-2 my-3">
          <Input placeholder="Enter a new todo" />
          <Button>Send</Button>
        </form>
        <h2 className="font-bold my-5 text-2xl">Todos</h2>
        <ItemGroup className="w-xl gap-2">
          {todos.map((i) => {
            return (
              <Item
                variant="outline"
                className="border-l-6 border-primary hover:bg-muted"
              >
                <ItemContent>{i}</ItemContent>
              </Item>
            );
          })}
        </ItemGroup>
      </div>
    </main>
  );
}

export default App;
