import z from "zod";

export const todoSchema = z.object({
  id: z.uuid(),
  description: z.string().trim().nonempty("Task cannot be empty"),
});

export const todoCreateSchema = z.object({
  description: z.string().trim().nonempty("Task cannot be empty"),
});

export type Todo = z.infer<typeof todoSchema>;
export type TodoCreate = z.infer<typeof todoCreateSchema>;
