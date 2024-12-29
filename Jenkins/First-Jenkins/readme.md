
# CloudDevOpsProject

This project automates the provisioning and configuration of infrastructure using **Terraform**, **Ansible**, and **Jenkins**. It offers a flexible, scalable pipeline for deploying development environments (e.g., EC2 instances on AWS) and configuring them using open-source tools.

## High-Level Overview

1. **Terraform**: Used to provision infrastructure resources like AWS EC2 instances.
2. **Ansible**: Configures the provisioned infrastructure by automating software installation and configurations.
3. **Jenkins**: Orchestrates the entire process, automating code checkout from GitHub, Terraform commands, and Ansible configurations.

## Project Workflow

### 1. **GitHub Checkout**
Jenkins pulls the latest code from the GitHub repository:
```groovy
git branch: 'main', url: 'https://github.com/username/CloudDevOpsProject.git'
```

### 2. **Terraform Initialization**
Jenkins initializes the Terraform working directory:
```groovy
terraformInit()
```

### 3. **Terraform Plan**
Jenkins generates a Terraform execution plan:
```groovy
terraformPlan()
```
This plan previews the changes (like creating EC2 instances) that Terraform will apply.

### 4. **Terraform Apply**
Terraform provisions EC2 instances on AWS:
```groovy
terraformApply()
```

### 5. **Inventory File**
Terraform generates an inventory file containing the EC2 instance IP addresses and SSH details:
```
[aws_instances]
18.204.194.253 ansible_user=ubuntu
```

### 6. **Run Ansible**
Jenkins triggers Ansible to configure the newly provisioned EC2 instances:
```bash
ansible-playbook playbook.yml -i inventory --private-key /path/to/ssh_key -u ubuntu -e "ansible_ssh_extra_args='-o StrictHostKeyChecking=no'"
```

### 7. **Results and Logs**
Jenkins logs the results of the Ansible playbook execution, providing real-time feedback in the Jenkins UI.

---

## Jenkins Shared Library for Terraform and Ansible Pipelines

This repository includes a shared Jenkins library designed to automate infrastructure provisioning and configuration using **Terraform** and **Ansible**. The library simplifies pipeline creation by providing pre-built, reusable stages.

### Key Features
- **Modularized Stages**: Separate stages for each pipeline step (e.g., Terraform init, plan, apply, Ansible playbook run).
- **Terraform Integration**: Seamlessly runs Terraform commands to initialize, plan, and apply infrastructure.
- **Ansible Integration**: Easily configures infrastructure using Ansible playbooks.
- **Reusability**: Stages are parameterized and can be reused across multiple projects.

---

## Folder Structure

```
(root)
├── vars/
│   ├── checkoutStage.groovy         # Git checkout logic
│   ├── terraformInitStage.groovy    # Initializes Terraform
│   ├── terraformPlanStage.groovy    # Generates Terraform plan
│   ├── terraformApplyStage.groovy   # Applies Terraform changes
│   ├── delayBeforeAnsibleStage.groovy # Delay before running Ansible
│   ├── ansibleStage.groovy          # Runs Ansible playbook
└── resources/                       # Optional static resources
```

---

## How to Use

### Step 1: Add the Shared Library to Jenkins
1. Go to **Manage Jenkins** > **Configure System**.
2. Scroll to **Global Pipeline Libraries**.
3. Add the library:
   - **Name**: `my-shared-library`
   - **Default Version**: `main` (or desired branch/tag)
   - **Source Code Management**: Git
   - **Repository URL**: `<Your Git Repository URL>`

### Step 2: Reference the Library in Your Jenkinsfile
In your project repository, reference the shared library in your `Jenkinsfile`:
```groovy
@Library('my-shared-library') _

pipeline {
    agent any

    stages {
        stage('Example Stage') {
            steps {
                echo 'Using the shared library!'
            }
        }
    }
}
```

---

## Example Pipeline

```groovy
@Library('my-shared-library') _

pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID = credentials('aws-access-key')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-key')
        AWS_REGION = 'us-east-1'
    }

    stages {
        stage('Checkout') {
            steps {
                checkoutStage(gitBranch: 'main', gitUrl: 'https://github.com/username/CloudDevOpsProject.git')
            }
        }

        stage('Terraform Init') {
            steps {
                terraformInitStage(terraformDir: 'terraform')
            }
        }

        stage('Terraform Plan') {
            steps {
                terraformPlanStage(terraformDir: 'terraform')
            }
        }

        stage('Terraform Apply') {
            steps {
                terraformApplyStage(terraformDir: 'terraform')
            }
        }

        stage('Delay') {
            steps {
                delayBeforeAnsibleStage(delayMinutes: 2)
            }
        }

        stage('Run Ansible') {
            steps {
                ansibleStage(
                    ansibleDir: 'ansible',
                    inventoryFile: 'inventory',
                    ansibleCredentialsId: 'ansible-ssh',
                    ansiblePlaybook: 'playbook.yml'
                )
            }
        }
    }
}
```

---

## Configuration

Make sure the following Jenkins credentials are configured:
- **AWS Credentials**:
  - `aws-access-key`
  - `aws-secret-key`
- **Ansible SSH Credentials**:
  - `ansible-ssh`

---

## Tools Used

### Jenkins
- Automates the entire pipeline, from code checkout to deployment.

### Terraform
- Manages infrastructure as code, provisioning resources like EC2 instances on AWS.

### Ansible
- Automates configuration management, ensuring infrastructure is properly set up and configured.

---

## Contributing

1. Fork the repository.
2. Create a new branch (`feature/my-feature`).
3. Commit changes.
4. Open a pull request.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---
