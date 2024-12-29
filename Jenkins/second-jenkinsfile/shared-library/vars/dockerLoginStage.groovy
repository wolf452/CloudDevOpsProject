f call() {
    stage('Login to Docker Hub') {
        steps {
            script {
                withCredentials([usernamePassword(credentialsId: 'Docker_hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                }
            }
        }
    }
}
