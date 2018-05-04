@Library('github.com/anaxo-io/pipeline-library@develop') _
pipeline {
    agent { label 'ubuntu_agent' }
    triggers {
        pollSCM("")
    }

    stages {
        stage ('Checkout') {
            steps {
                deleteDir()
                checkout scm
            }
			}
		stage ('InstallPy') {
		    steps {
                sudo apt install python -y
				}
            }
        }
    }

      
