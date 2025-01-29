pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                  git branch: 'main', url: 'https://github.com/RahulDani/Selenium_CI.git'  // Replace with your Git repository URL
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean package'  // Compiles and creates a JAR file in the target folder
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'java -jar target/Selenium_first-1.0-SNAPSHOT.jar'  // Replace with your JAR file name
            }
        }
    }
}
