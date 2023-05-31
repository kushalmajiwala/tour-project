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
import java.util.Properties;
import java.util.Random;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import org.primefaces.PrimeFaces;

@Named(value = "registrationController")
@SessionScoped
public class RegistrationController implements Serializable {

    public RegistrationController() {
    }
    PrimeFaces current = PrimeFaces.current();

    @EJB
    private UserBeanLocal ubl;

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

    public String getCurrentUsername() {
        String uname = "";
        try {
            HttpSession session = Util.getSession();
            if (session.getAttribute("admin") != null) {
                uname = session.getAttribute("admin").toString();
            }
            if (session.getAttribute("user") != null) {
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
            if (session.getAttribute("admin") != null) {
                admin_username = session.getAttribute("admin").toString();
            }
            if (session.getAttribute("user") != null) {
                user_username = session.getAttribute("user").toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(admin_username);
        System.out.println(user_username);
        if (!admin_username.equals("")) {
            return "home/adminHome.xhtml?faces-redirect=true";
        } else if (!user_username.equals("")) {
            return "home/userHome.xhtml?faces-redirect=true";
        }
        return "home/login.xhtml?faces-redirect=true";
    }

    public String redirectLoginFromGallery() {
        System.out.println("Login Redirect");
        System.out.println("This is being Called...");

        admin_username = "";
        user_username = "";

        try {
            HttpSession session = Util.getSession();
            if (session.getAttribute("admin") != null) {
                admin_username = session.getAttribute("admin").toString();
            }
            if (session.getAttribute("user") != null) {
                user_username = session.getAttribute("user").toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(admin_username);
        System.out.println(user_username);
        if (!admin_username.equals("")) {
            return "adminHome.xhtml?faces-redirect=true";
        } else if (!user_username.equals("")) {
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

        if (u.getUsername() == null || u.getUsername().isEmpty() || u.getFname().isEmpty() || u.getLname().isEmpty() || u.getEmail().isEmpty() || u.getPassword().isEmpty() || rpass.isEmpty()) {
            System.out.println("Empty Field");
            current.executeScript("PF('emptyField').show();");
            return "register.xhtml";
        } else if (!u.getEmail().contains("@")) {
            current.executeScript("PF('invalidEmail').show();");
        } else if (u.getPassword().length() < 8) {
            current.executeScript("PF('passwordShortLength').show();");
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
                ubl.registerUser(u.getUsername(), u.getFname(), u.getLname(), u.getEmail(), u.getPassword());
            }
        } else {
            System.out.println("Password and Confirm Password are not matching");
            current.executeScript("PF('unmatchPassword').show();");
            return "register.xhtml";
        }
        return "register.xhtml";
    }

    public void openForgotPasswordDialog() {
        current.executeScript("PF('takeUsername').show();");
    }

    public String closeTakePasswordDialog() {
        return "login.xhtml?faces-redirect=true";
    }
    //Forgot Password Working
    Usertb forgotUser = new Usertb();

    public Usertb getForgotUser() {
        return forgotUser;
    }

    public void setForgotUser(Usertb forgotUser) {
        this.forgotUser = forgotUser;
    }

    String actual_code = "";
    String user_code = "";
    String current_username = "";
    String new_password = "";
    String confirm_new_password = "";
    String encrypted_current_password = "";

    public String getEncrypted_current_password() {
        return encrypted_current_password;
    }

    public void setEncrypted_current_password(String encrypted_current_password) {
        this.encrypted_current_password = encrypted_current_password;
    }

    public String getCurrent_username() {
        return current_username;
    }

    public void setCurrent_username(String current_username) {
        this.current_username = current_username;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_new_password() {
        return confirm_new_password;
    }

    public void setConfirm_new_password(String confirm_new_password) {
        this.confirm_new_password = confirm_new_password;
    }

    public String getActual_code() {
        return actual_code;
    }

    public void setActual_code(String actual_code) {
        this.actual_code = actual_code;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public void submitUsernameForForgotPassword() {
        System.out.println("Forgot Function");
        if (current_username.isEmpty()) {
            current_username = "";
            current.executeScript("PF('forgotEmptyField').show();");
        } else if (ubl.getUserData(current_username) != null) {
            forgotUser = ubl.getUserData(current_username);
            actual_code = getRandomNumberString();
            current_username = forgotUser.getUsername();
            send("kushalmajiwala1212@gmail.com", "vqbcqcjhyfkfhdpn", forgotUser.getEmail(), "change password", "Your OTP -> " + actual_code);
            current.executeScript("PF('getVerificationCode').show();");
        } else {
            actual_code = "";
            user_code = "";
            current.executeScript("PF('invalidUsername').show();");
        }
    }

    public void sendOTP() {
        forgotUser = ubl.getUserData(current_username);
        actual_code = getRandomNumberString();
        current_username = forgotUser.getUsername();
        send("kushalmajiwala1212@gmail.com", "vqbcqcjhyfkfhdpn", forgotUser.getEmail(), "change password", "Your OTP -> " + actual_code);
        current.executeScript("PF('newVerificationCode').show();");
    }

    public void checkVerificationCode() {
        if (actual_code.equals(user_code)) {
            new_password = "";
            confirm_new_password = "";
            current.executeScript("PF('editPassword').show();");
        } else {
            current.executeScript("PF('invalidVerificationCode').show();");
        }
    }

    public void editPassword() {
        Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();

        if (new_password.isEmpty() || confirm_new_password.isEmpty()) {
            current.executeScript("PF('emptyPasswordField').show();");
        } else if (new_password.length() < 8) {
            current.executeScript("PF('passwordShortLength').show();");
        } else if (!new_password.equals(confirm_new_password)) {
            current.executeScript("PF('invalidNewConfirmPassword').show();");
        } else {
            encrypted_current_password = pb.generate(new_password.toCharArray());
            forgotUser.setPassword(encrypted_current_password);
            ubl.updateUserData(forgotUser);
            current.executeScript("PF('passwordUpdated').show();");
        }
    }

    public String closeEditPasswordDialog() {
        forgotUser = new Usertb();
        actual_code = "";
        user_code = "";
        new_password = "";
        confirm_new_password = "";
        current_username = "";
        return "login.xhtml?faces-redirect=true";
    }

    public String closePasswordEditedDialog() {
        forgotUser = new Usertb();
        actual_code = "";
        user_code = "";
        new_password = "";
        confirm_new_password = "";
        current_username = "";
        return "login.xhtml?faces-redirect=true";
    }

    public static void send(String from, String password, String to, String sub, String msg) {
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message  
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
