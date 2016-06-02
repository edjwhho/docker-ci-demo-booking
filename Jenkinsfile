def myScript

stage "Init SCM"
node {
    checkout scm
    myScript = load 'script.groovy'
}

stage "Build application WAR"
node {
    myScript.mvn 'package -DskipTests'
}

stage "Build Docker image"
node {
   sh 'sed "s/#BUILDTAG/${BUILD_TAG}/g" docker-compose.yml.template > docker-compose.yml ' 
}

stage "Run container"
node {
    sh 'docker-compose  -f docker-compose.yml up -d'
}
