pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Deploy') {
        steps {
            bat '''
                copy /Y "target\\employee-management-system-0.0.1-SNAPSHOT.jar" "C:\\ProgramData\\Jenkins\\deploy\\employee-management-system.jar"

                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do taskkill /PID %%a /F

                start "Employee Management System" /B cmd /c "java -jar C:\\ProgramData\\Jenkins\\deploy\\employee-management-system.jar > C:\\ProgramData\\Jenkins\\deploy\\application.log 2>&1"
        '''
            }
        }
    }

    post {
        success {
            echo 'Build and deployment completed successfully!'
        }

        failure {
            echo 'Build or deployment failed!'
        }
    }
}