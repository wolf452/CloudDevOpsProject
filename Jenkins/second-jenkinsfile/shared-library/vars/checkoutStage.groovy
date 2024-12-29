// vars/checkoutCode.groovy
def call() {
    stage('Checkout Code') {
        git branch: 'main', url: 'https://github.com/wolf452/CloudDevOpsProject.git'
    }
}

return this
