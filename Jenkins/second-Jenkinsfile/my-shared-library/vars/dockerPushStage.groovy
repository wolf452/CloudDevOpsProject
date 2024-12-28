def call(String dockerImage) {
    stage('Push Docker Image') {
        step {
            echo "Pushing Docker image to Docker Hub"
            sh "docker push $dockerImage"
        }
    }
}
