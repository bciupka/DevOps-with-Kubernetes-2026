function App() {
  return (
    <main className="min-h-screen bg-background text-foreground">
      <div className="flex flex-col items-center justify-center min-h-screen">
        <h1 className="text-3xl font-semibold my-5">Todo App</h1>
        <img
          src="/api/image"
          alt="Random Image"
          width={600}
          height={600}
          className="rounded-2xl"
        />
      </div>
    </main>
  );
}

export default App;
