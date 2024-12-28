def call(Map config = [:]) {
    stage('Terraform Apply') {
        steps {
            dir(config.terraformDir ?: 'terraform') {
                sh 'terraform apply -auto-approve'
            }
        }
    }
}
