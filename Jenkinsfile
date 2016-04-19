def src = 'https://github.com/TimTim74/TimTestPortlet.git'

node {
    def mvnHome = tool 'maven304'

    stage 'Checkout current_dev'

    //git url: src, credentialsId: cred, branch: 'current_dev'
    git url: src

    def v = version()
    def a = artifactId()
    def p = packaging()
    echo "Building version: ${a}-${v}.${p}"

    stage 'Build'
    bat "${mvnHome}\\bin\\mvn -B verify"

    stage 'Build XML'
    String filename = "test.txt"
    // creates a new file test.txt
    boolean success = new File(filename).createNewFile()
    echo 'Did we create a file: ' + success

    stage 'Push to Nexus'
    bat "${mvnHome}\\bin\\mvn -B deploy"
}

def version() {
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    matcher ? matcher[0][1] : null
}

def artifactId() {
    def matcher = readFile('pom.xml') =~ '<artifactId>(.+)</artifactId>'
    matcher ? matcher[0][1] : null
}

def packaging() {
    def matcher = readFile('pom.xml') =~ '<packaging>(.+)</packaging>'
    matcher ? matcher[0][1] : null
}