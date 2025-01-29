pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/RahulDani/Selenium_CI.git'  // Your Git repository URL
            }
        }

        stage('Build Project') {
            steps {
                script {
                    // Assuming you're using Maven or Gradle to build the project
                    bat 'mvn clean install'  // Adjust to your build tool and command
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    // Running Selenium tests
                    bat 'mvn test'  // Adjust as needed for your test command
                }
            }
        }
    }
}
