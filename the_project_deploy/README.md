# The project app

## Build images

- `docker build -t the-project:1.00 ../the_project`
- `docker build -t the-project-spa:1.00 ../the_project_spa`
- `docker build -t the-project-todo-back:1.00 ../the_project_todo_back`

## Import images to k3d

- `k3d image import the-project:1.00`
- `k3d image import the-project-spa:1.00`
- `k3d image import the-project-todo-back:1.00`

## Prepare infrastructure

- `kubectl apply -f namespace.yaml`
- `docker exec k3d-k3s-default-agent-0 mkdir -p /tmp/project/kube`
- `kubectl apply -f infrastructure`

## Deploy

- `kubectl apply -f manifests`

## Endpoints

- `/` - frontend
- `/api/image` - cached image
- `GET /api/todos` - list todos
- `POST /api/todos` - create todo

Cronjob adds a todo every hour.

## Monitoring

Follow the [monitoring setup](../monitoring/README.md) to install the charts and access Grafana.

The backend logs received todos and rejects descriptions longer than 140 characters with HTTP 400.
