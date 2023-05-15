package Controllers;

import AdminEJB.AdminBeanLocal;
import entity.Tourmaster;
import entity.Tourplace;
import entity.Usertb;
import entity.Vehicle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
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

    public void performUpdate() {
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
        } else if (image_url.equals("/noImage.jpg")) {
            System.out.println("No Image");
            current.executeScript("PF('noImage').show();");
        } else {
            abl.updateTourMaster(tm);
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

            current.executeScript("PF('tourUpdated').show();");
        }
    }

    public String updatePerformed() {
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
        return "adminHome.xhtml?faces-redirect=true";
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

    Vehicle v = new Vehicle();

    public Vehicle getV() {
        return v;
    }

    public void setV(Vehicle v) {
        this.v = v;
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
            v.setTourmasterid(tourmasterid_vehicle);
            v.setVehicle_name(vehicle_name);
            abl.addVehicle(v);
            vehicle_name = "";
            tourmasterid_vehicle = 0;
            v = new Vehicle();
            current.executeScript("PF('vehicleAdded').show();");
        }
        System.out.println(tourmasterid_vehicle);
    }

    public String closeAddVehicleDialog() {
        vehicle_name = "";
        v = new Vehicle();
        tourmasterid_vehicle = 0;
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
            abl.addPlace(tp);
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

    public void cancelTour() {
        abl.deleteTourMaster(cancel_tourmasterid);
        cancel_tourmasterid = 0;
        current.executeScript("PF('tourCancelled').show();");
    }

    public String closeCancelTourDialog() {
        cancel_tourmasterid = 0;
        return "adminHome.xhtml?faces-redirect=true";
    }

    //Update Vehicle
    List<Vehicle> all_vehicle = new ArrayList<>();

    public String updateVehicleRedirect(int tourmasterid) {
        all_vehicle = new ArrayList<>();
        System.out.println(tourmasterid);
        all_vehicle = abl.getVehicle(tourmasterid);
        System.out.println("all-Vehicle -> " + all_vehicle);
        return "updateVehicle.xhtml?faces-redirect=true";
    }

    public List<Vehicle> updateVehicleRecords() {
        return all_vehicle;
    }

    public void performVehicleUpdate(Vehicle ve) {
        System.out.println(ve.getVehicleid() + " - " + ve.getTourmasterid() + " - " + ve.getVehicle_name());
        if (ve.getVehicle_name().equals("")) {
            current.executeScript("PF('vehicleUpdateEmptyField').show();");
        } else {
            abl.updateVehicle(ve);
            current.executeScript("PF('vehicleNameUpdated').show();");
        }
    }

    public String vehicleUpdatePerformed() {
        return "adminHome.xhtml?faces-redirect=true";
    }

    int delete_vehicleid;

    public int getDelete_vehicleid() {
        return delete_vehicleid;
    }

    public void setDelete_vehicleid(int delete_vehicleid) {
        this.delete_vehicleid = delete_vehicleid;
    }

    public void openVehicleDeleteConfirmDialog(int vehicleid) {
        delete_vehicleid = vehicleid;
        current.executeScript("PF('vehicleDeleteConfirm').show();");
    }

    public void performVehicleDelete() {
        System.out.println(delete_vehicleid);
        abl.deleteVehicle(delete_vehicleid);
        current.executeScript("PF('vehicleDeleted').show();");
    }

    public String closeDeleteVehicleDialog() {
        delete_vehicleid = 0;
        return "updateVehicle.xhtml?faces-redirect=true";
    }

    public String vehicleDeleted() {
        return "adminHome.xhtml?faces-redirect=true";
    }

    //Update Place Operations
    List<Tourplace> all_place = new ArrayList<>();
    Date[] update_place_sdate;
    Date[] update_place_edate;

    public Date[] getUpdate_place_sdate() {
        return update_place_sdate;
    }

    public void setUpdate_place_sdate(Date[] update_place_sdate) {
        this.update_place_sdate = update_place_sdate;
    }

    public Date[] getUpdate_place_edate() {
        return update_place_edate;
    }

    public void setUpdate_place_edate(Date[] update_place_edate) {
        this.update_place_edate = update_place_edate;
    }

    public String updatePlaceRedirect(int tourmasterid) {
        all_place = new ArrayList<>();
        System.out.println(tourmasterid);

        all_place = abl.getPlaces(tourmasterid);
        update_place_sdate = new Date[all_place.size()];
        update_place_edate = new Date[all_place.size()];

        for (int i = 0; i < all_place.size(); i++) {
            update_place_sdate[i] = new java.util.Date(all_place.get(i).getStart_date().getTime());
            update_place_edate[i] = new java.util.Date(all_place.get(i).getEnd_date().getTime());
        }

        System.out.println("All places -> " + all_place);
        return "updatePlace.xhtml?faces-redirect=true";
    }

    public List<Tourplace> updatePlaceRecords() {
        return all_place;
    }

    public void performPlaceUpdate(Tourplace tplace, Date sdate, Date edate) {
        System.out.println(tplace.getPlace_name() + " - " + tplace.getTourmasterid() + " - " + sdate + " - " + edate);
        if (tplace.getPlace_name().isEmpty() || tplace.getPlace_city().isEmpty() || tplace.getPlace_state().isEmpty() || tplace.getPlace_description().isEmpty() || sdate == null || edate == null) {
            current.executeScript("PF('placeUpdateEmptyField').show();");
        } else {
            try {
                java.sql.Date place_start_date = new java.sql.Date(sdate.getTime());
                System.out.println("sqlDate:" + place_start_date);

                java.sql.Date place_end_date = new java.sql.Date(edate.getTime());
                System.out.println("sqlDate:" + place_end_date);

                tplace.setStart_date(place_start_date);
                tplace.setEnd_date(place_end_date);

                abl.updatePlace(tplace);
                current.executeScript("PF('placeUpdated').show();");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public String placeUpdatePerformed() {
        return "adminHome.xhtml?faces-redirect=true";
    }

    int delete_placeid;

    public int getDelete_placeid() {
        return delete_placeid;
    }

    public void setDelete_placeid(int delete_placeid) {
        this.delete_placeid = delete_placeid;
    }

    public void openPlaceDeleteConfirmDialog(int placeid) {
        delete_placeid = placeid;
        current.executeScript("PF('placeDeleteConfirm').show();");
    }

    public void performPlaceDelete() {
        System.out.println(delete_placeid);
        abl.deletePlace(delete_placeid);
        current.executeScript("PF('placeDeleted').show();");
    }

    public String closeDeletePlaceDialog() {
        delete_placeid = 0;
        return "updatePlace.xhtml?faces-redirect=true";
    }

    public String placeDeleted() {
        return "adminHome.xhtml?faces-redirect=true";
    }

    //Edit Profile Work
    Usertb user_info = new Usertb();

    public Usertb getUser_info() {
        return user_info;
    }

    public void setUser_info(Usertb user_info) {
        this.user_info = user_info;
    }

    String edit_username;

    public String getEdit_username() {
        return edit_username;
    }

    public void setEdit_username(String edit_username) {
        this.edit_username = edit_username;
    }

    public String getCurrentUsername() {
        String uname = "";
        try {
            HttpSession session = Util.getSession();
            if (session.getAttribute("admin") != null) {
                uname = session.getAttribute("admin").toString();
            }
            if (session.getAttribute("user") != null) {
                uname = session.getAttribute("user").toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return uname;
    }

    public void editProfileRedirect() {
        user_info = new Usertb();
        edit_username = "";
        edit_username = getCurrentUsername();
        user_info = abl.getUserData(edit_username);
        current.executeScript("PF('editProfile').show();");
    }

    public void performUserUpdate() {
        System.out.println(user_info.getUsername() + " - " + user_info.getEmail());
        if (user_info.getUsername().isEmpty() || user_info.getFname().isEmpty() || user_info.getLname().isEmpty() || user_info.getEmail().isEmpty()) {
            current.executeScript("PF('editProfileEmptyField').show();");
        } else {
            abl.updateUserData(user_info);
            current.executeScript("PF('profileEdited').show();");
        }
    }

    public String closeEditProfileDialog() {
        user_info = new Usertb();
        edit_username = "";
        return "adminHome.xhtml?faces-redirect=true";
    }

    public String redirectLogout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        System.out.println("Session Destroyed...");
        return "gallery.xhtml?faces-redirect=true";
    }

    //Edit Password Work
    Usertb user_info_password = new Usertb();

    public Usertb getUser_info_password() {
        return user_info_password;
    }

    public void setUser_info_password(Usertb user_info_password) {
        this.user_info_password = user_info_password;
    }

    String edit_password_username;

    public String getEdit_password_username() {
        return edit_password_username;
    }

    public void setEdit_password_username(String edit_password_username) {
        this.edit_password_username = edit_password_username;
    }

    String current_password;
    String new_password;
    String confirm_new_password;
    String encrypted_current_password;

    public String getEncrypted_current_password() {
        return encrypted_current_password;
    }

    public void setEncrypted_current_password(String encrypted_current_password) {
        this.encrypted_current_password = encrypted_current_password;
    }

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_new_password() {
        return confirm_new_password;
    }

    public void setConfirm_new_password(String confirm_new_password) {
        this.confirm_new_password = confirm_new_password;
    }

    public void editPasswordRedirect() {
        user_info_password = new Usertb();
        edit_password_username = "";
        current_password = "";
        new_password = "";
        confirm_new_password = "";
        encrypted_current_password = "";
        edit_password_username = getCurrentUsername();
        user_info_password = abl.getUserData(edit_password_username);
        current.executeScript("PF('editPassword').show();");
    }

    public void performPasswordUpdate() {
        Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();

        if (!current_password.equals("")) {
            encrypted_current_password = pb.generate(new_password.toCharArray());
        }

        System.out.println(current_password + " - " + new_password + " - " + confirm_new_password);
        if (current_password.isEmpty() || new_password.isEmpty() || confirm_new_password.isEmpty()) {
            current.executeScript("PF('emptyPasswordField').show();");
        } else if (!pb.verify(current_password.toCharArray(), user_info_password.getPassword())) {
            current.executeScript("PF('incorrectCurrentPassword').show();");
        } else if (current_password.equals(new_password)) {
            current.executeScript("PF('sameCurrentPassword').show();");
        } else if (!new_password.equals(confirm_new_password)) {
            current.executeScript("PF('invalidNewConfirmPassword').show();");
        } else {
            user_info_password.setPassword(encrypted_current_password);
            abl.updateUserData(user_info_password);
            current.executeScript("PF('passwordUpdated').show();");
        }
    }

    public String closeEditPasswordDialog() {
        user_info_password = new Usertb();
        edit_password_username = "";
        current_password = "";
        new_password = "";
        confirm_new_password = "";
        encrypted_current_password = "";
        return "adminHome.xhtml?faces-redirect=true";
    }

    public String passwordChangedSuccessfull() {
        HttpSession session = Util.getSession();
        session.invalidate();
        System.out.println("Session Destroyed...");
        return "login.xhtml?faces-redirect=true";
    }

    public String redirectToAdminHome() {
        return "adminHome.xhtml?faces-redirect=true";
    }

    //Application Statistics
    public void openApplicationStatisticsDialog() {
        current.executeScript("PF('applicationStatistics').show();");
    }

    PieChartModel pieModel;

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    @PostConstruct
    public void init() {
        createPieModel();
        createBarModel();
    }

    public void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();

        int number_of_admin = abl.getGroupByGroupname("admin").size();
        int number_of_user = abl.getGroupByGroupname("user").size();

        values.add(number_of_admin);
        values.add(number_of_user);

        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Admin");
        labels.add("User");
        data.setLabels(labels);

        pieModel.setData(data);
    }

    BarChartModel barModel;

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("admin / user");

        int number_of_admin = abl.getGroupByGroupname("admin").size();
        int number_of_user = abl.getGroupByGroupname("user").size();

        List<Number> values = new ArrayList<>();
        values.add(number_of_admin);
        values.add(number_of_user);
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");

        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Admin");
        labels.add("User");

        data.setLabels(labels);
        barModel.setData(data);
    }
    //Toggle Theme Change
    String color = "black";
    String bgcolor = "white";
    String themeIcon = "fa-solid fa-moon";
    String themeName = "Dark Mode";

    public String getThemeIcon() {
        return themeIcon;
    }

    public void setThemeIcon(String themeIcon) {
        this.themeIcon = themeIcon;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String changeTheme() {
        System.out.println("Changing Theme");
        System.out.println(color);
        if (color.equals("black")) {
            color = "white";
            bgcolor = "#343434";
            themeIcon = "fa-solid fa-sun";
            themeName = "Light Mode";
        } else {
            color = "black";
            bgcolor = "white";
            themeIcon = "fa-solid fa-moon";
            themeName = "Dark Mode";
        }
        return "adminHome.xhtml?faces-redirect=true";
    }
}
