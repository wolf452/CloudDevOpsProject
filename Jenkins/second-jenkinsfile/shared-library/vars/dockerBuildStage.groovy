// dockerBuildStage.groovy
def call(String dockerImage) {
    echo "Building Docker image: $dockerImage"
    sh "docker build -t $dockerImage ."
}
