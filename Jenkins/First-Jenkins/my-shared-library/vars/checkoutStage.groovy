def call(Map config = [:]) {
    stage('Checkout') {
        steps {
            git branch: config.gitBranch ?: 'main', url: config.gitUrl ?: 'https://github.com/wolf452/FinalProjectCode.git'
        }
    }
}
