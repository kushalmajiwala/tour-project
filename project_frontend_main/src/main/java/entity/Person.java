
package entity;

import java.sql.Date;
import lombok.*;

@Getter
@Setter
public class Person {

    private int personid;
    private int tourid;
    private String username;
    private String fname;
    private String lname;
    private String email;
    private String phoneno;
    private Date dob;
    private String gender;
}
