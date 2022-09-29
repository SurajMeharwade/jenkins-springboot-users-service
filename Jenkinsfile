pipeline 
{
    agent any
    
    environment
    {
    	DOCKER_IMG_NAME = "user-service"
    	DOCKER_CONTAINER_NAME = "user-service-container"
    	DOCKER_REPO = "9341708657"
    }

    stages 
    {
  
        stage('Maven Clean') 
        {
            steps 
            {
                sh 'mvn clean'
            }
   
        }
        
        stage('Maven Compile') 
        {
            steps 
            {
                sh 'mvn compile'
            }
   
        }
        /*stage('Quality') 
        {
            steps 
            {
                sh 'mvn sonar:sonar'
            }
   
        }*/
        
        stage('Maven Test') 
        {
            steps 
            {
                sh 'mvn test'
            }
   
        }
        
        stage('Maven Create JAR') 
        {
            steps 
            {
                sh 'mvn package -DskipTests'
            }
   
        }
        stage('Dockerize') 
        {
            steps 
            {
                echo 'building the docker image for user-service...'
				sh "docker build -t ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID} ."
				sh "docker tag ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID} ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest"    
			}
   
        }
        stage('Integration Testing') 
        {
            steps 
            {
        		sh 'docker run -dp 7070:8080 --rm --name ${DOCKER_CONTAINER_NAME} ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest'
        		sleep 30
        		sh 'curl -i http://localhost:7070/api/users'
            }
   
   
        }
        
        stage('docker publish') 
        {
        	steps 
        	{
	        	withDockerRegistry([credentialsId: 'docker-creds', url: '']) 
	        	{
        			sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID}"
        			sh "docker push ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest"
        		}
        	}
        }
    }
    
    post
    {
    	always
    	{
    	sh 'docker stop ${DOCKER_CONTAINER_NAME}'
    	sh "docker rmi ${DOCKER_REPO}/${DOCKER_IMG_NAME}:latest ${DOCKER_REPO}/${DOCKER_IMG_NAME}:${env.BUILD_ID}"
    	}
    }
}