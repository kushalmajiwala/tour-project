package AdminEJB;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Gallery;
import entity.Tourmaster;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class AdminBean implements AdminBeanLocal {

    @Override
    public String registerUser(String uname, String fname, String lname, String email, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getUserData(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addVehicle(int tourmasterid, String vehicle_name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateVehicle(int tourmasterid, String vehicle_name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getVehicle(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addPlace(int tourmasterid, String place_name, String place_city, String place_state, String desc, Date sdate, Date edate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updatePlace(int tourmasterid, String place_name, String place_city, String place_state, String desc, Date sdate, Date edate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getPlaces(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deletePlace(int placeid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List showMasterData() {
        List mylist = new ArrayList();
        try {
            String url = "http://localhost:9090/tourmaster/getalltour";
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            Tourmaster[] myrec = mapper.readValue(response.body().toString(), Tourmaster[].class);
            for (Tourmaster rec : myrec) {
                mylist.add(rec);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return mylist;
    }

    @Override
    public void addTourMaster(Tourmaster tm) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(tm);

            String url = "http://localhost:9090/tourmaster/addtour";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String updateTourMaster(String tour_title, String tour_pic, Date start_date, Date end_date, Time journey_begin_time, int per_person_price, String pickup_address) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTourMaster(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteTourMaster(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getTour(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getPersons(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getHistory(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getGalleryData(String tourname) {
        List gallery_record = new ArrayList();
        try {
            String url = "http://localhost:9090/gallery/getgallery/" + tourname;
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            Gallery[] myrec = mapper.readValue(response.body().toString(), Gallery[].class);
            for (Gallery rec : myrec) {
                gallery_record.add(rec);
            }
            System.out.println(tourname);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("This is Admin Bean");
        return gallery_record;
    }

    @Override
    public List getFeedback() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getAllComplaint() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getComplaint(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteComplaint(int cid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCart(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateStatus(int tourid, String uname, String payment_status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
