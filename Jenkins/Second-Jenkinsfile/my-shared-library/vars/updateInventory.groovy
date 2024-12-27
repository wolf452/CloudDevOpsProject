def call() {
    dir("${ANSIBLE_DIR}") {
        script {
            def inventoryContent = "[ec2]\n${env.PUBLIC_IP} ansible_user=ubuntu ansible_ssh_private_key_file=./ivolve.pem\n"
            writeFile file: "${INVENTORY_FILE}", text: inventoryContent
        }
    }
}
