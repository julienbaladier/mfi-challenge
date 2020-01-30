import jenkins.model.*;
import hudson.security.*;
import jenkins.security.s2m.AdminWhitelistRule;

def env = System.getenv();
def instance = Jenkins.getInstance();

def user = env['JENKINS_USERNAME'];
def pass = env['JENKINS_PASSWORD'];
println("-----> creating user " + user + "...");

def hudsonRealm = new HudsonPrivateSecurityRealm(false);

// check if this script has already been executed
if (hudsonRealm.getUser(user) != null){
  println("-----> user " + user + " already exists, no need to create it");
  return 0;
}

hudsonRealm.createAccount(user, pass);
instance.setSecurityRealm(hudsonRealm);

def strategy = new FullControlOnceLoggedInAuthorizationStrategy();
instance.setAuthorizationStrategy(strategy);
instance.save();

instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false);
println("-----> user " + user + " has been created");
