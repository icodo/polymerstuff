pipeline{
  agent none
  stages{
    stage('First step'){
      agent any
      steps{
        sh 'ls -ltrh'
        sh 'pwd'
        sh 'ls -ltrh'
        sh 'pwd'
        sh 'docker build .' 
        echo "Testing first step"
      }
    }
  }
}