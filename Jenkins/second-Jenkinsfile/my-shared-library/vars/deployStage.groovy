def call(String deploymentYaml) {
    stage('Deploy to Kubernetes') {
        steps {
            echo "Deploying the application to Kubernetes"
            sh "kubectl --kubeconfig=/home/ubuntu/jenkins/.kube/config apply -f $deploymentYaml"
        }
    }
}
