pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/JoaoAGrilo/DevOpsAC2.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t joaoagrilo/ac2:dev .'
            }
        }
        stage('Run Docker Compose') {
            steps {
                sh 'docker-compose -f docker-compose.dev.yml up -d'
            }
        }
    }
}
