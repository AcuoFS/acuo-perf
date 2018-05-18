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
		      sh 'cd ./Selenium/acuo-func/; python3 NonValuatedFlow.py qa'
			  }
		}
		stage ('LoadTest') {
			steps {
			   sh 'chmod -R 750./Jmeter/apache-jmeter-3.3/bin'
			   sh 'cd ./Jmeter/apache-jmeter-3.3/bin; python3 Val_Recon_Pledge.py' 
			   sh 'cd ./Jmeter/apache-jmeter-3.3/bin; python3 Val_Dispute.py'
			   sh 'cd ./Jmeter/apache-jmeter-3.3/bin; python3 NonVal_Recon_Pledge.py'
			   sh 'cd ./Jmeter/apache-jmeter-3.3/bin; python3 NonVal_Dispute.py'
			}
        }
	}
}
