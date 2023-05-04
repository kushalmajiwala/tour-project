package entity;

import java.sql.Date;
import lombok.*;

@Getter
@Setter
public class Tourplace {

    private int tourplaceid;
    private int tourmasterid;
    private String place_name;
    private String place_city;
    private String place_state;
    private String place_description;
    private Date start_date;
    private Date end_date;
}
