pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/your-repo-url.git'  // Replace with your Git repository URL
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean package'  // Compiles and creates a JAR file in the target folder
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'java -jar target/your-selenium-test.jar'  // Replace with your JAR file name
            }
        }
    }
}
