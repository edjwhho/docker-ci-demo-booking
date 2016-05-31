def myScript

stage "Init SCM"
node {
    checkout scm
    myScript = load 'script.groovy'
}

stage "Run unit test"
node {
    myScript.mvn 'test'
}

stage "Build application WAR"
node {
    myScript.mvn 'package -DskipTests'
}

stage "RUN HELLO TEST"
node {
    sh 'env | sort '
}


stage "Build Docker image"
node {
    sh 'docker build -t ${BUILD_TAG} . '
}

stage "Run container"
node {
    sh 'docker --name ${BUILD_TAG} run -d -p 8080 ${BUILD_TAG} '
    sh 'echo La redirection du port 8080 est :'
    sh 'docker inspect -f '{{(index (index .NetworkSettings.Ports "8080/tcp") 0).HostPort}}' ${BUILD_TAG}
}
