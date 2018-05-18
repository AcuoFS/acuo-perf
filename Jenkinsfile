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
		      sh 'chmod +x ./Selenium/acuo-func/src/test/resources/Driver/chromedriver.exe'
		      sh 'chmod +x ./Selenium/acuo-func/src/test/resources/Driver/chromedriver'
		      sh 'python3 ./Selenium/acuo-func/NonValuatedFlow.py qa'
			  }
		}
		stage ('LoadTest') {
			steps {
			   sh 'python3 ./Selenium/acuo-func/Val_Recon_Pledge.py' 
			   sh 'python3 ./Selenium/acuo-func/Val_Dispute.py'
			   sh 'python3 ./Selenium/acuo-func/NonVal_Recon_Pledge.py'
			   sh 'python3 ./Selenium/acuo-func/NonVal_Dispute.py'
			}
        }
	}
}
