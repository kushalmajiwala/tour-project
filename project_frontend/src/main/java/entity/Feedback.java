
package entity;

import lombok.*;

@Getter
@Setter
public class Feedback {

    private int feedbackid;
    private String username;
    private String rating;
    private String subject;
    private String message;
}
