def call(Map config = [:]) {
    stage('Delay Before Ansible Playbook') {
        step {
            script {
                echo "Waiting for ${config.delayMinutes ?: 1} minute(s) before proceeding..."
                sleep time: config.delayMinutes ?: 1, unit: 'MINUTES'
                echo "Proceeding to run Ansible playbook..."
            }
        }
    }
}
