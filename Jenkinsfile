pipeline 
{
    agent any
    
    environment
    {
    	DOCKER_IMG_NAME = "user-service"
    	DOCKER_CONTAINER_NAME = "user-service-container"
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
                sh 'docker build -t ${DOCKER_IMG_NAME}:latest -t ${DOCKER_IMG_NAME}:${env.BUILD_ID} .'
            }
   
        }
        stage('Integration Testing') 
        {
            steps 
            {
                sh 'docker run -dp 7070:8080 --rm --name ${DOCKER_CONTAINER_NAME} ${DOCKER_IMG_NAME}:latest'
            	sleep 30
            	sh 'curl -i http://localhost:7070/api/users'
            }
   
   
        }
    }
    
    post
    {
    	always
    	{
    	sh 'docker stop ${DOCKER_CONTAINER_NAME}'
    	}
    }
}