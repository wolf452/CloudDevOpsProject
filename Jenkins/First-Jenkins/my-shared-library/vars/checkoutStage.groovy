def call(String gitUrl, String branch) {
    echo "Cloning the source code from GitHub"
    
    // استخدام checkout مباشرة مع تعريف GitSCM
    checkout([
        $class: 'GitSCM',
        branches: [[name: branch]],
        userRemoteConfigs: [[url: gitUrl]]
    ])
}
