# Log output + Ping-pong apps

## Build images

- `docker build -t log-output-reader:1.01 ../log_output/log_output_reader`
- `docker build -t log-output-writer:1.01 ../log_output/log_output_writer`
- `docker build -t ping-pong:1.01 ../ping_pong`

## Import images to k3d

- `k3d image import log-output-reader:1.01`
- `k3d image import log-output-writer:1.01`
- `k3d image import ping-pong:1.01`

## Prepare persistent volume

- `docker exec k3d-k3s-default-agent-0 mkdir -p /tmp/kube`
- `kubectl apply -f infrastructure`

## Deploy

- `kubectl apply -f manifests`

## Endpoints

- `/` - log output with ping-pong count
- `/pingpong` - ping-pong counter
