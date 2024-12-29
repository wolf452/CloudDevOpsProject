// src/org/jenkins/pipelines/stages/CheckoutStage.groovy

def call() {
    stage('Checkout') {
        steps {
            echo "Cloning the source code from GitHub"
            git url: 'https://github.com/wolf452/FinalProjectCode.git', branch: 'main'
        }
    }
}
