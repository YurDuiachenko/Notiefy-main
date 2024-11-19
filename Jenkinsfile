pipeline {
    agent none

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/YurDuiachenko/Notify-main.git'
            }
        }

        stage('Build') {
            agent {
                docker { image 'gradle:7.6-jdk17' }
            }
            steps {
                script {
                    sh './gradlew clean build -x test'  // Убираем тесты на этапе сборки
                }
            }
        }

        stage('Test') {
            agent {
                docker { image 'gradle:7.6-jdk17' }
            }
            steps {
                script {
                    sh './gradlew test'
                }
            }
        }

        stage('Deploy') {
        //     agent {
        //         docker { image 'docker:19.03.12' } // Используем Docker-образ с Docker для деплоя
        //     }
        //     steps {
        //         script {
        //             sh 'docker build -t my-spring-app .'
        //             sh 'docker run -d -p 8080:8080 my-spring-app'
        //         }
        //     }
        // }
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