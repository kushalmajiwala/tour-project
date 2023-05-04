package AdminEJB;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AdminBeanLocal {

    public String registerUser(String uname, String fname, String lname, String email, String pass);

    public List getUserData(String uname);

    public String addVehicle(int tourmasterid, String vehicle_name);
    
    public String updateVehicle(int tourmasterid, String vehicle_name);

    public List getVehicle(int tourid);

    public String addPlace(int tourmasterid, String place_name, String place_city, String place_state, String desc, Date sdate, Date edate);

    public String updatePlace(int tourmasterid, String place_name, String place_city, String place_state, String desc, Date sdate, Date edate);
    
    public List getPlaces(int tourid);

    public String deletePlace(int placeid);

    public List showMasterData();

    public String addTourMaster(String tour_title, String tour_pic, Date start_date, Date end_date, Time journey_begin_time, int per_person_price, String pickup_address);

    public String updateTourMaster(String tour_title, String tour_pic, Date start_date, Date end_date, Time journey_begin_time, int per_person_price, String pickup_address);
    
    public String getTourMaster(int tourid);

    public String deleteTourMaster(int tourid);

    public List getTour(String uname);

    public List getPersons(int tourid);

    public List getHistory(String uname);

    public List getGalleryData(String tourname);

    public List getFeedback();

    public List getAllComplaint();

    public List getComplaint(String uname);

    public String deleteComplaint(int cid);

    public String getCart(String uname);
    
    public String updateStatus(int tourid, String uname, String payment_status);
}
