
package UserEJB;

import entity.Complaint;
import entity.Feedback;
import entity.Person;
import entity.Tour;
import entity.Tourmaster;
import entity.Tourplace;
import entity.Usertb;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserBeanLocal {
    
    public void registerUser(String uname, String fname, String lname, String email, String pass);

    public Usertb getUserData(String uname);

    public void updateUserData(Usertb u);

    public List getVehicle(int tourid);

    public List<Tourplace> getPlaces(int tourid);

    public List showMasterData();

    public Tourmaster getTourMaster(int tourid);
    
    public Tour addTour(Tour t);
    
    public String updateTour(int tourmasterid, String username, String payment_method);
    
    public List getTour(String uname);
    
    public void deleteTour(int tourid);
    
    public void addPerson(Person p);
    
    public void updatePerson(Person p);
    
    public List getPersons(int tourid);
    
    public void deletePerson(int pid);
            
    public String addHistory(int tourid, String uname);
    
    public List getHistory(String uname);
    
    public String deleteHistory(int hid);
    
    public List getGalleryData(String tourname);
    
    public void addFeedback(Feedback f);
    
    public void addComplaint(Complaint c);
    
    public List getComplaint(String uname);
    
    public String deleteComplaint(int cid);
    
    public String addCart(int tourid, String uname, String payment_status);
   
    public List getCart(String uname);
    
    public String deleteCart(int cartid);
    
    public List getFeedback();
}
