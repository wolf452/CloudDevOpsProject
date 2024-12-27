def call() {
    dir("${ANSIBLE_DIR}") {
        ansiblePlaybook(
            credentialsId: 'ansible-ssh-key',
            inventory: "${INVENTORY_FILE}",
            playbook: 'playbook.yml',
            extras: '-e "ansible_ssh_extra_args=\'-o StrictHostKeyChecking=no\'"'
        )
    }
}
