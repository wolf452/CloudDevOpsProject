stage('Checkout') {
  steps {
    checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/wolf452/CloudDevOpsProject.git']]])
  }
}
