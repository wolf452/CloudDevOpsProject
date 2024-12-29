# CloudDevOpsProject

# Project Overview

This project utilizes Jenkins pipelines to automate infrastructure provisioning, application deployment, and integration using Terraform, Ansible, Docker, Jenkins, Git, OpenShift CLI (oc), SonarQube, and PostgreSQL. It consists of two main pipelines orchestrated through Jenkins:

## Project Diagram

<img src="https://github.com/saeedkouta/MultiCloudDevOpsProject/assets/167209058/f3fad849-c43d-4630-bb15-50102cc850d0.svg" >

---

# **-tasks**



# 1.Version Control and Repository Management:

-**Created a GitHub repository (CloudDevOpsProject) for centralized collaboration, initialized with a README file, and managed version control for all deliverables.**

---

# 2.Containerization with Docker:

-**Developed a Dockerfile to containerize the application, ensuring portability and consistency across environments.**

---

# 3.Infrastructure Provisioning with Terraform:

-**Automated the provisioning of AWS resources, including VPC, Subnets, Security Groups, and EC2 instances, using modular Terraform scripts for scalability and reusability.**

-**Configured S3 for Terraform backend state management and integrated CloudWatch for application monitoring.**

---

# 4.Configuration Management with Ansible:

-**Created Ansible playbooks with roles to configure EC2 instances, including the installation of Docker, Java, Jenkins, and SonarQube, and setting up necessary environment variables.**

---

# 5.CI/CD Pipeline with Jenkins:

-**Designed a Jenkins pipeline using a Jenkinsfile, incorporating stages for Git checkout, unit testing, JAR building, SonarQube code analysis, Docker image building, image pushing to a registry, and deploying the application on OpenShift.**

-**Leveraged shared Jenkins libraries and Jenkins slaves for modularity and efficiency.**

---

# 6:Documentation:

-**Provided comprehensive documentation, including setup instructions, architecture diagrams, AWS integration details, and troubleshooting guidelines.**
