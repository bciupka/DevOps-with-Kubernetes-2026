import { Button } from "@/components/ui/button.tsx";

function App() {
  return (
    <main className="min-h-screen bg-background text-foreground">
      <div className="mx-auto flex min-h-screen max-w-5xl items-center px-6">
        <h1 className="text-3xl font-semibold">The Project</h1>
        <Button className="mx-2">Test</Button>
      </div>
    </main>
  );
}

export default App;
