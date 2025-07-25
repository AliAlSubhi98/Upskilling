# Containers & Orchestration

**Goal:** Master containerization with Docker and orchestrate scalable deployments using Kubernetes.

**Current Level:** Level 1

---

## Level 1: Docker Basics
- **Competencies:** Install, Pull, Run, Stop Containers, Volumes, Networks, Docker Compose
- **Commands:** Writing your first Dockerfile, `docker run`, `docker ps`, `docker stop`, `docker rm`, `docker volume`, `docker network`, `docker compose`
- **Checklist:**
  - [ ] Run a Docker container with a simple web app
  - [ ] Bind a volume to a container to persist data
  - [ ] Create a custom Docker network and connect two containers

## Level 2: Advanced Docker
- **Competencies:** Multi-Container Apps, Storage, Container Registries
- **Commands:** `docker push`, `docker tag`, `docker build`, `docker inspect`, caching layer, multi-stage
- **Checklist:**
  - [ ] Write a `docker-compose.yml` for a multi-container application
  - [ ] Push an image to GHCR or Docker Hub
  - [ ] Set up persistent storage using Docker volumes

## Level 3: Container Orchestration
- **Competencies:** Kubernetes Basics, Docker Swarm, Helm
- **Commands:** `kubectl apply`, `kubectl get pods`, `kubectl logs`, Docker Swarm, `helm install`
- **Checklist:**
  - [ ] Deploy an application on Kubernetes using YAML files
  - [ ] Use Helm charts to package and deploy an app
  - [ ] Set up a basic Swarm cluster and deploy a service

## Level 4: Kubernetes Advanced
- **Competencies:** Scaling, Security, Monitoring, Service Mesh
- **Commands:** `kubectl scale`, `kubectl rollout`, `kubectl top`, `istioctl`, `linkerd`, `cilium`
- **Checklist:**
  - [ ] Implement Horizontal Pod Autoscaling (HPA)
  - [ ] Deploy Kubernetes Network Policies
  - [ ] Secure an app with Istio or Linkerd Service Mesh

## Level 5: Enterprise Kubernetes
- **Competencies:** Multi-Cluster, Operators, Virtualization, Performance Tuning
- **Commands:** `kubeadm`, `kubevirt`, custom CRDs, `kubefed`, `cluster-api`
- **Checklist:**
  - [ ] Deploy a Multi-Cluster Kubernetes Federation
  - [ ] Write a Custom Kubernetes Operator
  - [ ] Optimize a large-scale Kubernetes cluster for 1000+ nodes

---

## Resources
- [Docker Docs](https://docs.docker.com/)
- [Kubernetes Docs](https://kubernetes.io/docs/)
- [Awesome Kubernetes](https://github.com/ramitsurana/awesome-kubernetes)

## Personal Notes
