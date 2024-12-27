def call(Map params) {
    stage('Checkout') {
        steps {
            echo "Cloning the source code from GitHub"
            git url: params.gitUrl ?: 'https://github.com/wolf452/FinalProjectCode.git', branch: params.gitBranch ?: 'main'
        }
    }
}
