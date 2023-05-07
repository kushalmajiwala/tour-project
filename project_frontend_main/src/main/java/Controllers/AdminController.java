package Controllers;

import UserEJB.UserBeanLocal;
import entity.Tourmaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.file.UploadedFile;

@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    public AdminController() {
    }

    @EJB
    private UserBeanLocal ubl;

    public List<Tourmaster> getAllTours() {
        return ubl.showMasterData();
    }

    public String redirectAllTours() {
        return "adminHome.xhtml?faces-redirect=true";
    }
    String startDate;
    String endDate;
    int time_diff;
    int day_diff;
    String image_url = "../images/noImage.jpg";
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
        return "addTour.xhtml?faces-redirect=true";
    }
    
    UploadedFile image;
    public UploadedFile getImage() {
        return image;
    }
    public void setImage(UploadedFile image) {
        this.image = image;
    }
    
    public void uploadImage() {
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
            while((temp = in.read(fileContent)) != -1)
            {
                output.write(fileContent, 0, temp);
            }
            output.flush();
            output.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        image_url = "../images/" + file_name;
    }
}
