package UserEJB;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Gallery;
import entity.ProjectgroupsPK;
import entity.Tourmaster;
import entity.Tourplace;
import entity.Usertb;
import entity.Vehicle;
import java.net.URI;
import java.net.http.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

@Stateless
public class UserBean implements UserBeanLocal {

    @Override
    public void registerUser(String uname, String fname, String lname, String email, String pass) {
        System.out.println("User Registered Successfully...");
        System.out.println("Username -> " + uname);
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(email);
        System.out.println(pass);
        Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();
        String encrypted_password = pb.generate(pass.toCharArray());
        System.out.println("new Password -> " + encrypted_password);

        Usertb u = new Usertb();
        u.setUsername(uname);
        u.setFname(fname);
        u.setLname(lname);
        u.setEmail(email);
        u.setPassword(encrypted_password);

        //Adding in User Table
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(u);

            String url = "http://localhost:9090/user/adddata";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("User Registered Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        //Adding in Group Table
        ProjectgroupsPK pg = new ProjectgroupsPK();
        pg.setGroupname("user");
        pg.setUsername(uname);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(pg);

            String url = "http://localhost:9090/projectgroups/addgroup";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("Group added Successfully...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List getUserData(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateProfile(String uname, String fname, String lname, String email, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getVehicle(int tourid) {
        List<Vehicle> mylist = new ArrayList();
        try {
            String url = "http://localhost:9090/vehicle/getvehicle/" + tourid;
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            Vehicle[] myrec = mapper.readValue(response.body().toString(), Vehicle[].class);
            for (Vehicle rec : myrec) {
                mylist.add(rec);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return mylist;
    }

    @Override
    public List<Tourplace> getPlaces(int tourid) {
        List<Tourplace> mylist = new ArrayList();
        try {
            String url = "http://localhost:9090/tourplace/getplace/" + tourid;
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            Tourplace[] myrec = mapper.readValue(response.body().toString(), Tourplace[].class);
            for (Tourplace rec : myrec) {
                mylist.add(rec);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return mylist;
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
    public String getTourMaster(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addTour(int tourmasterid, String username, String payment_method) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateTour(int tourmasterid, String username, String payment_method) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getTour(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteTour(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addPerson(int tourid, String uname, String fname, String lname, String email, String pno, Date dob, String gender) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updatePerson(int tourid, String uname, String fname, String lname, String email, String pno, Date dob, String gender) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getPersons(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deletePerson(int pid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addHistory(int tourid, String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getHistory(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteHistory(int hid) {
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
            System.out.println("This is UserBean...");
        } catch (Exception e) {
            System.out.println(e);
        }
        return gallery_record;
    }

    @Override
    public String addFeedback(String uname, String rating, String subject, String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addComplaint(String uname, String subject, String message) {
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
    public String addCart(int tourid, String uname, String payment_status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCart(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteCart(int cartid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
