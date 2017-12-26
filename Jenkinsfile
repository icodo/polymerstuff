pipeline{
  agent none
  stages{
    stage('First step'){
      agent any
      steps{
        sh 'ls -ltrh'
        sh 'pwd'
        sh 'ls -ltrh'
        sh 'cd ci/'
        sh 'ls -ltrh'
        sh 'docker build .' 
        echo "Testing first step"
      }
    }
  }
}