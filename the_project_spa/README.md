# The project

- `docker build -t the-project:1.01 .`
- `k3d image import the-project:1.01`
- `kubectl apply -f manifests/deployment.yaml -f manifests/service.yaml`

App runs on NodePort 30080