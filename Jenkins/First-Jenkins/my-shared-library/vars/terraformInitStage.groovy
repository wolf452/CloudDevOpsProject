def call(Map config = [:]) {
    stage('Terraform Init') {
        step {
            dir(config.terraformDir ?: 'terraform') {
                withCredentials([aws(credentialsId: config.awsCredentialsId ?: 'aws-cred')]) {
                    script {
                        env.TF_VAR_AWS_ACCESS_KEY = env.AWS_ACCESS_KEY_ID
                        env.TF_VAR_AWS_SECRET_KEY = env.AWS_SECRET_ACCESS_KEY
                    }
                    sh 'terraform init -reconfigure'
                }
            }
        }
    }
}
