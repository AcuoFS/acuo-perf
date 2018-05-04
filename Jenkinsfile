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
                    sh 'sudo apt install python3 -y'
		}
            }
        }
    }

      
