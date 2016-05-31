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
    sh 'docker run -d -p 8080 ${BUILD_TAG} '
}
