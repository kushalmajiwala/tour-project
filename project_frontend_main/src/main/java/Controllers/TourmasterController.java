package Controllers;

import UserEJB.UserBeanLocal;
import entity.Tourmaster;
import entity.Tourplace;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

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
    public List<String> tempFunc()
    {
        temp = new ArrayList<>();
        temp.add("Surat");
        return temp;
    }
    
    List<Tourplace> all_places;
    public String populate_places(int tourplaceid, String pic_url, String start_date, String end_date, String tour_title, int tour_price)
    {
        picUrl = pic_url;
        startDate = start_date;
        endDate = end_date;
        tourTitle = tour_title;
        tourPrice = tour_price;
        day_num = 0;
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");   
    
        try
        {
             Date sdate = obj.parse(startDate);
             Date edate = obj.parse(endDate);
             
             time_diff = (int) (edate.getTime() - sdate.getTime());
             days_diff = (time_diff / (1000*60*60*24)) % 365;   
        }
        catch(Exception e)
        {
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
    
    public List<Tourplace> getAllPlaces()
    {
        System.out.println("Getting Places -> " + all_places);
        return all_places;
    }
}
