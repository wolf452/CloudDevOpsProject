
def call() {
    stage('Unit Test') {
        steps {
            echo "Running unit tests"
            sh "chmod +x gradlew"
            sh "./gradlew test"
        }
    }
}
