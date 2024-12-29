# CloudDevOpsProject

This project aims to automate infrastructure setup and configuration using **Terraform**, **Ansible**, and **Jenkins**. The goal is to provide a flexible tool to deploy development environments, such as EC2 instances on AWS, and configure them using open-source tools.

## High-Level Idea

1. **Terraform**: Used to provision infrastructure on AWS .
2. **Ansible**: After Terraform provisions the infrastructure, Ansible is used to configure the instances.
3. **Jenkins**: Automates the entire process through the pipeline, starting from checking out the code from GitHub, running Terraform to provision the infrastructure, and then triggering Ansible to configure the systems.

## Project Workflow

### 1. **GitHub Checkout**
The first step involves Jenkins pulling the code from the GitHub repository using the following command:
```groovy
git branch: 'main', url: 'https://github.com/wolf452/CloudDevOpsProject.git
```
### 2. Terraform Initialization
After the code is checked out, Jenkins initializes Terraform using the following command:
```groovy
terraformInit()
```
This initializes the Terraform working directory and connects to the provider (AWS) to create the infrastructure.

### 3. Terraform Plan
Jenkins then creates a plan using Terraform:

```groovy
terraformPlan()
```
This step shows the preview of changes that will be applied to the environment, such as creating EC2 instances.

### 4. Terraform Apply
At this stage, the changes defined in the plan are applied with the command:

```groovy
terraformApply()
```
Terraform provisions EC2 instances on AWS based on the configuration defined in the Terraform files.

### 5. Inventory File from Terraform
After the EC2 instances are created, Terraform generates an inventory file that contains the IP addresses of the EC2 instances, along with connection details like the username (usually "ubuntu"). The inventory file might look like this:
```
[aws_instances]
18.204.194.253 ansible_user=ubuntu
```

### 6. Run Ansible
After the infrastructure is provisioned, Jenkins triggers Ansible to configure the instances using the playbook.yml file:

```bash

ansible-playbook playbook.yml -i inventory --private-key /var/lib/jenkins/workspace/Iaac-ansible/ansible/ssh15462748033567534218.key -u ubuntu -e "ansible_ssh_extra_args='-o StrictHostKeyChecking=no'"
```
The playbook.yml file contains a set of tasks to execute on the EC2 instances, such as:

   - Installing packages (e.g., Git, Docker, Java).
   - Setting up services (e.g., Jenkins, SonarQube).
  - Ensuring services like PostgreSQL and Docker are running.

### 7. Checking the Results
After Ansible completes its execution, Jenkins displays the results in the Jenkins UI, logging any changes or issues encountered during execution.


# Jenkins Shared Library for Terraform and Ansible Pipelines
This repository contains a shared library for Jenkins to manage infrastructure and configuration automation using Terraform and Ansible. Each stage of the pipeline is modularized into separate files for better maintainability and reusability

- Table of Contents
   - Introduction
   - Folder Structure
   - Features
   - How to Use
   - Pipeline Stages
           - Checkout Stage
           - Terraform Init Stage
           - Terraform Plan Stage
           - Terraform Apply Stage
           - Delay Before Ansible Stage
           - Run Ansible Stage
           - Configuration
Examples
Contributing
