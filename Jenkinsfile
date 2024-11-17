pipeline {
    agent any
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t joaoagrilo/ac2:dev .'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    sh 'docker push joaoagrilo/ac2:dev'
                }
            }
        }
    }
}
