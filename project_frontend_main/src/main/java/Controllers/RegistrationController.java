package Controllers;

import UserEJB.UserBeanLocal;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Usertb;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;

@Named(value = "registrationController")
@SessionScoped
public class RegistrationController implements Serializable {

    public RegistrationController() {
    }

    @EJB
    UserBeanLocal usb;

    Usertb u = new Usertb();

    public Usertb getU() {
        return u;
    }

    public void setU(Usertb u) {
        this.u = u;
    }

    String rpass;

    public String getRpass() {
        return rpass;
    }

    public void setRpass(String rpass) {
        this.rpass = rpass;
    }

    String captcha_response;

    public String getCaptcha_response() {
        return captcha_response;
    }

    public void setCaptcha_response(String captcha_response) {
        this.captcha_response = captcha_response;
    }
    
    public String getCurrentUsername()
    {
        String uname = "";
         try {
            HttpSession session = Util.getSession();
            if(session.getAttribute("admin") != null)
            {
                uname = session.getAttribute("admin").toString();
            }
            if(session.getAttribute("user") != null)
            {
                uname = session.getAttribute("user").toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return uname;
    }
    
    String admin_username = "";
    String user_username = "";

    public String redirectLogin() {
        System.out.println("Login Redirect");

        admin_username = "";
        user_username = "";

        try {
            HttpSession session = Util.getSession();
            if(session.getAttribute("admin") != null)
            {
                admin_username = session.getAttribute("admin").toString();
            }
            if(session.getAttribute("user") != null)
            {
                user_username = session.getAttribute("user").toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(admin_username);
        System.out.println(user_username);
        if (!admin_username.equals("")) {
            return "home/adminHome.xhtml?faces-redirect=true";
        }
        else if (!user_username.equals("")) {
            return "home/userHome.xhtml?faces-redirect=true";
        }
        return "home/login.xhtml?faces-redirect=true";
    }

    public String redirectLoginFromGallery()
    {
        System.out.println("Login Redirect");

        admin_username = "";
        user_username = "";

        try {
            HttpSession session = Util.getSession();
            if(session.getAttribute("admin") != null)
            {
                admin_username = session.getAttribute("admin").toString();
            }
            if(session.getAttribute("user") != null)
            {
                user_username = session.getAttribute("user").toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(admin_username);
        System.out.println(user_username);
        if (!admin_username.equals("")) {
            return "adminHome.xhtml?faces-redirect=true";
        }
        else if (!user_username.equals("")) {
            return "userHome.xhtml?faces-redirect=true";
        }
        return "login.xhtml?faces-redirect=true";
    }
    
    public String redirectLogout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        System.out.println("Session Destroyed...");
        return "gallery.xhtml?faces-redirect=true";
    }

    public String registerUser() {
        PrimeFaces current = PrimeFaces.current();

        System.out.println(u);
        System.out.println(u.getUsername().toString());
        System.out.println("User Registered Successfully...");

        if (u.getUsername().isEmpty() || u.getFname().isEmpty() || u.getLname().isEmpty() || u.getEmail().isEmpty() || u.getPassword().isEmpty() || rpass.isEmpty()) {
            System.out.println("Empty Field");
            current.executeScript("PF('emptyField').show();");
            return "register.xhtml";
        } else if (u.getPassword().equals(rpass)) {
            List<Usertb> mylist = new ArrayList<>();
            try {
                String url = "http://localhost:9090/user/getalldata";
                HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
                HttpClient client = HttpClient.newBuilder().build();
                HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
                ObjectMapper mapper = new ObjectMapper();
                Usertb[] myrec = mapper.readValue(response.body().toString(), Usertb[].class);
                for (Usertb rec : myrec) {
                    mylist.add(rec);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            int status = 0;
            for (Usertb user : mylist) {
                if (user.getUsername().equals(u.getUsername().toString())) {
                    status = 1;
                    break;
                }
            }
            if (status == 1) {
                System.out.println("User Already Exists");
                current.executeScript("PF('userExists').show();");
                return "register.xhtml";
            }
            if (status == 0) {
                current.executeScript("PF('userRegistered').show();");
                usb.registerUser(u.getUsername(), u.getFname(), u.getLname(), u.getEmail(), u.getPassword());
            }
        } else {
            System.out.println("Password and Confirm Password are not matching");
            current.executeScript("PF('unmatchPassword').show();");
            return "register.xhtml";
        }
        return "register.xhtml";
    }
}
