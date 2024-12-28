def call(String gitUrl, String branch) {
    echo "Cloning the source code from GitHub"
    checkout([$class: 'GitSCM', branches: [[name: branch]], userRemoteConfigs: [[url: gitUrl]]])
}
