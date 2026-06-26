# Log output app

- `docker build -t log-output:1.02 .`
- `k3d image import log-output:1.02`
- `kubectl apply -f manifests`

App would be available at localhost:XXXX/