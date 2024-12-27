def call() {
    script {
        env.PUBLIC_IP = sh(script: "cd ${TERRAFORM_DIR} && terraform output -raw ec2_public_ips", returnStdout: true).trim()
        if (env.PUBLIC_IP) {
            echo "Public IP: ${env.PUBLIC_IP}"
        } else {
            error "Public IP output not found in Terraform state"
        }
    }
}
