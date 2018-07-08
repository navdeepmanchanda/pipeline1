package com.opstree.devops.scm

/****************************************
***** function to checkout the code *****
*****************************************/
def checkout(String git_url, String branch, String credentials)
{
   try {
      wrap([$class: 'AnsiColorBuildWrapper']) {
        println "\u001B[32m[INFO] => checking out code from ${git_url} and branch ${branch}, please wait..."
        checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${credentials}", url: "${git_url}"]]])
      }
   }
   catch(Exception e)
   {
      wrap([$class: 'AnsiColorBuildWrapper']) {
         print "\u001B[41m[ERROR] => failed to clone git repository ${git_url} from branch ${branch}, exiting..."
         currentBuild.result = "FAILURE"
         throw e
      }
   }
}
