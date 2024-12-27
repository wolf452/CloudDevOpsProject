def call(String dockerImage) {
    stage('Push Docker Image') {
        steps {
            echo "Pushing Docker image to Docker Hub"
            sh "docker push $dockerImage"
        }
    }
}
