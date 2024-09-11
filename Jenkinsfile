pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = "faggioni/k8s"
    }
    stages {
        stage('Build Docker Image') {
            steps {
                def image = docker.build("faggioni/k8s:${env.BUILD_NUMBER}")
            }
        }
        stage('Push Docker Image') {
            when {
                branch 'master'
            }
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker_hub_login') {
                        app.push("latest")
                    }
                }
            }
        }
    }
}
