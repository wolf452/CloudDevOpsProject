checkout([$class: 'GitSCM',
          branches: [[name: '*/main']],
          doGenerateSubmoduleConfigurations: false,
          extensions: [],
          userRemoteConfigs: [[url: 'https://github.com/wolf452/CloudDevOpsProject.git']]
])
