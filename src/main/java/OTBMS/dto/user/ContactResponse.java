package OTBMS.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactResponse {
    private Integer id;
    private Long phoneNo;
    private String email;
    private String firstName;
    private String lastName;
}
