pipeline {
    agent any

    environment {
        RESTAPI = 't1-project'
        DOCKERFILE_BRANCH = 'main'
        DOCKER_HUB_REGISTRY = 'docker.io'
        IMAGE_TAG = 'latest'
        
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
                        sh "sudo docker build -t ${DOCKER_HUB_REGISTRY}/${DOCKER_HUB_USERNAME}/${RESTAPI}:${IMAGE_TAG} ."
                    }
                }
            }
        }

        stage('Code Analysis') {
            environment {
                scannerHome = tool 'sonarqube'
            }
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner \
                            sh "${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=T1 \
                            -Dsonar.projectName=T1 \
                            -Dsonar.sources=."
                    }
                }
            }
        }

        stage('Quality gate check') {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }

        stage('Pushing to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-credentials', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                    sh "sudo docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                    sh "sudo docker push ${DOCKER_HUB_REGISTRY}/${DOCKER_HUB_USERNAME}/${RESTAPI}:${IMAGE_TAG}"
                }
            }
        }
    }
}
