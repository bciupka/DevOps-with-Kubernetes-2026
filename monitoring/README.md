# Monitoring

Run the commands from this directory. Helm and a running cluster are required.

## Add Helm repositories

- `helm repo add prometheus-community https://prometheus-community.github.io/helm-charts`
- `helm repo add grafana https://grafana.github.io/helm-charts`
- `helm repo update`

## Install charts

- `helm upgrade --install prom prometheus-community/prometheus --namespace monitoring --create-namespace 
--values prom-values.yaml`
- `helm upgrade --install loki grafana/loki --namespace monitoring --values loki-values.yaml`
- `helm upgrade --install k8smon grafana/k8s-monitoring --namespace monitoring --values k8smon-values.yaml`
- `helm upgrade --install grafana grafana/grafana --namespace monitoring --values grafana-values.yaml`

## Grafana

- `kubectl get pods --namespace monitoring`
- `kubectl port-forward --namespace monitoring svc/grafana 3000:80`

Open http://localhost:3000 and log in with `admin` / `admin`.

In Explore, select Loki and query `{namespace="project"}` for project logs.
