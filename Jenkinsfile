def myScript

stage "Init SCM"
node {
    checkout scm
    myScript = load 'script.groovy'
    sh 'sh /vagrant/ucp-bundle-admin/start_env.sh'
}

stage "Run unit test"
node {
    myScript.mvn 'test'
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

stage "Run IT Test"
node {
    myScript.mvn 'failsafe:integration-test'
}

stage "Push img on repo"
node {
    sh 'docker tag tomcat-${BUILD_TAG} localhost:5000/tomcat-${BUILD_TAG}'
    sh 'docker push localhost:5000/tomcat-${BUILD_TAG}'
}

/*
stage "Stop container"
node {
    sh 'docker-compose  -f docker-compose.yml down '
}
*/
