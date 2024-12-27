def call() {
    echo "Deploying to Kubernetes"
    sh "kubectl --kubeconfig=/home/ubuntu/jenkins/.kube/config apply -f $DEPLOYMENT_YAML"
}
