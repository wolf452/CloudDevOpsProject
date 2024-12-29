def call() {
    stage('Deploy to Kubernetes') {
        steps {
            echo "Deploying the application to Kubernetes"
            sh "kubectl --kubeconfig=/home/ubuntu/jenkins/.kube/config apply -f $DEPLOYMENT_YAML"
        }
    }
}
