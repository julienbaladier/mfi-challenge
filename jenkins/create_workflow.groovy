import jenkins.model.Jenkins;
import hudson.plugins.git.GitSCM;
import hudson.plugins.git.BranchSpec;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition;

def jenkins = Jenkins.instance;
def env = System.getenv();

def job_name = env["JENKINS_JOB_NAME"];
println("-----> creating a job: " + job_name);

// check if this script has already been executed
if (jenkins.getItem(job_name) != null){
  println("-----> the job " + job_name + " already exists, no need to create it");
  return 0;
}

def repo_url = env["JENKINS_JOB_REPO_URL"];
def branch = "*/master";
println("-----> job repository url: " + repo_url);


def scm = new GitSCM(repo_url);
scm.branches = [new BranchSpec("*/master")];

def job = new WorkflowJob(jenkins, job_name);
job.definition = new CpsScmFlowDefinition(scm, "Jenkinsfile");

// add job to the jenkins instance
jenkins.add(job, job.name);
println("-----> the job " + job_name + " has been created");

// schedule the first build
job.scheduleBuild();
