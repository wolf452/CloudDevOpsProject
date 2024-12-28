def call(Map config = [:]) {
    pipeline {
        agent any
        environment {
            TERRAFORM_DIR = 'terraform'
            BACKEND_DIR = 'terraform'
            ANSIBLE_DIR = 'ansible'
            INVENTORY_FILE = 'inventory'
            AWS_ACCESS_KEY_ID = credentials('aws-access-key')  
            AWS_SECRET_ACCESS_KEY = credentials('aws-secret-key')  
            AWS_REGION = 'us-east-1'
        }
        stages {
            stage('Checkout') {
                step {
                    git branch: config.gitBranch ?: 'main', url: config.gitUrl ?: 'https://github.com/wolf452/CloudDevOpsProject.git'
                }
            }
        }
    }
}
