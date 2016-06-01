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

stage "Run IT Test"
node {
    myScript.mvn 'failsafe:integration-test'
}

stage "Push img on repo"
node {
    sh 'docker tag ${BUILD_TAG} localhost:5000/${BUILD_TAG}'
    sh 'docker push localhost:5000/${BUILD_TAG}'
}
