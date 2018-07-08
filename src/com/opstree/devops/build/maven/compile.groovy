package com.opstree.devops.build.maven

/***************************************
***** function to compile the code *****
***************************************/
def code_compile(String maven_goals, String pom_path) {
  try {
      wrap([$class: 'AnsiColorBuildWrapper']) {
        println "\u001B[32m[INFO] => compiling java code, please wait..."
        def maven_home = tool name: 'MAVEN_HOME', type: 'maven'
        MAVEN_GOALS = maven_goals
        MAVEN_PATH = maven_path
        env.PATH = "${maven_home}/bin:${env.PATH}"
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
