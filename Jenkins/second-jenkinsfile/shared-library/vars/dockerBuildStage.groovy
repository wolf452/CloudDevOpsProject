def call() {
    stage('Build Docker Image') {
        steps {
            echo "Building Docker image: $DOCKER_IMAGE"
            sh "docker build -t $DOCKER_IMAGE ."
        }
    }
}
