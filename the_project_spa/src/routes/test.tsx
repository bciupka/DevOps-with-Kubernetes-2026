import { createFileRoute } from "@tanstack/react-router";

export const Route = createFileRoute("/test")({ component: Test });

function Test() {
  return <h1 className="m-6 bg-amber-300 font-bold text-center">TEST</h1>;
}
