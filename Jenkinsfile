pipeline {
  agent any
  environment {
        // Define the Maven tool installation
        MAVEN_HOME = tool 'Maven 3.2.5'
        PATH = "${MAVEN_HOME}/bin:${PATH}"
     
    }

  stages {
    stage('Checkout') {
      steps {
        // Checkout the code
        cleanWs()
                  checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/kondetiparabrahmam/counterapp.git']])         
      }
    }

    stage('Build') {
      steps {
        script {
          // Build the Maven project
          sh 'mvn clean install'
        }
      }
    }

    stage('Docker Build') {
      steps {
        sh 'docker build -t counterapp .'
      }
    }

    stage('Docker Run') {
      steps {
        script {
                    // Run the container in detached mode with interactivity
                    //sh 'docker run -p 9090:9090 counterapp'
                    //def containerId = sh(script: 'docker ps -lq', returnStdout: true).trim()
                    def containerId = sh(script: 'docker run -d -p 9090:9090 counterapp', returnStdout: true).trim()
                    
                    // Store the container ID for later cleanup
                    env.CONTAINER_ID = containerId
                    
                

                  
                }
      }
    }
  }

  post {
    success {
      // Display information on how to access the application in a browser
      echo 'Application is accessible at http://3.93.17.109:9090'
    }

    always {
      steps {
                script {
                sh 'docker stop ${env.CONTAINER_ID} || true'  // Stop the container (if it's still running)
                sh 'docker rm ${env.CONTAINER_ID} || true'  
                            }
            }
      
  }
}
}
