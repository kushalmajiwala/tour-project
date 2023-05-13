package Controllers;

import UserEJB.UserBeanLocal;
import entity.Feedback;
import entity.Tourmaster;
import entity.Tourplace;
import entity.Usertb;
import entity.Vehicle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public String redirectAllTours() {
        return "userHome.xhtml?faces-redirect=true";
    }

    public List<Tourmaster> getAllTours() {
        return ubl.showMasterData();
    }
    //Day difference
    String startDate;
    String endDate;
    int time_diff;
    int day_diff;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTime_diff() {
        return time_diff;
    }

    public void setTime_diff(int time_diff) {
        this.time_diff = time_diff;
    }

    public int getDay_diff() {
        return day_diff;
    }

    public void setDay_diff(int day_diff) {
        this.day_diff = day_diff;
    }

    public int getDayDiff(String start_date, String end_date) {
        startDate = start_date;
        endDate = end_date;

        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date sdate = obj.parse(startDate);
            Date edate = obj.parse(endDate);

            time_diff = (int) (edate.getTime() - sdate.getTime());
            day_diff = (time_diff / (1000 * 60 * 60 * 24)) % 365;
        } catch (Exception e) {
            System.out.println(e);
        }
        return day_diff;
    }
    //showUserTemplate
    String sDate;
    String eDate;
    String tourTitle;
    int tourPrice;
    int times_diff;
    int days_diff;
    int data_length;
    int day_num;
    String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public int getTimes_diff() {
        return times_diff;
    }

    public void setTimes_diff(int times_diff) {
        this.times_diff = times_diff;
    }

    int tourmasterid;

    public int getTourmasterid() {
        return tourmasterid;
    }

    public void setTourmasterid(int tourmasterid) {
        this.tourmasterid = tourmasterid;
    }

    public int getDay_num() {
        return day_num;
    }

    public void setDay_num(int day_num) {
        this.day_num = day_num;
    }

    public int getData_length() {
        return data_length;
    }

    public void setData_length(int data_length) {
        this.data_length = data_length;
    }

    public int getDays_diff() {
        return days_diff;
    }

    public void setDays_diff(int days_diff) {
        this.days_diff = days_diff;
    }

    public int getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(int tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }

    List<String> temp = new ArrayList<>();

    public List<String> tempFunc() {
        temp = new ArrayList<>();
        temp.add("Surat");
        return temp;
    }

    List<Tourplace> all_places;

    public String populate_places(int tourplaceid, String pic_url, String start_date, String end_date, String tour_title, int tour_price) {
        tourmasterid = tourplaceid;
        sDate = start_date;
        eDate = end_date;
        picUrl = pic_url;
        tourTitle = tour_title;
        tourPrice = tour_price;
        day_num = 0;
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date sdate = obj.parse(sDate);
            Date edate = obj.parse(eDate);

            times_diff = (int) (edate.getTime() - sdate.getTime());
            days_diff = (times_diff / (1000 * 60 * 60 * 24)) % 365;
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Done");
        all_places = new ArrayList<>();
        all_places = ubl.getPlaces(tourplaceid);
        data_length = all_places.size();

        System.out.println("All Tours -> " + all_places);
        System.out.println("I am done Here");

        return "showUserTemplate.xhtml?faces-redirect=true";
    }

    public List<Tourplace> getAllPlaces() {
        System.out.println("Getting Places -> " + all_places);
        return all_places;
    }

    public List<Vehicle> getAllVehicleByTourmasterid() {
        System.out.println("Getting Vehicle");
        List<Vehicle> all_vehicle = ubl.getVehicle(tourmasterid);
        System.out.println("All Vehicle -> " + all_vehicle);
        return all_vehicle;
    }

    public String redirectToUserHome() {
        return "userHome.xhtml?faces-redirect=true";
    }
    //Give Feedback
    int rating = 0;
    String subject;
    String message;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void openGiveFeedbackDialog() {
        rating = 0;
        subject = "";
        message = "";
        current.executeScript("PF('giveFeedback').show();");
    }

    public String closeGiveFeedbackDialog() {
        rating = 0;
        subject = "";
        message = "";
        return "userHome.xhtml?faces-redirect=true";
    }

    public void submitRating() {
        String current_rating = "";
        System.out.println(rating + " - " + subject + " - " + message);
        if (rating == 0 || subject.isEmpty() || message.isEmpty()) {
            current.executeScript("PF('giveFeedbackEmptyField').show();");
        } else {
            switch (rating) {
                case 1:
                    current_rating = "one";
                    break;
                case 2:
                    current_rating = "two";
                    break;
                case 3:
                    current_rating = "three";
                    break;
                case 4:
                    current_rating = "four";
                    break;
                case 5:
                    current_rating = "five";
                    break;
                default:
                    break;
            }
            Feedback f = new Feedback();
            f.setUsername(getCurrentUsername());
            f.setRating(current_rating);
            f.setSubject(subject);
            f.setMessage(message);
            ubl.addFeedback(f);
            current.executeScript("PF('feedbackSubmitted').show();");
            rating = 0;
            subject = "";
            message = "";
        }
    }
    //Rating Work
    String star_color1 = "#8c8c8c";
    String star_color2 = "#8c8c8c";
    String star_color3 = "#8c8c8c";
    String star_color4 = "#8c8c8c";
    String star_color5 = "#8c8c8c";
    float avg_rating = 0;

    public float getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(float avg_rating) {
        this.avg_rating = avg_rating;
    }

    public String getStar_color1() {
        return star_color1;
    }

    public void setStar_color1(String star_color1) {
        this.star_color1 = star_color1;
    }

    public String getStar_color2() {
        return star_color2;
    }

    public void setStar_color2(String star_color2) {
        this.star_color2 = star_color2;
    }

    public String getStar_color3() {
        return star_color3;
    }

    public void setStar_color3(String star_color3) {
        this.star_color3 = star_color3;
    }

    public String getStar_color4() {
        return star_color4;
    }

    public void setStar_color4(String star_color4) {
        this.star_color4 = star_color4;
    }

    public String getStar_color5() {
        return star_color5;
    }

    public void setStar_color5(String star_color5) {
        this.star_color5 = star_color5;
    }

    public float getAverageRating() {
        avg_rating = 0;
        int total_feedback = 0;
        int temp_num = 0;
        List<Feedback> all_feedback = new ArrayList<>();
        all_feedback = ubl.getFeedback();
        total_feedback = all_feedback.size();
        for (Feedback f : all_feedback) {
            switch (f.getRating()) {
                case "one":
                    avg_rating += 1;
                    break;
                case "two":
                    avg_rating += 2;
                    break;
                case "three":
                    avg_rating += 3;
                    break;
                case "four":
                    avg_rating += 4;
                    break;
                case "five":
                    avg_rating += 5;
                    break;
                default:
                    break;
            }
        }
        avg_rating = avg_rating / total_feedback;
        if (avg_rating > 0 && avg_rating <= 1.5) {
            star_color1 = "#1D3AB8";
            star_color2 = "#8c8c8c";
            star_color3 = "#8c8c8c";
            star_color4 = "#8c8c8c";
            star_color5 = "#8c8c8c";
        } else if (avg_rating > 1.5 && avg_rating <= 2.5) {
            star_color1 = "#1D3AB8";
            star_color2 = "#1D3AB8";
            star_color3 = "#8c8c8c";
            star_color4 = "#8c8c8c";
            star_color5 = "#8c8c8c";
        } else if (avg_rating > 2.5 && avg_rating <= 3.5) {
            star_color1 = "#1D3AB8";
            star_color2 = "#1D3AB8";
            star_color3 = "#1D3AB8";
            star_color4 = "#8c8c8c";
            star_color5 = "#8c8c8c";
        } else if (avg_rating > 3.5 && avg_rating <= 4.5) {
            star_color1 = "#1D3AB8";
            star_color2 = "#1D3AB8";
            star_color3 = "#1D3AB8";
            star_color4 = "#1D3AB8";
            star_color5 = "#8c8c8c";
        } else if (avg_rating > 4.5) {
            star_color1 = "#1D3AB8";
            star_color2 = "#1D3AB8";
            star_color3 = "#1D3AB8";
            star_color4 = "#1D3AB8";
            star_color5 = "#1D3AB8";
        }
        return round(avg_rating, 1);
    }

    private static float round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }
}
