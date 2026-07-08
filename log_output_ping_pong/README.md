# Log output + Ping-pong apps

## Build images

- `docker build -t log-output-reader:1.00 ../log_output/log_output_reader`
- `docker build -t log-output-writer:1.00 ../log_output/log_output_writer`
- `docker build -t ping-pong:1.00 ../ping_pong`

## Import images to k3d

- `k3d image import log-output-reader:1.00`
- `k3d image import log-output-writer:1.00`
- `k3d image import ping-pong:1.00`

## Infrastructure

Do not apply `infrastructure` for this exercise.

## Deploy

- `kubectl apply -f manifests`

## Endpoints

- `/` - log output with ping-pong count
- `/pingpong` - ping-pong counter
