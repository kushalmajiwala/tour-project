package auth;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

@ApplicationScoped
public class performAuth implements HttpAuthenticationMechanism, Serializable {

    @Inject
    IdentityStoreHandler handler;

    CredentialValidationResult result;

    AuthenticationStatus status;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest req, HttpServletResponse res, HttpMessageContext hmc) throws AuthenticationException {
        try {
            req.setCharacterEncoding("UTF-8");
            if (req.getParameter("username") != null && req.getParameter("password") != null) {
                String username = req.getParameter("username");
                String password = req.getParameter("password");

                System.out.println(username);
                System.out.println(password);
                
                HttpSession session = req.getSession();

                Credential credential = new UsernamePasswordCredential(username, new Password(password));
                result = handler.validate(credential);

                if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                    if (result.getCallerGroups().contains("admin")) {
                        System.out.println("Admin Executed");
                        session.setAttribute("admin", username);
                        res.sendRedirect("adminHome.xhtml");
                    } else if (result.getCallerGroups().contains("user")) {
                        System.out.println("User Executed");
                        session.setAttribute("user", username);
                        res.sendRedirect("userHome.xhtml");
                    }
                } else {
                    System.out.println("Else Executed");
                    res.sendRedirect("loginError.xhtml?faces-redirect=true");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hmc.doNothing();
    }
}
