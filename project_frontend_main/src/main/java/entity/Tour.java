
package entity;

import lombok.*;

@Getter
@Setter
public class Tour {

    private int tourid;
    private int tourmasterid;
    private String username;
    private String payment_method;
    private String payment_status;
}
