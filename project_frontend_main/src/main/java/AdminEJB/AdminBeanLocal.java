package AdminEJB;

import entity.Tourmaster;
import entity.Tourplace;
import entity.Usertb;
import entity.Vehicle;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AdminBeanLocal {

    public String registerUser(String uname, String fname, String lname, String email, String pass);

    public Usertb getUserData(String uname);
    
    public void updateUserData(Usertb u);

    public void addVehicle(Vehicle v);
    
    public void updateVehicle(Vehicle v);

    public List getVehicle(int tourid);
    
    public void deleteVehicle(int vehicleid);

    public void addPlace(Tourplace tp);

    public void updatePlace(Tourplace tp);
    
    public List getPlaces(int tourid);

    public void deletePlace(int placeid);

    public List showMasterData();

    public void addTourMaster(Tourmaster tm);

    public void updateTourMaster(Tourmaster tm);
    
    public String getTourMaster(int tourid);

    public void deleteTourMaster(int tourid);

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
    
    public List getGroupByGroupname(String gname);
}
