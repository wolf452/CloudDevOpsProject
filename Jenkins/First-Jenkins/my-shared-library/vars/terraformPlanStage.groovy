def call(Map config = [:]) {
    stage('Terraform Plan') {
        steps {
            dir(config.terraformDir ?: 'terraform') {
                sh 'terraform plan'
            }
        }
    }
}
