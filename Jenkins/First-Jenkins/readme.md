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



# Tools Used

## Jenkins:
     - Jenkins is an open-source automation server used primarily for continuous integration and continuous delivery (CI/CD). 
     -  It automates tasks like building code, testing it, and deploying it, and integrates with Terraform and Ansible to create a fully automated infrastructure and deployment pipeline.


###  Terraform:
 - Terraform is an open-source tool by HashiCorp used for managing infrastructure as code (IaC). 
 - It allows users to create, modify, and delete infrastructure resources such as AWS EC2 instances, VPCs, and more.

- Terraform Commands:
    - terraform init: Initializes the working directory containing Terraform configuration files.     
    - terraform plan: Shows the execution plan of changes.
    - terraform apply: Applies the changes to create/update/delete resources.

###  Ansible: 
     - Ansible is an open-source automation tool used for configuration management.
     -  It simplifies IT automation tasks such as configuration management, software installation, and updates.
# Project Structure
 -  GitHub Repository: The repository stores the Terraform and Ansible files. This is where the code resides, and Jenkins will pull it from there.
 - Terraform Files: The Terraform files reside in the terraform/ directory. These files define the infrastructure resources (e.g., EC2 instances, VPCs, Security Groups).
 - Ansible Playbooks: The Ansible Playbooks reside in the ansible/ directory. These files contain the tasks needed to configure the infrastructure, such as installing Docker or configuring network settings.
 - Jenkins Pipeline: The Jenkins Pipeline is defined in the jenkins/ directory. The Jenkinsfile contains all the steps to automate the process, including terraform init, terraform apply, and ansible playbook execution.

### How to Run the Project
      - Set up the environment: Ensure Terraform, Ansible, and Jenkins are properly installed and configured.
      - Set up the Git repository: Store the Terraform and Ansible code in the GitHub repository.
      - Configure Jenkins: Set up the Jenkins Pipeline in Jenkins and ensure it includes steps for Terraform and Ansible.
      - Run the Pipeline: Once the Pipeline is set up, you can trigger it in Jenkins to run the entire automation process.
### Key Features of the Project
        - Complete Automation: The entire process of infrastructure creation and configuration is automated using Terraform and Ansible, with the process orchestrated by Jenkins.
        - Reusability: The Jenkins Shared Library makes it easy to reuse common steps across different projects.
        - Smooth Execution: The Jenkins Pipeline ensures a smooth flow from creating infrastructure to deploying applications, all without manual intervention.
