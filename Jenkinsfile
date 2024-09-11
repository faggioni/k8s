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
                script{
                    image = docker.build(DOCKER_IMAGE)
                    image.inside {
                        sh "echo test"
                    }
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.digitalocean.com/faggioni', 'digital_ocean_login') {
                        image.push(DOCKER_IMAGE_VERSION)
                    }
                }
            }
        }
    }
}
