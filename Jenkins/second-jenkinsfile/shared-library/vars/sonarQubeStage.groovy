

def call() {
    stage('SonarQube Analysis') {
        steps {
            withSonarQubeEnv('sonar') { 
                echo "Running SonarQube analysis"
                sh "./gradlew sonarqube"
            }
        }
    }
}
