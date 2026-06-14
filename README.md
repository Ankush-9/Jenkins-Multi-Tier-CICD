# Multi-Tier Application CI/CD Pipeline on AWS EKS & RDS

A production-grade, fully automated CI/CD pipeline engineered to deploy a full-stack Spring Boot application onto a multi-tier AWS infrastructure. This project demonstrates modern DevSecOps practices, emphasizing secure network isolation, containerization, managed orchestration, and programmatic continuous deployment.

---

## [1] Architecture Overview

The infrastructure is designed with high availability, security, and tight network boundaries in mind:

* **Custom VPC Layout:** Network segregation using a dedicated VPC (`multi-tier-cicd-vpc`) split across public and private subnets over multiple Availability Zones (`ap-south-1`).
* **Secure Multi-Tier Isolation:**
  * **Public Tier:** Houses the public-facing Jenkins automation server and an AWS Internet Gateway (IGW) paired with a NAT Gateway (NGW).
  * **Private Tier:** Encompasses the Amazon EKS Worker Nodes and the Amazon RDS MySQL instance, ensuring core business logic and state components remain entirely inaccessible from the public internet.
* **Traffic Routing:** NAT Gateway translation allows worker nodes to pull container images securely, while strict AWS Security Group policies restrict inbound database traffic solely to authorized infrastructure components.

---

## [2] Tech Stack & System Components

* **Orchestration & Compute:** Amazon EKS (Kubernetes), AWS EC2
* **CI/CD Automation:** Jenkins (Pipeline-as-Code)
* **Containerization:** Docker, Docker Hub
* **Database Management:** Amazon RDS (MySQL)
* **Build Tooling:** Apache Maven (Java 17 runtime)
* **Infrastructure Utilities:** AWS CLI, `kubectl`

---

## [3] Pipeline Workflow

The Jenkins declarative pipeline automates the entire software delivery lifecycle upon every source code revision:

> [Source Control] -> [Maven Build] -> [Docker Push] -> [EKS Rolling Update]

1. **Checkout Stage:** Sources the latest code tracking from the main application repository branch.
2. **Build Stage:** Compiles and packages the Spring Boot application using Maven, executing within a defined Java 17 local environment.
3. **Docker Build & Push:** Micro-containerizes the application artifact and pushes the tagged immutable image to Docker Hub using securely managed credentials.
4. **Deploy to EKS:**
   * Authenticates context against the remote AWS EKS Cluster using programmatic IAM execution rights (`aws-eks-creds`).
   * Applies declarative Kubernetes manifests (`k8s/`) to spin up cluster deployments.
   * Injects runtime secrets (such as the RDS credential payload) dynamically via `kubectl set env` without hardcoding sensitive configurations.
   * Triggers an active rolling update and monitors deployment health status (`rollout status`).

---

## [4] Deployment Verification

Upon pipeline success, the application is exposed securely via a Kubernetes LoadBalancer Service. The live deployment can be accessed publicly through the generated external URL footprint:

> http://<aws-load-balancer-url>/crm/cxlist
