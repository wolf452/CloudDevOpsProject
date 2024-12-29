
# CloudDevOpsProject

This project automates infrastructure setup and configuration using **Terraform**, **Ansible**, and **Jenkins**. The objective is to provide a flexible tool to deploy development environments, such as EC2 instances on AWS, and configure them using open-source tools.

## High-Level Idea

1. **Terraform**: Used to provision infrastructure on AWS.
2. **Ansible**: After Terraform provisions the infrastructure, Ansible is used to configure the instances.
3. **Jenkins**: Automates the entire process through a pipeline, starting from checking out the code from GitHub, running Terraform to provision the infrastructure, and then triggering Ansible to configure the systems.

## Project Workflow

### 1. **GitHub Checkout**
Jenkins pulls the code from the GitHub repository using the following command:
```groovy
git branch: 'main', url: 'https://github.com/wolf452/CloudDevOpsProject.git'
```

### 2. **Terraform Initialization**
Jenkins initializes Terraform using the following command:
```groovy
terraformInit()
```
![init-terr-out](https://github.com/user-attachments/assets/2a500b8d-0f95-4a3b-aad2-feb32d424440)


### 3. **Terraform Plan**
Jenkins creates a plan using Terraform:
```groovy
terraformPlan()
```
This shows a preview of changes that will be applied to the environment, such as creating EC2 instances.

![plan-terr-2](https://github.com/user-attachments/assets/1aa88b5f-7aa0-44af-a338-c725f5a2bcdd)

### 4. **Terraform Apply**
Terraform applies the changes defined in the plan to provision EC2 instances on AWS:
```groovy
terraformApply()
```
![apply-terr-3](https://github.com/user-attachments/assets/fb1514a0-7509-438c-8e98-17ee48e5776a)

### 5. **Inventory File from Terraform**
After the EC2 instances are created, Terraform generates an inventory file containing IP addresses and connection details, like:
```
[aws_instances]
18.204.194.253 ansible_user=ubuntu
```
![inventory](https://github.com/user-attachments/assets/91778313-5745-4fca-ab4a-4a1b538de60b)

### 6. **Run Ansible**
Jenkins triggers Ansible to configure the instances with the playbook:
```bash
ansible-playbook playbook.yml -i inventory --private-key /var/lib/jenkins/workspace/Iaac-ansible/ansible/ssh15462748033567534218.key -u ubuntu -e "ansible_ssh_extra_args='-o StrictHostKeyChecking=no'"

```
![ansible-1](https://github.com/user-attachments/assets/a9386981-50a8-41f5-8711-5a3e2c3c8c81)
![ansible-2](https://github.com/user-attachments/assets/40ab0252-df8e-4672-b7c0-edf372f83bd4)
![ansible-3](https://github.com/user-attachments/assets/e0053c03-3dd3-4cf6-9e6d-f5ae29648428)
![ansible-4](https://github.com/user-attachments/assets/f3dce60a-7564-4b7f-b4fe-87c5651d6c37)
![1-out](https://github.com/user-attachments/assets/f47b7a1a-ff7e-4e02-aadc-ed7feb283c43)

### 7. **Check the Results**
After Ansible completes execution, Jenkins displays the results in the Jenkins UI, logging any changes or issues encountered.

## Tools Used

### 1. Jenkins
- An open-source automation server used for continuous integration and continuous delivery (CI/CD).
- Automates tasks like building code, testing it, and deploying it, integrating Terraform and Ansible to create a fully automated infrastructure and deployment pipeline.

### 2. Terraform
- An open-source tool used for managing infrastructure as code (IaC).
- Enables the creation, modification, and deletion of infrastructure resources like AWS EC2 instances, VPCs, and more.
- **Terraform Commands**:
  - `terraform init`: Initializes the working directory with configuration files.
  - `terraform plan`: Previews changes to be made.
  - `terraform apply`: Applies the changes to provision or update resources.

### 3. Ansible
- An open-source automation tool for configuration management.
- Automates IT tasks like software installation and configuration.
  
## Project Structure

- **GitHub Repository**: Stores the Terraform and Ansible code.
- **Terraform Files**: Located in the `terraform/` directory to define infrastructure resources.
- **Ansible Playbooks**: Located in the `ansible/` directory to define tasks for configuring the infrastructure.
- **Jenkins Pipeline**: Defined in the `jenkins/` directory with all necessary steps for automation (e.g., `terraform init`, `terraform apply`, `ansible-playbook`).

## How to Run the Project

1. **Set up the environment**:
   - Ensure Terraform, Ansible, and Jenkins are installed.
   
2. **Set up the Git repository**:
   - Store the Terraform and Ansible code in a GitHub repository.
   
3. **Configure Jenkins**:
   - Set up the Jenkins Pipeline with steps for Terraform and Ansible.
   
4. **Run the Pipeline**:
   - Trigger the Jenkins pipeline to automate the infrastructure provisioning and configuration process.

## Key Features

- **Complete Automation**: The entire process is automated, from infrastructure creation to configuration, ensuring minimal manual intervention.
- **Reusability**: Common steps can be reused through the Jenkins Shared Library.
- **Smooth Execution**: The Jenkins pipeline ensures a seamless flow from infrastructure creation to application deployment.

## Future Enhancements

- Add support for multiple cloud providers.
- Implement more complex configuration management with Ansible.
- Add error handling and notification systems to the Jenkins pipeline.

---
