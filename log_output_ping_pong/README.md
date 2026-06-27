# Logoutput + pingpong apps

# 1. [pingpong-app](../ping_pong)
- `docker build -t ping-pong:1.00 ../ping_pong`
- `k3d image import ping-pong:1.00`

# 2. [logoutput-app](../log_output)
- `docker build -t log-output:1.02 ../log_ouptut`
- `k3d image import log-output:1.02`

# 3. Deployment
- `kubectl apply -f manifests`