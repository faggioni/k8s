pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = "faggioni/k8s"
        DOCKER_IMAGE_VERSION = "0.0.${env.BUILD_NUMBER}"
        DOCKER_IMAGE = "${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION}"
    }
    stages {
        stage('Build Docker Image') {
            steps {
                docker.build(DOCKER_IMAGE)
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
