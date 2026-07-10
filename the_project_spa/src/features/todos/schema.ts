import z from "zod";

export const todoSchema = z.object({
  desc: z.string().trim().nonempty("Task cannot be empty"),
});

export type Todo = z.infer<typeof todoSchema>;
