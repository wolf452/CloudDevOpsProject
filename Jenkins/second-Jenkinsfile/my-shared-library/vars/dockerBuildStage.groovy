def call(String dockerImage) {
    stage('Build Docker Image') {
        step {
            echo "Building Docker image: $dockerImage"
            sh "docker build -t $dockerImage ."
        }
    }
}
