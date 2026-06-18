# Log output app

- `docker build -t log-output:1.01 .`
- `k3d image import log-output:1.01`
- `kubectl create deployment logoutput-dep --image=log-output:1.01`