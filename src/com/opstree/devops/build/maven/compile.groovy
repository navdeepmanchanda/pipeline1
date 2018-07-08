package com.opstree.devops.build.maven

/***************************************
***** function to compile the code *****
***************************************/
def code_compile(String maven_goals, String pom_path) {
  try {
      wrap([$class: 'AnsiColorBuildWrapper']) {
        println "\u001B[32m[INFO] => compiling java code, please wait..."
        def maven_home = tool name: 'MAVEN_HOME', type: 'maven'
        def java_home  = tool name: 'JAVA_HOME', type: 'jdk'
        MAVEN_GOALS = maven_goals
        POM_PATH = pom_path
        env.JAVA_HOME = java_home
        env.PATH = "${maven_home}/bin:${java_home}/bin:${env.PATH}"
        sh "mvn -f ${POM_PATH} ${MAVEN_GOALS}"
      }
   }
   catch(Exception e)
   {
      wrap([$class: 'AnsiColorBuildWrapper']) {
         print "\u001B[41m[ERROR] => failed to compile the code, exiting..."
         currentBuild.result = "FAILURE"
         throw e
      }
   }
}


def unit_tests(String maven_test_goals, String pom_path) {
  try {
      wrap([$class: 'AnsiColorBuildWrapper']) {
        println "\u001B[32m[INFO] => compiling java code, please wait..."
        def maven_home = tool name: 'MAVEN_HOME', type: 'maven'
        def java_home  = tool name: 'JAVA_HOME', type: 'jdk'
        env.JAVA_HOME = java_home
        MAVEN_TEST_GOALS = maven_test_goals
        POM_PATH = pom_path
        env.PATH = "${maven_home}/bin:${JAVA_HOME/bin:${env.PATH}"
        sh "mvn -f ${POM_PATH} ${MAVEN_TEST_GOALS}"
      }
   }
   catch(Exception e)
   {
      wrap([$class: 'AnsiColorBuildWrapper']) {
         print "\u001B[41m[ERROR] => failed to compile the code, exiting..."
         currentBuild.result = "FAILURE"
         throw e
      }
   }
}

