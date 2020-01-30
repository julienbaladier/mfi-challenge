import hudson.security.csrf.DefaultCrumbIssuer;
import jenkins.model.Jenkins;

def j = Jenkins.instance;

if(!j.isQuietingDown()) {

    if(j.getCrumbIssuer() == null) {
        j.setCrumbIssuer(new DefaultCrumbIssuer(true));
        j.save();
        println('-----> CSRF Protection has been enabled');
    }
    else {
        println('-----> CSRF Protection already configured');
    }
}
else {
    println("-----> shutdown mode enabled, CSRF protection configuration SKIPPED");
}
