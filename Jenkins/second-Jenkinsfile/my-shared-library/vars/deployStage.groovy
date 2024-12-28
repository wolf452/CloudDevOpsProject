def call(String deploymentYaml) {
    stage('Deploy to Kubernetes') {
        step {
            echo "Deploying the application to Kubernetes"
            sh "kubectl --kubeconfig=/home/ubuntu/jenkins/.kube/config apply -f $deploymentYaml"
        }
    }
}
