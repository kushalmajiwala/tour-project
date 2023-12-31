package Controllers;

import UserEJB.UserBeanLocal;
import entity.Tourmaster;
import entity.Tourplace;
import entity.Vehicle;
import jakarta.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;

@Named(value = "tourmasterController")
@SessionScoped
public class TourmasterController implements Serializable {

    @EJB
    private UserBeanLocal userbean;

    public TourmasterController() {
    }

    public List<Tourmaster> showMasterData() {
        return userbean.showMasterData();
    }
    String picUrl;
    String startDate;
    String endDate;
    String tourTitle;
    int tourPrice;
    int time_diff;
    int days_diff;
    int data_length;
    int day_num;
    String journey_begin_time;

    public String getJourney_begin_time() {
        return journey_begin_time;
    }

    public void setJourney_begin_time(String journey_begin_time) {
        this.journey_begin_time = journey_begin_time;
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

    public int getTime_diff() {
        return time_diff;
    }

    public void setTime_diff(int time_diff) {
        this.time_diff = time_diff;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

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

    public String getRealTime(String actual_time) {
        String time = "";
        try {
            int hourOfDay = Integer.parseInt(actual_time.split(":")[0]);
            int minute = Integer.parseInt(actual_time.split(":")[1]);

            time = ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
        } catch (Exception e) {
            System.out.println(e);
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

    List<Tourplace> all_places;

    public String populate_places(int tourplaceid, String pic_url, String start_date, String end_date, String tour_title, int tour_price, String begin_time) {
        tourmasterid = tourplaceid;
        picUrl = pic_url;
        startDate = start_date;
        endDate = end_date;
        tourTitle = tour_title;
        tourPrice = tour_price;
        journey_begin_time = begin_time;
        day_num = 0;
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date sdate = obj.parse(startDate);
            Date edate = obj.parse(endDate);

            time_diff = (int) (edate.getTime() - sdate.getTime());
            days_diff = (time_diff / (1000 * 60 * 60 * 24)) % 365;
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Done");
        all_places = new ArrayList<>();
        all_places = userbean.getPlaces(tourplaceid);
        data_length = all_places.size();

        System.out.println("All Tours -> " + all_places);
        System.out.println("I am done Here");

        return "home/showTemplate.xhtml?faces-redirect=true";
    }

    public List<Tourplace> getAllPlaces() {
        System.out.println("Getting Places -> " + all_places);
        return all_places;
    }

    public List<Vehicle> getAllVehicleByTourmasterid() {
        System.out.println("Getting Vehicle");
        List<Vehicle> all_vehicle = userbean.getVehicle(tourmasterid);
        System.out.println("All Vehicle -> " + all_vehicle);
        return all_vehicle;
    }
    //Contact working
    String contact_name = "";
    String contact_email = "";
    String contact_message = "";

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_message() {
        return contact_message;
    }

    public void setContact_message(String contact_message) {
        this.contact_message = contact_message;
    }
    PrimeFaces current = PrimeFaces.current();

    public void submitContact() {
        System.out.println(contact_name + " - " + contact_email + " - " + contact_message);
        if (contact_name.isEmpty() || contact_email.isEmpty() || contact_message.isEmpty()) {
            current.executeScript("PF('emptyField').show();");
        } else {
            current.executeScript("PF('sentContact').show();");
        }
    }

    public String closeDialog() {
        contact_name = "";
        contact_email = "";
        contact_message = "";
        return "index.xhtml#contact?faces-redirect=true";
    }
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getAllImages() {
        images = new ArrayList<String>();
        images.add("/images/homeimage.jpg");
        images.add("/images/goa/aguadajail.jpg");
        images.add("/images/goa/beach.jpg");
        images.add("/images/goa/church.jpg");
        images.add("/images/goa/goabeach.jpg");
        images.add("/images/goa/resort.jpg");
        images.add("/images/hp/hidimba.jpg");
        images.add("/images/hp/khajjiar.jpg");
        images.add("/images/hp/rohtang.jpg");
        images.add("/images/hp/shimlachurch.jpg");
        images.add("/images/hp/stadium.jpg");
        images.add("/images/hp/temple.jpg");
        images.add("/images/kashmir/chasmesahi.jpg");
        images.add("/images/kashmir/gulmarg.jpg");
        images.add("/images/kashmir/sonamarg.jpg");
        images.add("/images/kashmir/srinagar.jpg");
        images.add("/images/kashmir/tulipgarden.jpg");
        images.add("/images/kashmir/zeropoint.jpg");
        images.add("/images/kerala/houseboat.jpeg");
        images.add("/images/kerala/kanyakumari.jpg");
        images.add("/images/kerala/munnar.jpg");
        images.add("/images/kerala/rameshvaram.jpg");
        images.add("/images/kerala/waterfall.jpg");
        images.add("/images/kerala/wayanad.jpg");
        
        return images;
    }

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        images.add("/images/homeimage.jpg");
        images.add("/images/goa/aguadajail.jpg");
        images.add("/images/goa/beach.jpg");
        images.add("/images/goa/church.jpg");
        images.add("/images/goa/goabeach.jpg");
        images.add("/images/goa/resort.jpg");
    }

}
