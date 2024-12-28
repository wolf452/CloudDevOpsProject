def call() {
    stage('Unit Test') {
        step {
            echo "Running unit tests"
            sh "chmod +x gradlew"
            sh "./gradlew test"
        }
    }
}
