import jenkins.model.*;

def executors = System.getenv("JENKINS_MASTER_EXECUTORS") ?: "1";
Jenkins.instance.setNumExecutors(executors.toInteger());
println("-----> jenkins master executors number has been set to " + executors);
