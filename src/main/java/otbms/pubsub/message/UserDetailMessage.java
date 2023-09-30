package otbms.pubsub.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailMessage {
    private Integer userId;// customer.id table ID
    private long phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    //add cartId or sessionId if available
    private Map<String, Object> metaData;
}
