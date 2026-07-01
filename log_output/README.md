# Log output app

## Build images

- `docker build -t log-output-reader:1.00 log_output_reader`
- `docker build -t log-output-writer:1.00 log_output_writer`

## Import images to k3d

- `k3d image import log-output-reader:1.00`
- `k3d image import log-output-writer:1.00`

## Deploy

- `kubectl apply -f manifests`

App is available at `/`.
