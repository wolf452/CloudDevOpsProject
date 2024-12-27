def call(String dockerImage) {
    stage('Build Docker Image') {
        steps {
            echo "Building Docker image: $dockerImage"
            sh "docker build -t $dockerImage ."
        }
    }
}
