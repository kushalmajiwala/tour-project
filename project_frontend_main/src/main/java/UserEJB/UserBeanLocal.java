
package UserEJB;

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

    public String getTourMaster(int tourid);
    
    public String addTour(int tourmasterid, String username, String payment_method);
    
    public String updateTour(int tourmasterid, String username, String payment_method);
    
    public List getTour(String uname);
    
    public String deleteTour(int tourid);
    
    public String addPerson(int tourid, String uname, String fname, String lname, String email, String pno, Date dob, String gender);
    
    public String updatePerson(int tourid, String uname, String fname, String lname, String email, String pno, Date dob, String gender);
    
    public List getPersons(int tourid);
    
    public String deletePerson(int pid);
            
    public String addHistory(int tourid, String uname);
    
    public List getHistory(String uname);
    
    public String deleteHistory(int hid);
    
    public List getGalleryData(String tourname);
    
    public String addFeedback(String uname, String rating, String subject, String message);
    
    public String addComplaint(String uname, String subject, String message);
    
    public List getComplaint(String uname);
    
    public String deleteComplaint(int cid);
    
    public String addCart(int tourid, String uname, String payment_status);
   
    public String getCart(String uname);
    
    public String deleteCart(int cartid);
}
