def call() {
    stage('Build') {
        step {
            echo "Building the project with Gradle"
            sh "./gradlew build"
        }
    }
}
