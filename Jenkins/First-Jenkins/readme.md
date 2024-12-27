

# Jenkins Shared Library for Terraform and Ansible Pipelines

This repository contains a shared library for Jenkins to manage infrastructure and configuration automation using **Terraform** and **Ansible**. Each stage of the pipeline is modularized into separate files for better maintainability and reusability.

## Table of Contents

- [Introduction](#introduction)
- [Folder Structure](#folder-structure)
- [Features](#features)
- [How to Use](#how-to-use)
- [Pipeline Stages](#pipeline-stages)
  - [Checkout Stage](#checkout-stage)
  - [Terraform Init Stage](#terraform-init-stage)
  - [Terraform Plan Stage](#terraform-plan-stage)
  - [Terraform Apply Stage](#terraform-apply-stage)
  - [Delay Before Ansible Stage](#delay-before-ansible-stage)
  - [Run Ansible Stage](#run-ansible-stage)
- [Configuration](#configuration)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

---

## Introduction

This shared library simplifies the creation of Jenkins pipelines by modularizing the process of provisioning infrastructure with Terraform and configuring it with Ansible. It provides pre-built stages for common tasks.

---

## Folder Structure

The library uses the following structure:

```
(root)
├── vars/
│   ├── checkoutStage.groovy         # Handles the Git checkout
│   ├── terraformInitStage.groovy    # Initializes Terraform
│   ├── terraformPlanStage.groovy    # Plans Terraform changes
│   ├── terraformApplyStage.groovy   # Applies Terraform changes
│   ├── delayBeforeAnsibleStage.groovy # Adds a delay before running Ansible
│   ├── ansibleStage.groovy          # Runs Ansible playbook
└── resources/                       # (Optional) Static resources for the library
```

---

## Features

- **Modularized Stages**: Each pipeline stage is implemented as a separate Groovy script.
- **Terraform Integration**: Automatically initializes, plans, and applies Terraform configurations.
- **Ansible Integration**: Runs Ansible playbooks with customizable inventory and SSH credentials.
- **Reusable and Configurable**: Easily reusable across different projects with parameterized stages.

---

## How to Use

### Step 1: Add the Shared Library to Jenkins
1. Go to **Manage Jenkins** > **Configure System**.
2. Scroll to **Global Pipeline Libraries**.
3. Add a new library:
   - **Name**: `my-shared-library`
   - **Default Version**: `main` (or the branch/tag you use)
   - **Retrieval Method**: Modern SCM
   - **Source Code Management**: Git
   - **Repository URL**: `<Your Git Repository URL>`

### Step 2: Use the Library in a Jenkinsfile
In your project repository, reference the library in your `Jenkinsfile`:

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

## Pipeline Stages

### Checkout Stage
**File**: `checkoutStage.groovy`

**Description**: Clones the specified branch from a Git repository.

**Parameters**:
- `gitBranch` (default: `main`): Branch to clone.
- `gitUrl`: URL of the Git repository.

**Example Usage**:
```groovy
checkoutStage(
    gitBranch: 'main',
    gitUrl: 'https://github.com/username/repository.git'
)
```

---

### Terraform Init Stage
**File**: `terraformInitStage.groovy`

**Description**: Initializes the Terraform working directory.

**Parameters**:
- `terraformDir` (default: `terraform`): Directory containing Terraform files.

**Example Usage**:
```groovy
terraformInitStage(
    terraformDir: 'terraform'
)
```

---

### Terraform Plan Stage
**File**: `terraformPlanStage.groovy`

**Description**: Generates and displays an execution plan for Terraform.

**Parameters**:
- `terraformDir` (default: `terraform`): Directory containing Terraform files.

**Example Usage**:
```groovy
terraformPlanStage(
    terraformDir: 'terraform'
)
```

---

### Terraform Apply Stage
**File**: `terraformApplyStage.groovy`

**Description**: Applies the Terraform configuration.

**Parameters**:
- `terraformDir` (default: `terraform`): Directory containing Terraform files.

**Example Usage**:
```groovy
terraformApplyStage(
    terraformDir: 'terraform'
)
```

---

### Delay Before Ansible Stage
**File**: `delayBeforeAnsibleStage.groovy`

**Description**: Adds a delay before running the Ansible playbook.

**Parameters**:
- `delayMinutes` (default: `1`): Number of minutes to wait.

**Example Usage**:
```groovy
delayBeforeAnsibleStage(
    delayMinutes: 2
)
```

---

### Run Ansible Stage
**File**: `ansibleStage.groovy`

**Description**: Executes an Ansible playbook.

**Parameters**:
- `ansibleDir` (default: `ansible`): Directory containing the playbook.
- `inventoryFile` (default: `inventory`): Path to the inventory file.
- `ansibleCredentialsId`: Jenkins credential ID for SSH.
- `ansiblePlaybook` (default: `playbook.yml`): Path to the playbook file.

**Example Usage**:
```groovy
ansibleStage(
    ansibleDir: 'ansible',
    inventoryFile: 'inventory',
    ansibleCredentialsId: 'ansible-ssh',
    ansiblePlaybook: 'playbook.yml'
)
```

---

## Configuration

Set the required environment variables and credentials in your Jenkinsfile or pipeline settings. Ensure the following credentials are configured in Jenkins:

- **AWS Credentials**:
  - `aws-access-key`
  - `aws-secret-key`
- **Ansible SSH Credentials**:
  - `ansible-ssh`

---

## Examples

Here is a complete example of a pipeline using the shared library:

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
                checkoutStage(
                    gitBranch: 'main',
                    gitUrl: 'https://github.com/username/repository.git'
                )
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

## Contributing

To contribute:
1. Fork the repository.
2. Create a new branch (`feature/my-feature`).
3. Commit your changes.
4. Open a pull request.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---
