package com.opstree.devops.build.maven

/***************************************
***** function to compile the code *****
***************************************/
def code_compile(String maven_goals) {
  try {
      wrap([$class: 'AnsiColorBuildWrapper']) {
        println "\u001B[32m[INFO] => compiling java code, please wait..."
        def maven_home = tool name: 'MAVEN_HOME', type: 'maven'
        MAVEN_GOALS = maven_goals
        env.PATH = "${maven_home}/bin:${env.PATH}"
        sh "mvn ${MAVEN_GOALS}"
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