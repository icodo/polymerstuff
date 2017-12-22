pipeline{
  agent none
  stages{
    stage('First step'){
      agent any
      steps{
        sh 'ls -ltrh'
        sh 'pwd' 
        echo "Testing first step"
      }
    }
  }
}