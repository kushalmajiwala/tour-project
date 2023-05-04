
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "project_resource",
        callerQuery = "select password from usertb where username = ?",
        groupsQuery = "select groupname from projectgroups where username = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)
@ApplicationScoped
public class config {
    
}
