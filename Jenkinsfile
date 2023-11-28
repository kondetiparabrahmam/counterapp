pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // Checkout the code from your version control system
        checkout https: //github.com/kondetiparabrahmam/counterapp.git
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
        script {
          // Build the Docker image using the Dockerfile in the project
          docker.build("counterapp1")
        }
      }
    }

    stage('Docker Run') {
      steps {
        script {
          // Run the Docker container, expose port 8080
          docker.image("counterapp1").run('-p 9090:9090', '--name counterapp1')
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
      // Clean up resources even if the pipeline fails
      script {
        docker.image("counterapp1").stop()
        docker.image("counterapp1").remove()
      }
    }
  }
}
