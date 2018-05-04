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
		stage ('InstallPy3') {
		    steps {
                sh 'sudo apt install python3 -y'
		        }
            }
		stage ('FunctionalTest') {
		      sh 'python3 NonValuatedFlow.py qa'
		}
		stage ('LoadTest') {
			steps {
			   sh 'python3 Val_Recon_Pledge.py' 
			   sh 'python3 Val_Dispute.py'
			   sh 'python3 NonVal_Recon_Pledge.py'
			   sh 'python3 NonVal_Dispute.py'
			}
        }
	}
}

