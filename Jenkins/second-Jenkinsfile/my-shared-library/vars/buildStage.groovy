def call() {
    stage('Build') {
        steps {
            echo "Building the project with Gradle"
            sh "./gradlew build"
        }
    }
}
