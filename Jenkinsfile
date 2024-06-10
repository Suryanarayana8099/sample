pipeline {
    agent any

    environment {
        RESTAPI = 't1-project'
        DOCKERFILE_BRANCH = 'main'
        DOCKER_HUB_USERNAME = 'suryanarayana070'
        DOCKER_HUB_REGISTRY = 'docker.io'
        IMAGE_TAG = 'latest'
        SONAR_PROJECT_KEY = 'T1'
        SONAR_PROJECT_NAME = 'T1'
        SONAR_SERVER_URL = 'http://54.241.147.5:9000'
        SONAR_LOGIN = 'squ_43ab795516f57fbd4eef59b939230703894f2081'
    }

    tools {
        maven 'Maven-3.8.4'
    }

    stages {
        stage('SCM') {
            steps {
                dir("${WORKSPACE}/${RESTAPI}") {
                    git branch: 'main', url: 'https://github.com/Suryanarayana8099/sample.git'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build
                    dir("${WORKSPACE}/${RESTAPI}") {
                        sh 'mvn package'
                    }
                    
                    // Build the Docker image
                    dir("${WORKSPACE}/${RESTAPI}") {
                        // Docker build command
                        sh "sudo docker build -t ${DOCKER_HUB_USERNAME}/${RESTAPI}:${IMAGE_TAG} ."
                        sh "sudo docker push ${DOCKER_HUB_USERNAME}/${RESTAPI}:${IMAGE_TAG}"
                    }
                }
            }
        }

        stage('Code Analysis') {
            steps {
                script {                    
                    // Run SonarScanner to send the reports to SonarQube
                    sh "SonarQube \
                        -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                        -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                        -Dsonar.sources=. \
                        -Dsonar.host.url=${SONAR_SERVER_URL} \
                        -Dsonar.login=${SONAR_LOGIN}"
                }
            }
        }
    }
}

