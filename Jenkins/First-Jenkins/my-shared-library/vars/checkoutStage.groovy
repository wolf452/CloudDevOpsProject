// checkoutStage.groovy
def call(String gitUrl, String branch) {
    echo "Cloning the source code from GitHub"
    git url: gitUrl, branch: branch
}
