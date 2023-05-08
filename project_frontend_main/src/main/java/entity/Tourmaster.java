package entity;

import java.sql.Date;
import lombok.*;

@Getter
@Setter
public class Tourmaster {

    private int tourmasterid;
    private String tour_title;
    private String tour_pic;
    private Date start_date;
    private Date end_date;
    private String journey_begin_time;
    private int per_person_price;
    private String pickup_address;
}
