pipeline {
    agent none

    environment {
        WORKSPACE_DIR = "/home/jenkins/agent/workspace/Notiefy-main@2"
        PROJECT_NAME = "Notiefy-main"
    }

    stages {
        stage('Checkout') {
            agent { label 'agent' }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'github-creds', usernameVariable: 'GITHUB_USER', passwordVariable: 'GITHUB_TOKEN')]) {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: '*/master']],
                            userRemoteConfigs: [[
                                url: 'https://github.com/YurDuiachenko/Notiefy-main.git',
                                credentialsId: 'github-creds'
                            ]]
                        ])
                    }
                }
                sh 'pwd'
                sh 'ls -la'
            }
        }

        stage('Build') {
            agent {
                docker {
                    image 'gradle:8.10.2-jdk17'
                    reuseNode true
                    args "--user root --volume ${WORKSPACE_DIR}:/Notiefy-main --workdir=/Notiefy-main --volume /tmp/.gradle:/home/gradle/.gradle"
                }
            }
            steps {
                script {
                    sh 'gradle clean build -x test'
                }
            }
        }

        stage('Deploy') {
            agent { label 'agent' }
            steps {
                script {
                    sh 'docker-compose up -d'
                    sh 'ls -la build/libs'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
