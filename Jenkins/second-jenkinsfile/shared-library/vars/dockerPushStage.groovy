def call() {
    stage('Push Docker Image') {
        steps {
            script {
                echo "Pushing Docker image to Docker Hub"
                sh "docker push $DOCKER_IMAGE"
            }
        }
    }
}
