def call(Map config = [:]) {
    stage('Checkout') {
        step {
            git branch: config.gitBranch ?: 'main', url: config.gitUrl ?: 'https://github.com/wolf452/CloudDevOpsProject.git'
        }
    }
}
