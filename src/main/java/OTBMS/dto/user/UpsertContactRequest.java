package OTBMS.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpsertContactRequest {
    @NotEmpty
    private Long phoneNo;
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    private String firstName;
    private String lastName;
    // metadata like sessionId, cartId etc. for messaging
    private Map<String, Object> metaData;
}
