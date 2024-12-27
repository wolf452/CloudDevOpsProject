
# Jenkins Pipeline Documentation

## Overview
This repository contains a shared library for Jenkins pipelines, organized to streamline CI/CD processes. Each pipeline stage is implemented as an independent script inside the `vars` directory, allowing for modularity and reusability.

## Pipeline Stages
Below is an overview of the stages included in the shared library:

1. **Checkout Stage**  
   Clones the source code from a specified Git repository.
   - **File:** `vars/checkoutStage.groovy`
   - **Parameters:**
     - `gitUrl`: URL of the Git repository (default: `https://github.com/wolf452/FinalProjectCode.git`).
     - `gitBranch`: Branch to checkout (default: `main`).

2. **Unit Test Stage**  
   Runs unit tests using Gradle.
   - **File:** `vars/unitTestStage.groovy`

3. **Build Stage**  
   Builds the project using Gradle.
   - **File:** `vars/buildStage.groovy`

4. **SonarQube Analysis Stage**  
   Runs SonarQube analysis to assess code quality.
   - **File:** `vars/sonarQubeStage.groovy`

5. **Docker Login Stage**  
   Logs into Docker Hub using Jenkins credentials.
   - **File:** `vars/dockerLoginStage.groovy`

6. **Docker Build Stage**  
   Builds a Docker image.
   - **File:** `vars/dockerBuildStage.groovy`
   - **Parameters:**
     - `dockerImage`: Name of the Docker image to build.

7. **Docker Push Stage**  
   Pushes the built Docker image to Docker Hub.
   - **File:** `vars/dockerPushStage.groovy`
   - **Parameters:**
     - `dockerImage`: Name of the Docker image to push.

8. **Deploy Stage**  
   Deploys the application to a Kubernetes cluster using a deployment YAML file.
   - **File:** `vars/deployStage.groovy`
   - **Parameters:**
     - `deploymentYaml`: Path to the Kubernetes deployment YAML file.

## Usage
### Using the Pipeline in Your Project
To use this shared library in your Jenkins pipeline:

1. Add the shared library to your Jenkins configuration (see [Configuration](#configuration)).
2. Create a `Jenkinsfile` in your project repository.
3. Use the shared library and call the stages as shown below:

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
### Jenkins Shared Library
1. Go to **Manage Jenkins** > **Configure System**.
2. Scroll to **Global Pipeline Libraries**.
3. Add a new library:
   - **Name:** `my-shared-library`
   - **Default version:** Branch name (e.g., `main`).
   - **Retrieval method:** Modern SCM.
   - **Source Code Management:** Git.
   - **Repository URL:** URL of the shared library repository.

### Credentials
- Ensure Jenkins credentials for Docker Hub (`Docker_hub`) and SonarQube (`sonar`) are configured in Jenkins.

## Benefits
- **Modularity:** Each stage is in its own file, allowing for easy updates and maintenance.
- **Reusability:** The library can be reused across multiple projects.
- **Flexibility:** Parameters make it easy to customize behavior per project.

## Contributing
Feel free to contribute to this shared library by adding new stages or improving existing ones. Create a pull request with your changes and ensure all stages are tested.

## License
This project is licensed under the MIT License.

