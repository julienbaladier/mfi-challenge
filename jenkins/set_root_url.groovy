import jenkins.model.Jenkins;
import jenkins.model.JenkinsLocationConfiguration;

// get Jenkins location configuration
def location_configuration = JenkinsLocationConfiguration.get();

// set Jenkins URL
location_configuration.setUrl(System.getenv("JENKINS_ROOT_URL"));

// save current Jenkins state to disk
location_configuration.save();
println("-----> Jenkins root URL has been set");
