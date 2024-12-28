// deployStage.groovy
def call(String deploymentYaml) {
    echo "Deploying the application to Kubernetes"
    sh "kubectl --kubeconfig=/home/ubuntu/jenkins/.kube/config apply -f $deploymentYaml"
}
