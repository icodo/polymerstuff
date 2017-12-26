pipeline{
  agent none
  stages{
    stage('Clean') {
            agent any
            steps {
                deleteDir()
            }
        }
    stage('Build') {
            agent any
            steps {
                sh 'docker version'
            }
        }
      }
    }