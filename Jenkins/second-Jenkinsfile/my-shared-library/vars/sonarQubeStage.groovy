def call() {
    stage('SonarQube Analysis') {
        step {
            withSonarQubeEnv('sonar') {
                echo "Running SonarQube analysis"
                sh "./gradlew sonarqube"
            }
        }
    }
}
