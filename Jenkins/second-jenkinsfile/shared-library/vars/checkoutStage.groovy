def call(Map parameters = [:]) {
    def gitBranch = parameters.gitBranch ?: 'main'
    def gitUrl = parameters.gitUrl ?: 'https://github.com/wolf452/CloudDevOpsProject.git'

    stage('Checkout Code') {
        checkout scm: [
            $class: 'GitSCM',
            branches: [[name: "*/${gitBranch}"]],
            userRemoteConfigs: [[url: gitUrl]]
        ]
    }
}

return this
