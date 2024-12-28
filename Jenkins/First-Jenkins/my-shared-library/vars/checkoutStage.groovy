checkout([
  $class: 'GitSCM',
  branches: [[name: 'main']],
  userRemoteConfigs: [[url: 'https://github.com/wolf452/CloudDevOpsProject.git']]
])
