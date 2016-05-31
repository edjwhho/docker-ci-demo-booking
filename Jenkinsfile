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

stage "Build Docker image"
node {
    sh 'docker build -t ${BUILD_TAG} . '
}

stage "Run container"
node {
    sh 'docker run --name ${BUILD_TAG} -d -p 8080 ${BUILD_TAG} '
}
