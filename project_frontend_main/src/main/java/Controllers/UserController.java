package Controllers;

import UserEJB.UserBeanLocal;
import entity.Complaint;
import entity.Feedback;
import entity.History;
import entity.Person;
import entity.Tour;
import entity.Tourmaster;
import entity.Tourplace;
import entity.Usertb;
import entity.Vehicle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

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
        } else if (!user_info.getEmail().contains("@")) {
            current.executeScript("PF('invalidEmail').show();");
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
        } else if (new_password.length() < 8) {
            current.executeScript("PF('passwordShortLength').show();");
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

    List<Tourmaster> all_tours = new ArrayList<>();
    List<Tourmaster> searched_tours = new ArrayList<>();

    public List<Tourmaster> getAll_tours() {
        return all_tours;
    }

    public void setAll_tours(List<Tourmaster> all_tours) {
        this.all_tours = all_tours;
    }

    public List<Tourmaster> getSearched_tours() {
        return searched_tours;
    }

    public void setSearched_tours(List<Tourmaster> searched_tours) {
        this.searched_tours = searched_tours;
    }

    public List<Tourmaster> getAllTours() {

        searched_tours = new ArrayList<>();
        all_tours = ubl.showMasterData();
        if (selected_tour == null) {
            return all_tours;
        } else if (selected_tour.equals("")) {
            return all_tours;
        } else {
            for (Tourmaster tm : all_tours) {
                if (tm.getTour_title().equals(selected_tour)) {
                    searched_tours.add(tm);
                    break;
                }
            }
            return searched_tours;
        }
    }

    public List<Tourmaster> getTours() {
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

    public String getRealTime(String actual_time) {
        String time = "";
        if (!actual_time.equals("")) {
            try {
                int hourOfDay = Integer.parseInt(actual_time.split(":")[0]);
                int minute = Integer.parseInt(actual_time.split(":")[1]);

                time = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return time;
    }

    public String meetingTime(String actual_time) {
        String time = "";
        try {
            int hourOfDay = Integer.parseInt(actual_time.split(":")[0]) - 1;
            int minute = Integer.parseInt(actual_time.split(":")[1]);

            time = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
        } catch (Exception e) {
            System.out.println(e);
        }
        return time;
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
    String journey_begin_time;

    public String getJourney_begin_time() {
        return journey_begin_time;
    }

    public void setJourney_begin_time(String journey_begin_time) {
        this.journey_begin_time = journey_begin_time;
    }

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

    public String populate_places(int tourplaceid, String pic_url, String start_date, String end_date, String tour_title, int tour_price, String begin_time) {
        tourmasterid = tourplaceid;
        sDate = start_date;
        eDate = end_date;
        picUrl = pic_url;
        tourTitle = tour_title;
        tourPrice = tour_price;
        journey_begin_time = begin_time;
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
    //Toggle Theme Change
    String color = "black";
    String bgcolor = "white";
    String themeIcon = "fa-solid fa-moon fa-beat-fade";
    String themeName = "Dark Mode";
    String txtColor = "black";
    String cardBgColor = "white";
    String loopBgColor = "#F3F3F3";
    String sidebarBgColor = "white";
    String menuTitleBgColor = "white";
    String menuTitleColor = "black";
    String navbarContentBgColor = "white";
    String navbarContentColor = "#484C8D";
    String navbarContentBorderColor = "0.5px solid #C4C4F2";
    String hoverBgColor = "#474792";

    public String getHoverBgColor() {
        return hoverBgColor;
    }

    public void setHoverBgColor(String hoverBgColor) {
        this.hoverBgColor = hoverBgColor;
    }

    public String getNavbarContentBgColor() {
        return navbarContentBgColor;
    }

    public void setNavbarContentBgColor(String navbarContentBgColor) {
        this.navbarContentBgColor = navbarContentBgColor;
    }

    public String getNavbarContentColor() {
        return navbarContentColor;
    }

    public void setNavbarContentColor(String navbarContentColor) {
        this.navbarContentColor = navbarContentColor;
    }

    public String getNavbarContentBorderColor() {
        return navbarContentBorderColor;
    }

    public void setNavbarContentBorderColor(String navbarContentBorderColor) {
        this.navbarContentBorderColor = navbarContentBorderColor;
    }

    public String getMenuTitleBgColor() {
        return menuTitleBgColor;
    }

    public void setMenuTitleBgColor(String menuTitleBgColor) {
        this.menuTitleBgColor = menuTitleBgColor;
    }

    public String getMenuTitleColor() {
        return menuTitleColor;
    }

    public void setMenuTitleColor(String menuTitleColor) {
        this.menuTitleColor = menuTitleColor;
    }

    public String getSidebarBgColor() {
        return sidebarBgColor;
    }

    public void setSidebarBgColor(String sidebarBgColor) {
        this.sidebarBgColor = sidebarBgColor;
    }

    public String getCardBgColor() {
        return cardBgColor;
    }

    public void setCardBgColor(String cardBgColor) {
        this.cardBgColor = cardBgColor;
    }

    public String getLoopBgColor() {
        return loopBgColor;
    }

    public void setLoopBgColor(String loopBgColor) {
        this.loopBgColor = loopBgColor;
    }

    public String getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
    }

    public String getThemeIcon() {
        return themeIcon;
    }

    public void setThemeIcon(String themeIcon) {
        this.themeIcon = themeIcon;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public void changeTheme() {
        System.out.println("Changing Theme");
        System.out.println(color);
        if (color.equals("black")) {
            color = "white";
            bgcolor = "#343434";
            themeIcon = "fa-solid fa-sun fa-beat-fade";
            themeName = "Light Mode";
            txtColor = "white";
            cardBgColor = "#262E47";
            loopBgColor = "#bfbfbf";
            sidebarBgColor = "black";
            menuTitleBgColor = "black";
            menuTitleColor = "white";
            navbarContentBgColor = "black";
            navbarContentColor = "white";
            navbarContentBorderColor = "none";
            hoverBgColor = "#2C2C2C";
        } else {
            color = "black";
            bgcolor = "white";
            themeIcon = "fa-solid fa-moon fa-beat-fade";
            themeName = "Dark Mode";
            txtColor = "black";
            cardBgColor = "white";
            loopBgColor = "#F3F3F3";
            sidebarBgColor = "white";
            menuTitleBgColor = "white";
            menuTitleColor = "black";
            navbarContentBgColor = "white";
            navbarContentColor = "#484C8D";
            navbarContentBorderColor = "0.5px solid #C4C4F2";
            hoverBgColor = "#474792";
        }
//        return "userHome.xhtml?faces-redirect=true";
    }
    //Add Complaint Working
    Complaint cp = new Complaint();

    public Complaint getCp() {
        return cp;
    }

    public void setCp(Complaint cp) {
        this.cp = cp;
    }

    public void openAddComplaintDialog() {
        cp = new Complaint();
        current.executeScript("PF('addComplaint').show();");
    }

    public void performAddComplaint() {
        cp.setUsername(getCurrentUsername());
        System.out.println(cp.getSubject() + " - " + cp.getMessage() + " - " + cp.getUsername());
        if (cp.getSubject().isEmpty() || cp.getMessage().isEmpty()) {
            current.executeScript("PF('addComplaintEmptyField').show();");
        } else {
            ubl.addComplaint(cp);
            current.executeScript("PF('complaintAdded').show();");
        }
    }

    public String closeAddComplaintDialog() {
        cp = new Complaint();
        return "userHome.xhtml?faces-redirect=true";
    }
    //Search For Userhome
    List<String> tour_title = new ArrayList<>();
    String selected_tour = "";

    public List<String> getTour_title() {
        return tour_title;
    }

    public void setTour_title(List<String> tour_title) {
        this.tour_title = tour_title;
    }

    public String getSelected_tour() {
        return selected_tour;
    }

    public void setSelected_tour(String selected_tour) {
        this.selected_tour = selected_tour;
    }

    public List<String> populateTourTitle() {
        tour_title = new ArrayList<>();
        for (Tourmaster tm : getTours()) {
            tour_title.add(tm.getTour_title());
        }
        return tour_title;
    }

    public String searchedTour(SelectEvent e) {
        System.out.println(e);
        return "userHome.xhtml?faces-redirect=true";
    }

    public String refreshPage() {
        return "userHome.xhtml?faces-redirect=true";
    }
    //Booking and Cart Working
    Tourmaster booking_master = new Tourmaster();

    public Tourmaster getBooking_master() {
        return booking_master;
    }

    public void setBooking_master(Tourmaster booking_master) {
        this.booking_master = booking_master;
    }
    int book_tourmasterid = 0;

    public int getBook_tourmasterid() {
        return book_tourmasterid;
    }

    public void setBook_tourmasterid(int book_tourmasterid) {
        this.book_tourmasterid = book_tourmasterid;
    }

    public void openBookTourDialog(Tourmaster tm) {
        book_tourmasterid = tm.getTourmasterid();
        booking_master = new Tourmaster();
        System.out.println(tm.getTour_title());
        booking_master = tm;
        current.executeScript("PF('bookTour').show();");
    }
    Tour tour = new Tour();
    Person person = new Person();

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    Date insert_dob;

    public Date getInsert_dob() {
        return insert_dob;
    }

    public void setInsert_dob(Date insert_dob) {
        this.insert_dob = insert_dob;
    }

    public void performBooking() {
        System.out.println(book_tourmasterid);
        try {
            java.sql.Date actual_dob = new java.sql.Date(insert_dob.getTime());
            System.out.println("sqlDate:" + actual_dob);

            person.setDob(actual_dob);

        } catch (Exception e) {
            System.out.println(e);
        }

        if (tour.getPayment_method() == null || tour.getPayment_method().isEmpty() || person.getFname().isEmpty() || person.getLname().isEmpty() || person.getEmail().isEmpty() || person.getDob() == null || person.getPhoneno().isEmpty() || person.getGender() == null || person.getGender().isEmpty()) {
            current.executeScript("PF('bookingEmptyField').show();");
        } else {
            //Adding Tour
            tour.setTourmasterid(book_tourmasterid);
            tour.setUsername(getCurrentUsername());
            tour.setPayment_status("remaining");
            Tour response = ubl.addTour(tour);
            System.out.println("Added Cart Id -> " + response.getTourid());

            //Adding Person
            person.setTourid(response.getTourid());
            person.setUsername(getCurrentUsername());
            ubl.addPerson(person);
            current.executeScript("PF('cartAdded').show();");
        }
    }

    public String closeBookTourDialog() {
        insert_dob = null;
        tour = new Tour();
        person = new Person();
        booking_master = new Tourmaster();
        return "userHome.xhtml?faces-redirect=true";
    }

    //Getting Cart Size
    public int total_cart_items() {
        System.out.println(ubl.getTour(getCurrentUsername()));
        return ubl.getTour(getCurrentUsername()).size();
    }

    public List<Tour> getAllCartItems() {
        return ubl.getTour(getCurrentUsername());
    }

    public String cartRedirect() {
        return "myCart.xhtml?faces-redirect=true";
    }

    //Working on Cart Details
    public String getCartTourPic(int tourmasterid) {
        return ubl.getTourMaster(tourmasterid).getTour_pic();
    }

    public String getCartTourName(int tourmasterid) {
        return ubl.getTourMaster(tourmasterid).getTour_title();
    }

    public Date getCartTourStartDate(int tourmasterid) {
        return ubl.getTourMaster(tourmasterid).getStart_date();
    }

    public Date getCartTourEndDate(int tourmasterid) {
        return ubl.getTourMaster(tourmasterid).getEnd_date();
    }

    public int getCartTourPrice(int tourmasterid) {
        return ubl.getTourMaster(tourmasterid).getPer_person_price();
    }

    public String getCartTourTime(int tourmasterid) {
        return ubl.getTourMaster(tourmasterid).getJourney_begin_time();
    }

    public String getCartTourAddress(int tourmasterid) {
        return ubl.getTourMaster(tourmasterid).getPickup_address();
    }

    public int getTotalPerson(int tourmasterid) {
        return ubl.getPersons(tourmasterid).size();
    }
    //Cancel or Remove Cart Item
    String cancel_button_name = "Remove Cart Item";
    String status_color;

    public String getStatus_color() {
        return status_color;
    }

    public void setStatus_color(String status_color) {
        this.status_color = status_color;
    }

    public String getCancel_button_name() {
        return cancel_button_name;
    }

    public void setCancel_button_name(String cancel_button_name) {
        this.cancel_button_name = cancel_button_name;
    }

    public String getRemoveCartButtonName(String payment_status) {
        if (payment_status.equals("done")) {
            cancel_button_name = "Cancel Booking";
        } else if (payment_status.equals("remaining")) {
            cancel_button_name = "Remove Cart Item";
        }
        return cancel_button_name;
    }

    public String getStatusColor(String payment_status) {
        if (payment_status.equals("done")) {
            status_color = "#29C90F";
        } else if (payment_status.equals("remaining")) {
            status_color = "red";
        }
        return status_color;
    }

    int add_person_tourid;

    public int getAdd_person_tourid() {
        return add_person_tourid;
    }

    public void setAdd_person_tourid(int add_person_tourid) {
        this.add_person_tourid = add_person_tourid;
    }

    public void openAddPersonDialog(Tour t) {
        System.out.println("Adding person with -> " + t.getTourid());
        add_person_tourid = t.getTourid();
        booking_master = ubl.getTourMaster(t.getTourmasterid());
        current.executeScript("PF('addPerson').show();");
    }

    public void performAddPerson() {
        try {
            java.sql.Date actual_dob = new java.sql.Date(insert_dob.getTime());
            System.out.println("sqlDate:" + actual_dob);

            person.setDob(actual_dob);

        } catch (Exception e) {
            System.out.println(e);
        }

        if (person.getFname().isEmpty() || person.getLname().isEmpty() || person.getEmail().isEmpty() || person.getDob() == null || person.getPhoneno().isEmpty() || person.getGender() == null || person.getGender().isEmpty()) {
            current.executeScript("PF('addPersonEmptyField').show();");
        } else {
            //Adding Person
            person.setTourid(add_person_tourid);
            person.setUsername(getCurrentUsername());
            ubl.addPerson(person);
            current.executeScript("PF('personAdded').show();");
        }
    }

    public String closeAddPersonDialog() {
        insert_dob = null;
        tour = new Tour();
        person = new Person();
        add_person_tourid = 0;
        booking_master = new Tourmaster();
        return "myCart.xhtml?faces-redirect=true";
    }
    //Removing Cart Item
    int delete_cart_tourid;
    String delete_cart_payment_status;

    public int getDelete_cart_tourid() {
        return delete_cart_tourid;
    }

    public void setDelete_cart_tourid(int delete_cart_tourid) {
        this.delete_cart_tourid = delete_cart_tourid;
    }

    public String getDelete_cart_payment_status() {
        return delete_cart_payment_status;
    }

    public void setDelete_cart_payment_status(String delete_cart_payment_status) {
        this.delete_cart_payment_status = delete_cart_payment_status;
    }

    public void openDeleteCartDialog(Tour t) {
        delete_cart_tourid = t.getTourid();
        delete_cart_payment_status = t.getPayment_status();

        if (delete_cart_payment_status.equals("done")) {
            current.executeScript("PF('cancelBooking').show();");
        } else if (delete_cart_payment_status.equals("remaining")) {
            current.executeScript("PF('removeCart').show();");
        }
    }

    public void performCartRemove() {
        if (delete_cart_payment_status.equals("done")) {
            ubl.deleteTour(delete_cart_tourid);
            current.executeScript("PF('bookingCancelled').show();");
        } else if (delete_cart_payment_status.equals("remaining")) {
            ubl.deleteTour(delete_cart_tourid);
            current.executeScript("PF('cartRemoved').show();");
        }
    }

    public String closeRemoveCartDialog() {
        delete_cart_tourid = 0;
        delete_cart_payment_status = "";
        return "myCart.xhtml?faces-redirect=true";
    }
    //Edit Person Working
    List<Person> all_person = new ArrayList<>();
    Date[] update_person_dob;

    public Date[] getUpdate_person_dob() {
        return update_person_dob;
    }

    public void setUpdate_person_dob(Date[] update_person_dob) {
        this.update_person_dob = update_person_dob;
    }

    public List<Person> getAll_person() {
        return all_person;
    }

    public void setAll_person(List<Person> all_person) {
        this.all_person = all_person;
    }

    public String redirectEditPerson(int tourid) {
        all_person = new ArrayList<>();
        all_person = ubl.getPersons(tourid);

        update_person_dob = new Date[all_person.size()];

        for (int i = 0; i < all_person.size(); i++) {
            update_person_dob[i] = new java.util.Date(all_person.get(i).getDob().getTime());
        }
        return "updatePerson.xhtml?faces-redirect=true";
    }

    public List<Person> getAllPerson() {
        return all_person;
    }

    public void performPersonUpdate(Person p, Date dob) {

        if (p.getFname().isEmpty() || p.getLname().isEmpty() || p.getEmail().isEmpty() || dob == null || p.getPhoneno().isEmpty() || p.getGender() == null || p.getGender().isEmpty()) {
            current.executeScript("PF('editPersonEmptyField').show();");
        } else {
            try {
                java.sql.Date actual_dob = new java.sql.Date(dob.getTime());
                System.out.println("sqlDate:" + actual_dob);

                p.setDob(actual_dob);

                //Editing Person
                ubl.updatePerson(p);
                current.executeScript("PF('personEdited').show();");

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    int delete_personid;

    public int getDelete_personid() {
        return delete_personid;
    }

    public void setDelete_personid(int delete_personid) {
        this.delete_personid = delete_personid;
    }

    public void openPersonDeleteDialog(int personid) {
        delete_personid = personid;
        current.executeScript("PF('personDeleteConfirm').show();");
    }

    public void performPersonDelete() {
        ubl.deletePerson(delete_personid);
        current.executeScript("PF('personDeleted').show();");
    }

    public String closeDeletePersonDialog() {
        return "updatePerson.xhtml?faces-redirect=true";
    }

    public String closeEditPersonDialog() {
        all_person = new ArrayList<>();
        update_person_dob = null;
        return "myCart.xhtml?faces-redirect=true";
    }

    public String personDeleted() {
        return "myCart.xhtml?faces-redirect=true";
    }

    public String redirectFromEditPersonToCart() {
        return "myCart.xhtml?faces-redirect=true";
    }

    //View History Working
    public String redirectViewHistory() {
        getAndUpdateHistory();
        return "viewHistory.xhtml?faces-redirect=true";
    }
    List<History> user_history;

    public List<History> getUser_history() {
        return user_history;
    }

    public void setUser_history(List<History> user_history) {
        this.user_history = user_history;
    }

    public void getAndUpdateHistory() {
        List<Tour> all_cart_items = ubl.getTour(getCurrentUsername());
        List<Tourmaster> all_tour = new ArrayList<>();
        user_history = new ArrayList<>();

        for (Tour t : all_cart_items) {
            Tourmaster tm = ubl.getTourMaster(t.getTourmasterid());
            all_tour.add(tm);
            System.out.println("This is getting tour working -> " + tm.getTour_title());
            boolean isDatePast = isDatePast(tm.getEnd_date().toString(), "yyyy-MM-dd");
            if (isDatePast && t.getPayment_status().equals("done")) {
                System.out.println(t.getTourid() + " date is past...");
                History h = new History();
                h.setTourid(t.getTourid());
                h.setUsername(getCurrentUsername());
                user_history.add(h);
            } else {
                System.out.println(t.getTourid() + " date is yet to come...");
            }
        }
        try {
            ubl.deleteHistoryByUsername(getCurrentUsername());
            for (History hist : user_history) {
                ubl.addHistory(hist);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("This is my history -> " + user_history);
    }

    public static boolean isDatePast(final String date, final String dateFormat) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate inputDate = LocalDate.parse(date, dtf);

        return inputDate.isBefore(localDate);
    }

    public List<History> getHistory() {
        return ubl.getHistory(getCurrentUsername());
    }

    public String getHistoryTourPic(int tid) {
        int current_masterid = 0;
        List<Tour> all_tours = ubl.getTour(getCurrentUsername());
        for (Tour t : all_tours) {
            if (t.getTourid() == tid) {
                current_masterid = t.getTourmasterid();
            }
        }
        return ubl.getTourMaster(current_masterid).getTour_pic();
    }

    public String getHistoryTourName(int tid) {
        int current_masterid = 0;
        List<Tour> all_tours = ubl.getTour(getCurrentUsername());
        for (Tour t : all_tours) {
            if (t.getTourid() == tid) {
                current_masterid = t.getTourmasterid();
            }
        }
        return ubl.getTourMaster(current_masterid).getTour_title();
    }

    public String getHistoryTourStartDate(int tid) {
        int current_masterid = 0;
        List<Tour> all_tours = ubl.getTour(getCurrentUsername());
        for (Tour t : all_tours) {
            if (t.getTourid() == tid) {
                current_masterid = t.getTourmasterid();
            }
        }
        return ubl.getTourMaster(current_masterid).getStart_date().toString();
    }

    public int getHistoryTourPrice(int tid) {
        int current_masterid = 0;
        List<Tour> all_tours = ubl.getTour(getCurrentUsername());
        for (Tour t : all_tours) {
            if (t.getTourid() == tid) {
                current_masterid = t.getTourmasterid();
            }
        }
        return ubl.getTourMaster(current_masterid).getPer_person_price();
    }

    public String getHistoryTourEndDate(int tid) {
        int current_masterid = 0;
        List<Tour> all_tours = ubl.getTour(getCurrentUsername());
        for (Tour t : all_tours) {
            if (t.getTourid() == tid) {
                current_masterid = t.getTourmasterid();
            }
        }
        return ubl.getTourMaster(current_masterid).getEnd_date().toString();
    }

    public String getHistoryPaymentMethod(int tid) {
        String method = "";
        List<Tour> all_tours = ubl.getTour(getCurrentUsername());
        for (Tour t : all_tours) {
            if (t.getTourid() == tid) {
                method = t.getPayment_method();
            }
        }
        return method;
    }
    //Working on Deleting History
    int delete_historyid;

    public int getDelete_historyid() {
        return delete_historyid;
    }

    public void setDelete_historyid(int delete_historyid) {
        this.delete_historyid = delete_historyid;
    }

    public void openDeleteHistoryDialog(int hid) {
        delete_historyid = hid;
        current.executeScript("PF('historyDeleteConfirm').show();");
    }

    public void performHistoryDelete() {
        ubl.deleteHistory(delete_historyid);
        current.executeScript("PF('historyDeleted').show();");
    }

    public String closeDeleteHistoryDialog() {
        delete_historyid = 0;
        return "viewHistory.xhtml?faces-redirect=true";
    }
}
