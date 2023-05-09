package Controllers;

import AdminEJB.AdminBeanLocal;
import entity.Tourmaster;
import entity.Tourplace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;

@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    public AdminController() {
    }
    PrimeFaces current = PrimeFaces.current();

    Tourmaster tm = new Tourmaster();

    public Tourmaster getTm() {
        return tm;
    }

    public void setTm(Tourmaster tm) {
        this.tm = tm;
    }

    @EJB
    private AdminBeanLocal abl;

    public List<Tourmaster> getAllTours() {
        return abl.showMasterData();
    }

    public String redirectAllTours() {
        return "adminHome.xhtml?faces-redirect=true";
    }
    Date insert_sdate;
    Date insert_edate;
    Date insert_stime;

    public Date getInsert_sdate() {
        return insert_sdate;
    }

    public void setInsert_sdate(Date insert_sdate) {
        this.insert_sdate = insert_sdate;
    }

    public Date getInsert_edate() {
        return insert_edate;
    }

    public void setInsert_edate(Date insert_edate) {
        this.insert_edate = insert_edate;
    }

    public Date getInsert_stime() {
        return insert_stime;
    }

    public void setInsert_stime(Date insert_stime) {
        this.insert_stime = insert_stime;
    }

    String vehicle_name = "";
    int tourmasterid_vehicle;

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public int getTourmasterid_vehicle() {
        return tourmasterid_vehicle;
    }

    public void setTourmasterid_vehicle(int tourmasterid_vehicle) {
        this.tourmasterid_vehicle = tourmasterid_vehicle;
    }

    String startDate;
    String endDate;
    int time_diff;
    int day_diff;
    String image_url = "/noImage.jpg";
    String upload_folder = "E:\\programming\\sem8_project\\project_frontend_main\\src\\main\\webapp\\images\\";

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public int getDayDiff(String start_date, String end_date) {
        startDate = start_date;
        endDate = end_date;

        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date sdate = obj.parse(startDate);
            Date edate = obj.parse(endDate);

            time_diff = (int) (edate.getTime() - sdate.getTime());
            day_diff = (time_diff / (1000 * 60 * 60 * 24)) % 365;
        } catch (Exception e) {
            System.out.println(e);
        }
        return day_diff;
    }

    public String addTourRedirect() {
        tm = new Tourmaster();
        tm.setTour_title("");
        tm.setPer_person_price(0);
        tm.setEnd_date(null);
        tm.setStart_date(null);
        tm.setPickup_address("");
        tm.setTour_pic("/noImage.jpg");
        tm.setJourney_begin_time("");
        image_url = "/noImage.jpg";

        insert_sdate = null;
        insert_edate = null;
        insert_stime = null;
        return "addTour.xhtml?faces-redirect=true";
    }

    UploadedFile image;

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public String uploadImage() {
        String file_name = "";

        try {
            file_name = image.getFileName();
            file_name = file_name.substring(file_name.lastIndexOf('\\') + 1);
            System.out.println(image.getFileName());
            byte[] fileContent = new byte[(int) image.getSize()];
            InputStream in = image.getInputStream();
            File fileToCreate = new File(upload_folder, file_name);
            OutputStream output = new FileOutputStream(fileToCreate);
            int temp = 0;
            while ((temp = in.read(fileContent)) != -1) {
                output.write(fileContent, 0, temp);
            }
            output.flush();
            output.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        image_url = "/" + file_name;
        System.out.println(image_url);
        return "addTour.xhtml";
    }

    public String addNewTour() {
        PrimeFaces current = PrimeFaces.current();

        System.out.println(tm);
        System.out.println(insert_sdate);
        System.out.println(insert_edate);

        try {
            java.sql.Date actual_start_date = new java.sql.Date(insert_sdate.getTime());
            System.out.println("sqlDate:" + actual_start_date);

            java.sql.Date actual_end_date = new java.sql.Date(insert_edate.getTime());
            System.out.println("sqlDate:" + actual_end_date);

            SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
            String start_time = localDateFormat.format(insert_stime);
            System.out.println("The Time is -> " + start_time);

            tm.setStart_date(actual_start_date);
            tm.setEnd_date(actual_end_date);
            tm.setJourney_begin_time(start_time);
            tm.setTour_pic(image_url);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (tm.getTour_title().isEmpty() || tm.getStart_date() == null || tm.getEnd_date() == null || tm.getJourney_begin_time().isEmpty() || tm.getPer_person_price() == 0 || tm.getPickup_address().isEmpty()) {
            System.out.println("Empty Field");
            current.executeScript("PF('emptyField').show();");
            return "addTour.xhtml";
        } else if (image_url.equals("/noImage.jpg")) {
            System.out.println("No Image");
            current.executeScript("PF('noImage').show();");
            return "addTour.xhtml";
        } else {
            abl.addTourMaster(tm);
        }
        tm = new Tourmaster();
        tm.setTour_title("");
        tm.setPer_person_price(0);
        tm.setEnd_date(null);
        tm.setStart_date(null);
        tm.setPickup_address("");
        tm.setTour_pic("/noImage.jpg");
        tm.setJourney_begin_time("");
        image_url = "/noImage.jpg";

        insert_sdate = null;
        insert_edate = null;
        insert_stime = null;

        current.executeScript("PF('tourAdded').show();");
        return "addTour.xhtml";
    }

    public String closeDialog() {
        tm = new Tourmaster();
        tm.setTour_title("");
        tm.setPer_person_price(0);
        tm.setEnd_date(null);
        tm.setStart_date(null);
        tm.setPickup_address("");
        tm.setTour_pic("/noImage.jpg");
        tm.setJourney_begin_time("");
        image_url = "/noImage.jpg";

        insert_sdate = null;
        insert_edate = null;
        insert_stime = null;
        return "addTour.xhtml?faces-redirect=true";
    }

    public String updateRedirect(Tourmaster t) {
        tm = t;
        image_url = t.getTour_pic();
        insert_sdate = new java.sql.Date(t.getStart_date().getTime());
        insert_edate = new java.sql.Date(t.getEnd_date().getTime());
        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            insert_stime = formatter.parse(t.getJourney_begin_time());
        } catch (Exception e) {
            System.out.println(e);
        }
        return "updateTour.xhtml?faces-redirect=true";
    }

    //Adding Vehicle
    public void openAddVehicleDialog(int id) {
        tourmasterid_vehicle = id;
        System.out.println(tourmasterid_vehicle);
        current.executeScript("PF('addVehicle').show();");
    }

    public void addVehicle() {
        System.out.println("Name -> " + vehicle_name);
        if (vehicle_name.equals("")) {
            current.executeScript("PF('emptyVehicleName').show();");
        } else {
            //bean call for add action
            vehicle_name = "";
            tourmasterid_vehicle = 0;
            current.executeScript("PF('vehicleAdded').show();");
        }
        System.out.println(tourmasterid_vehicle);
    }

    public String closeAddVehicleDialog() {
        vehicle_name = "";
        return "adminHome.xhtml?faces-redirect=true";
    }

    Date place_sdate;
    Date place_edate;

    public Date getPlace_sdate() {
        return place_sdate;
    }

    public void setPlace_sdate(Date place_sdate) {
        this.place_sdate = place_sdate;
    }

    public Date getPlace_edate() {
        return place_edate;
    }

    public void setPlace_edate(Date place_edate) {
        this.place_edate = place_edate;
    }

    Tourplace tp = new Tourplace();

    public Tourplace getTp() {
        return tp;
    }

    public void setTp(Tourplace tp) {
        this.tp = tp;
    }

    //Adding Place
    public void openAddPlaceDialog(int id) {
        tp.setTourmasterid(id);
        current.executeScript("PF('addPlace').show();");
    }

    public void addPlace() {
        try {
            java.sql.Date place_start_date = new java.sql.Date(place_sdate.getTime());
            System.out.println("sqlDate:" + place_start_date);

            java.sql.Date place_end_date = new java.sql.Date(place_edate.getTime());
            System.out.println("sqlDate:" + place_end_date);

            tp.setStart_date(place_start_date);
            tp.setEnd_date(place_end_date);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (tp.getPlace_name().isEmpty() || tp.getPlace_city().isEmpty() || tp.getPlace_state().isEmpty() || tp.getPlace_description().isEmpty() || tp.getStart_date() == null || tp.getEnd_date() == null) {
            current.executeScript("PF('emptyPlaceField').show();");
        } else {
            //bean call for add action
            tp = new Tourplace();
            place_sdate = null;
            place_edate = null;
            current.executeScript("PF('placeAdded').show();");
        }
        System.out.println(tp.getTourmasterid());
    }

    public String closeAddPlaceDialog() {
        tp = new Tourplace();
        place_sdate = null;
        place_edate = null;
        return "adminHome.xhtml?faces-redirect=true";
    }

    //Canceling Tour
    
    int cancel_tourmasterid;
    public int getCancel_tourmasterid() {
        return cancel_tourmasterid;
    }
    public void setCancel_tourmasterid(int cancel_tourmasterid) {
        this.cancel_tourmasterid = cancel_tourmasterid;
    }
    
    public void openCancelTourDialog(int id) {
        System.out.println(id);
        cancel_tourmasterid = id;
        current.executeScript("PF('cancelTour').show();");
    }

    public void cancelTour()
    {
        System.out.println(cancel_tourmasterid);
        cancel_tourmasterid = 0;
        //bean call for cancel action
        current.executeScript("PF('tourCancelled').show();");
    }
    
    public String closeCancelTourDialog() {
        cancel_tourmasterid = 0;
        return "adminHome.xhtml?faces-redirect=true";
    }
}
