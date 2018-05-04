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
		stage ('FunctionalTest') {
		    steps {
		      sh './Selenium/acuo-func/python3 NonValuatedFlow.py qa'
			  }
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
