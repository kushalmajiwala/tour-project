
package Controllers;

import UserEJB.UserBeanLocal;
import entity.Tourmaster;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    public AdminController() {
    }
    
    @EJB
    private UserBeanLocal ubl;
    
    public List<Tourmaster> getAllTours()
    {
        return ubl.showMasterData();
    }
    public String redirectAllTours()
    {
        return "adminHome.xhtml?faces-redirect=true";
    }
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
    
    public int getDayDiff(String start_date, String end_date)
    {
        startDate = start_date;
        endDate = end_date;
        
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");   
    
        try
        {
             Date sdate = obj.parse(startDate);
             Date edate = obj.parse(endDate);
             
             time_diff = (int) (edate.getTime() - sdate.getTime());
             day_diff = (time_diff / (1000*60*60*24)) % 365;   
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return day_diff;
    }
    public String addTourRedirect()
    {
        return "addTour.xhtml?faces-redirect=true";
    }
}
