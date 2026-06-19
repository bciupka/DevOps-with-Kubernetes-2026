# The project

- `docker build -t the-project:1.00 .`
- `k3d image import the-project:1.00`
- `kubectl apply -f manifests/deployment.yaml`

For changing PORT, on which the web server runs, change value of an `env` named `PORT` in `manifests/deployment.yaml` 