def call(Map config = [:]) {
    stage('Run Ansible') {
        step {
            dir(config.ansibleDir ?: 'ansible') {
                ansiblePlaybook(
                    credentialsId: config.ansibleCredentialsId ?: 'ansible-ssh',
                    inventory: config.inventoryFile ?: 'inventory',
                    playbook: config.ansiblePlaybook ?: 'playbook.yml',
                    extras: '-e "ansible_ssh_extra_args=\'-o StrictHostKeyChecking=no\'"'
                )
            }
        }
    }
}
