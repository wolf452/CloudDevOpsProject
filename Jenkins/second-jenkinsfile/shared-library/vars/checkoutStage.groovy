// vars/checkoutCode.groovy
def call(Map parameters = [:]) {
    def gitBranch = parameters.gitBranch ?: 'main'
    def gitUrl = parameters.gitUrl ?: 'https://github.com/wolf452/CloudDevOpsProject.git'

    stage('Checkout Code') {
        git branch: gitBranch, url: gitUrl
    }
}

return this
