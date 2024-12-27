def call() {
    echo "Building Docker image"
    sh "docker build -t $DOCKER_IMAGE ."
}
