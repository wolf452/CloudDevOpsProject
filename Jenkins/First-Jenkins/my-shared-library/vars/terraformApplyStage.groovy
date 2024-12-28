def call(Map config = [:]) {
    stage('Terraform Apply') {
        step {
            dir(config.terraformDir ?: 'terraform') {
                sh 'terraform apply -auto-approve'
            }
        }
    }
}
