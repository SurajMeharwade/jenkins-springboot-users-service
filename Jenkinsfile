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
        stage('Quality') 
        {
            steps 
            {
                sh 'mvn sonar:sonar'
            }
   
        }
        
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
    }
}