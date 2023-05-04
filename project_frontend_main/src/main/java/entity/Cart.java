
package entity;

import lombok.*;

@Getter
@Setter
public class Cart {

    private int cartid;
    private int tourid;
    private String username;
    private String payment_status;
}
