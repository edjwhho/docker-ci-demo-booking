def myScript

stage "Init SCM"
node {
    checkout scm
    myScript = load 'script.groovy'
    sh 'bash /vagrant/ucp-bundle-admin/start_env.sh'
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
    sh 'docker tag tomcat-${BUILD_TAG} 192.168.50.11/tomcat-${BUILD_TAG}'
    sh 'docker login -u cvandome -p treeptik -e c.vandome@treeptik.fr 192.168.50.11 && docker push 192.168.50.11/tomcat-${BUILD_TAG}'
}

/*
stage "Stop container"
node {
    sh 'docker-compose  -f docker-compose.yml down '
}
*/
