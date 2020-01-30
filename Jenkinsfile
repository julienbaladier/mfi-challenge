pipeline {
  agent any
  triggers {
    pollSCM('H/5 * * * *')
  }
  stages {
    stage('Build Metwork image') {
      steps {
        sh 'docker build metwork -t localhost:5000/jb/metwork:latest'
      }
    }
    stage('Push Metwork image') {
      steps {
        sh 'docker push localhost:5000/jb/metwork:latest'
      }
    }
  }
}
