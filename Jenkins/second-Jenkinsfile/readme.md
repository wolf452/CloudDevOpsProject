
# Jenkins Pipeline Documentation

## Overview
This repository contains a shared Jenkins pipeline library designed to streamline the CI/CD (Continuous Integration/Continuous Delivery) process. It organizes the pipeline stages into reusable, modular Groovy scripts stored in the `vars` directory. These scripts can be easily integrated into any Jenkins pipeline to automate tasks like code checkout, testing, building, containerization, and deployment.

## Pipeline Stages
The shared library includes the following stages for managing the pipeline execution. Each stage is designed as an independent Groovy script to facilitate customization and modularity.

### 1. **Checkout Stage**
The `Checkout` stage is responsible for pulling the source code from a Git repository. It enables version control integration and ensures that the pipeline uses the latest code for the build process.

- **File:** `vars/checkoutStage.groovy`
- **Parameters:**
  - `gitUrl`: URL of the Git repository (default: `https://github.com/wolf452/FinalProjectCode.git`).
  - `gitBranch`: Branch to checkout (default: `main`).

### 2. **Unit Test Stage**
The `Unit Test` stage runs automated unit tests using Gradle to ensure that the code is functioning as expected.

- **File:** `vars/unitTestStage.groovy`

### 3. **Build Stage**
The `Build` stage compiles the source code and creates the project artifacts using Gradle. It is crucial for ensuring the build is successful before proceeding to further stages.

- **File:** `vars/buildStage.groovy`

### 4. **SonarQube Analysis Stage**
The `SonarQube Analysis` stage runs a static code analysis using SonarQube to assess the quality of the code. It helps identify bugs, vulnerabilities, and code smells in the codebase.

- **File:** `vars/sonarQubeStage.groovy`

### 5. **Docker Login Stage**
This stage logs into Docker Hub using the credentials stored in Jenkins, enabling subsequent stages to interact with Docker Hub (e.g., pushing or pulling images).

- **File:** `vars/dockerLoginStage.groovy`

### 6. **Docker Build Stage**
The `Docker Build` stage builds a Docker image from the Dockerfile in the project directory. It packages the application into a container image.

- **File:** `vars/dockerBuildStage.groovy`
- **Parameters:**
  - `dockerImage`: The name of the Docker image to build.

### 7. **Docker Push Stage**
After the Docker image is built, this stage pushes the image to Docker Hub so it can be stored and used for deployment.

- **File:** `vars/dockerPushStage.groovy`
- **Parameters:**
  - `dockerImage`: The name of the Docker image to push.

### 8. **Deploy Stage**
This stage is responsible for deploying the application to a Kubernetes cluster using a Kubernetes deployment YAML file. It allows for seamless deployment in a containerized environment.

- **File:** `vars/deployStage.groovy`
- **Parameters:**
  - `deploymentYaml`: The path to the Kubernetes deployment YAML file.

## Usage
### How to Use the Shared Library in Your Jenkins Pipeline

1. **Add the shared library** to your Jenkins configuration:
   - Go to **Manage Jenkins** > **Configure System**.
   - Scroll to **Global Pipeline Libraries**.
   - Add a new library with the following settings:
     - **Name:** `my-shared-library`
     - **Default version:** Choose the branch name, typically `main`.
     - **Retrieval method:** Modern SCM.
     - **Source Code Management:** Git.
     - **Repository URL:** The URL of your shared library repository.

2. **Create a `Jenkinsfile`** in your project repository and reference the shared library. The pipeline in the `Jenkinsfile` will then execute the defined stages.

```groovy
@Library('my-shared-library') _

pipeline {
    agent { label 'jenkins-slave' }

    environment {
        DOCKER_IMAGE = 'docker.io/ahmedmaher07/project'
        DEPLOYMENT_YAML = 'deployment.yaml'
    }

    stages {
        stage('Checkout') {
            steps {
                checkoutStage(
                    gitUrl: 'https://github.com/wolf452/FinalProjectCode.git',
                    gitBranch: 'main'
                )
            }
        }

        stage('Unit Test') {
            steps {
                unitTestStage()
            }
        }

        stage('Build') {
            steps {
                buildStage()
            }
        }

        stage('SonarQube Analysis') {
            steps {
                sonarQubeStage()
            }
        }

        stage('Login to Docker Hub') {
            steps {
                dockerLoginStage()
            }
        }

        stage('Build Docker Image') {
            steps {
                dockerBuildStage(env.DOCKER_IMAGE)
            }
        }

        stage('Push Docker Image') {
            steps {
                dockerPushStage(env.DOCKER_IMAGE)
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                deployStage(env.DEPLOYMENT_YAML)
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully."
        }
        failure {
            echo "Pipeline failed. Please check the logs for details."
        }
    }
}
```

## Configuration
### Jenkins Shared Library Setup

1. Go to **Manage Jenkins** > **Configure System**.
2. Scroll to **Global Pipeline Libraries**.
3. Add a new library with the following details:
   - **Name:** `my-shared-library`
   - **Default version:** The branch name, typically `main`.
   - **Retrieval method:** Modern SCM.
   - **Source Code Management:** Git.
   - **Repository URL:** The URL of the shared library repository.

### Credentials

Ensure that the necessary Jenkins credentials are configured:

- **Docker Hub credentials:** Create a Docker Hub credential (`Docker_hub`) to log into Docker.
- **SonarQube credentials:** Configure the SonarQube credentials (`sonar`) for code quality analysis.

## Benefits
- **Modularity:** Each stage is implemented in a separate Groovy file, making the pipeline easy to maintain and extend.
- **Reusability:** This library can be reused across different projects, ensuring consistency in CI/CD pipelines.
- **Customization:** Parameters are provided for each stage to allow for flexibility and adaptation to different project requirements.

## Contributing
Contributions are welcome! If you would like to enhance this shared library by adding new stages or improving existing ones, please submit a pull request. Make sure to include tests for any new functionality added.

## License
This project is licensed under the MIT License.
