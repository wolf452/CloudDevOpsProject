// vars/sonarQubeStage.groovy
def call() {
    withSonarQubeEnv('token-sonar') {
        echo "Running SonarQube analysis"
        sh "./gradlew sonarqube --stacktrace"
    }
}
