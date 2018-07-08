#!groovy

import com.opstree.devops.scm.*

def call(body)
{
   def config = [:]
   body.resolveStrategy = Closure.DELEGATE_FIRST
   body.delegate = config
   body()
   timestamps {
      currentBuild.result = "SUCCESS"
      try {
         wrap([$class: 'AnsiColorBuildWrapper']) {
            stage('\u2776 Code Checkout') {
               def c = new git()
               c.checkout("${config.git_url}","${config.branch}","${config.credentials}")
            }
         }
      }
      catch(Exception e) {
        wrap([$class: 'AnsiColorBuildWrapper']) {
           print "\u001B[41m[ERROR] => failed to run the spring pipeline, please check logs"
           currentBuild.result = "FAILURE"
           throw e
        }
      }
      finally {
        wrap([$class: 'AnsiColorBuildWrapper']) {
           print '\u001B[34m[INFO] => in the finally block"
        }
      }
   }
}
         
