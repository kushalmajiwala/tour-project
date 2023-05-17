package AdminEJB;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Complaint;
import entity.Gallery;
import entity.ProjectgroupsPK;
import entity.Tourmaster;
import entity.Tourplace;
import entity.Usertb;
import entity.Vehicle;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    public Usertb getUserData(String uname) {
        Usertb mylist = new Usertb();
        try {
            String url = "http://localhost:9090/user/getdata/" + uname;
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            Usertb myrec = mapper.readValue(response.body().toString(), Usertb.class);
            mylist = myrec;
        } catch (Exception e) {
            System.out.println(e);
        }
        return mylist;
    }

    @Override
    public void addVehicle(Vehicle v) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(v);

            String url = "http://localhost:9090/vehicle/addvehicle";

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
    public void updateVehicle(Vehicle v) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(v);

            String url = "http://localhost:9090/vehicle/updatevehicle";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }
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
    public void deleteVehicle(int vehicleid) {
        try {
            String url = "http://localhost:9090/vehicle/deletevehicle/" + vehicleid;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addPlace(Tourplace tp) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(tp);

            String url = "http://localhost:9090/tourplace/addplace";

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
    public void updatePlace(Tourplace tp) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(tp);

            String url = "http://localhost:9090/tourplace/updateplace";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List getPlaces(int tourid) {
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
    public void deletePlace(int placeid) {
        try {
            String url = "http://localhost:9090/tourplace/deleteplace/" + placeid;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }
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
    public void updateTourMaster(Tourmaster tm) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(tm);

            String url = "http://localhost:9090/tourmaster/updatetour";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String getTourMaster(int tourid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteTourMaster(int tourid) {
        try {
            String url = "http://localhost:9090/tourmaster/deletetour/" + tourid;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("Record to be deleted -> " + tourid);
            System.out.println("Deleted...");
        } catch (Exception e) {
            System.out.println(e);
        }
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
        List mylist = new ArrayList();
        try {
            String url = "http://localhost:9090/complaint/getallcomplaint";
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            Complaint[] myrec = mapper.readValue(response.body().toString(), Complaint[].class);
            for (Complaint rec : myrec) {
                mylist.add(rec);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return mylist;
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

    @Override
    public void updateUserData(Usertb u) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(u);

            String url = "http://localhost:9090/user/updatedata";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List getGroupByGroupname(String gname) {
        List group_data = new ArrayList();
        try {
            String url = "http://localhost:9090/projectgroups/getgroup/" + gname;
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            ObjectMapper mapper = new ObjectMapper();
            ProjectgroupsPK[] myrec = mapper.readValue(response.body().toString(), ProjectgroupsPK[].class);
            for (ProjectgroupsPK rec : myrec) {
                group_data.add(rec);
            }
            System.out.println(gname);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("This is Admin Bean");
        return group_data;
    }
}
