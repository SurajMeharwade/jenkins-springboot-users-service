pipeline 
{
    agent any

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
                sh 'docker build -t user-service:latest .'
            }
   
        }
        stage('Integration Testing') 
        {
            steps 
            {
                sh 'docker run -dp 7070:8080 -rm --name user-service-container user-service'
            	sleep 30
            	sh 'curl -i http://localhost:7070/api/users'
            }
   
   
        }
    }
    
    post
    {
    	always
    	{
    	sh 'docker stop user-service-container'
    	}
    }
}