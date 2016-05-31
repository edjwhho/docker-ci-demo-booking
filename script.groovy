
def mvn(args) {
    sh "${tool 'M3'}/bin/mvn ${args}"
}

def java(args) {
    sh "${tool 'JAVA8'}/bin/java ${args}"
}

this
