pipeline {
  agent any
  environment {
        // Define the Maven tool installation
        MAVEN_HOME = tool 'Maven 3.2.5'
        PATH = "${MAVEN_HOME}/bin:${PATH}"
		DOCKER_IMAGE_NAME = "bannukondeti/counterapp"
        DOCKER_IMAGE_TAG = "latest"
        DOCKER_USERNAME = "kondetipbhm@gmail.com"
        DOCKER_PASSWORD = "Bannu@123"
        DOCKER_REGISTRY_URL = "https://index.docker.io/v1/"
            
    }

  stages {
    stage('Checkout') {
      steps {        
        cleanWs()
        checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/kondetiparabrahmam/counterapp.git']])         
      }
    }

    stage('Build') {
      steps {
        script {         
          sh 'mvn clean install'
        }
      }
    }

    stage('Docker Build') {
      steps {
        sh 'docker stop $(docker ps -q) || true'
        sh 'docker rm -f $(docker ps -a -q) || true'
        sh 'docker rmi -f $(docker images -q) || true' 
        sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
        sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
        sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD} ${DOCKER_REGISTRY_URL}"
        sh "docker push ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"		
        //sh 'docker build -t counterapp .'
      }
    }

    stage('Docker Run') {
      steps {
        script {                    
                    def containerId = sh(script: 'docker run -d -p 9090:9090 counterapp', returnStdout: true).trim()                    
                    env.CONTAINER_ID = containerId                                
                  
                }
      }
    }
  }

  post {
        success {
           
            
           script {
                    def publicIp = sh(script: 'curl -s ifconfig.me', returnStdout: true).trim()
                    echo "Public IP: ${publicIp}"
                    echo "Application is accessible at http://${publicIp}:9090"
                }
        }

        failure {
         
            echo 'Pipeline failed!'
           script {
                sh 'docker stop ${env.CONTAINER_ID} || true'  // Stop the container (if it's still running)
                sh 'docker rm ${env.CONTAINER_ID} || true'  
                            }
        }

        always {
            
            echo 'Cleaning up...'
           
        }
    }
}
