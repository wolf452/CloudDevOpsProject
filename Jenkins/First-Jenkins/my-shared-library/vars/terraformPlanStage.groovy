def call(Map config = [:]) {
    stage('Terraform Plan') {
        step {
            dir(config.terraformDir ?: 'terraform') {
                sh 'terraform plan'
            }
        }
    }
}
