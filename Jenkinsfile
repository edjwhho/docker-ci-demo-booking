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
    myScript.mvn 'clean package'
}

stage "RUN HELLO TEST"
node {
    sh 'echo hello world '
}


stage "Build Docker image"
node {
    sh 'docker build -t treeptik/${env.JOB_NAME}${env.BUILD_NUMBER} . '
}

stage "Run container"
node {
    sh 'docker run -d -p 8080 ${env.JOB_NAME}${env.BUILD_NUMBER} '
}
