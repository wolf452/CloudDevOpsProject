def checkoutStage(Map config) {
  checkout([
    $class: 'GitSCM', 
    branches: [[name: config.gitBranch]], 
    userRemoteConfigs: [[url: config.gitUrl]]
  ])
}

stage('Checkout') {
  steps {
    checkoutStage(gitBranch: 'main', gitUrl: 'https://github.com/wolf452/CloudDevOpsProject.git')
  }
}
