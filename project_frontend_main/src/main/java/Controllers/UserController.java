package Controllers;

import UserEJB.UserBeanLocal;
import entity.Usertb;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import org.primefaces.PrimeFaces;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    public UserController() {
    }
    PrimeFaces current = PrimeFaces.current();

    @EJB
    UserBeanLocal ubl;

    public String redirectLogout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        System.out.println("Session Destroyed...");
        return "gallery.xhtml?faces-redirect=true";
    }
    
    //Edit Profile Work
    Usertb user_info = new Usertb();

    public Usertb getUser_info() {
        return user_info;
    }

    public void setUser_info(Usertb user_info) {
        this.user_info = user_info;
    }

    String edit_username;

    public String getEdit_username() {
        return edit_username;
    }

    public void setEdit_username(String edit_username) {
        this.edit_username = edit_username;
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

    public void editProfileRedirect() {
        user_info = new Usertb();
        edit_username = "";
        edit_username = getCurrentUsername();
        user_info = ubl.getUserData(edit_username);
        current.executeScript("PF('editProfile').show();");
    }

    public void performUserUpdate() {
        System.out.println(user_info.getUsername() + " - " + user_info.getEmail());
        if (user_info.getUsername().isEmpty() || user_info.getFname().isEmpty() || user_info.getLname().isEmpty() || user_info.getEmail().isEmpty()) {
            current.executeScript("PF('editProfileEmptyField').show();");
        } else {
            ubl.updateUserData(user_info);
            current.executeScript("PF('profileEdited').show();");
        }
    }

    public String closeEditProfileDialog() {
        user_info = new Usertb();
        edit_username = "";
        return "userHome.xhtml?faces-redirect=true";
    }

    //Edit Password Work
    Usertb user_info_password = new Usertb();

    public Usertb getUser_info_password() {
        return user_info_password;
    }

    public void setUser_info_password(Usertb user_info_password) {
        this.user_info_password = user_info_password;
    }

    String edit_password_username;

    public String getEdit_password_username() {
        return edit_password_username;
    }

    public void setEdit_password_username(String edit_password_username) {
        this.edit_password_username = edit_password_username;
    }

    String current_password;
    String new_password;
    String confirm_new_password;
    String encrypted_current_password;

    public String getEncrypted_current_password() {
        return encrypted_current_password;
    }

    public void setEncrypted_current_password(String encrypted_current_password) {
        this.encrypted_current_password = encrypted_current_password;
    }

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
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

    public void editPasswordRedirect() {
        user_info_password = new Usertb();
        edit_password_username = "";
        current_password = "";
        new_password = "";
        confirm_new_password = "";
        encrypted_current_password = "";
        edit_password_username = getCurrentUsername();
        user_info_password = ubl.getUserData(edit_password_username);
        current.executeScript("PF('editPassword').show();");
    }

    public void performPasswordUpdate() {
        Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();

        if (!current_password.equals("")) {
            encrypted_current_password = pb.generate(new_password.toCharArray());
        }

        System.out.println(current_password + " - " + new_password + " - " + confirm_new_password);
        if (current_password.isEmpty() || new_password.isEmpty() || confirm_new_password.isEmpty()) {
            current.executeScript("PF('emptyPasswordField').show();");
        } else if (!pb.verify(current_password.toCharArray(), user_info_password.getPassword())) {
            current.executeScript("PF('incorrectCurrentPassword').show();");
        } else if (current_password.equals(new_password)) {
            current.executeScript("PF('sameCurrentPassword').show();");
        } else if (!new_password.equals(confirm_new_password)) {
            current.executeScript("PF('invalidNewConfirmPassword').show();");
        } else {
            user_info_password.setPassword(encrypted_current_password);
            ubl.updateUserData(user_info_password);
            current.executeScript("PF('passwordUpdated').show();");
        }
    }

    public String closeEditPasswordDialog() {
        user_info_password = new Usertb();
        edit_password_username = "";
        current_password = "";
        new_password = "";
        confirm_new_password = "";
        encrypted_current_password = "";
        return "userHome.xhtml?faces-redirect=true";
    }

    public String passwordChangedSuccessfull() {
        HttpSession session = Util.getSession();
        session.invalidate();
        System.out.println("Session Destroyed...");
        return "login.xhtml?faces-redirect=true";
    }

    public String redirectToAdminHome() {
        return "userHome.xhtml?faces-redirect=true";
    }
    
    public String redirectAllTours()
    {
        return "userHome.xhtml?faces-redirect=true";
    }
    public void goTofeedback()
    {
    
    }
}
