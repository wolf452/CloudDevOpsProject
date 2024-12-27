def call(String dockerImage) {
    echo "Pushing Docker image to Docker Hub"
    sh "docker push $dockerImage"
}
