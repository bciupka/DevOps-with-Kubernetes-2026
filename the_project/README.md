# The project

- `docker build -t the-project:1.00 .`
- `k3d image import the-project:1.00`
- `kubectl create deployment theproject-dep --image the-project:1.00`

For changing PORT, on which the web server runs - `kubectl set env deployment/theproject-dep PORT=XXXX`