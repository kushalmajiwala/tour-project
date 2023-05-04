package Controllers;

import UserEJB.UserBeanLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

@Named
@SessionScoped
public class GalleryController implements Serializable {

    @EJB
    private UserBeanLocal userbean;

    public GalleryController() {
    }

    public List getGalleryData(String tourname) 
    {
        System.out.println("Gallery Executed...");
        return this.userbean.getGalleryData(tourname);
    }
}
