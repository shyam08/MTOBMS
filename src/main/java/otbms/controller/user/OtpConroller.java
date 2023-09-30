package otbms.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import otbms.service.user.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-svc/v1/otp")
@Slf4j
@CrossOrigin
@Tag(name = "user-service")
public class OtpConroller {

    @Autowired
    private OtpService otpService;

    @Operation(summary = "send sms otp")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sent sms otp successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/phone-numbers/{phone-number}/generate", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces =
                    {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity sendPhoneNumberOtp(@PathVariable("phone-number") Long phoneNumber) throws Exception {
        log.info("generate otp for phone-number={}", phoneNumber);
        otpService.sendSmsOtp(phoneNumber);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "verify phone-number otp")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "verified sms otp successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/phone-numbers/{phone-number}/verify", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity verifyPhoneNumberOtp(@PathVariable("phone-number") Long phoneNumber) throws Exception {
        log.info("verify otp for phone-number={}", phoneNumber);
        otpService.verifySmsOtp(phoneNumber);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "send email otp")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sent email otp successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/emails/{email}/generate", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces =
                    {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity sendPhoneNumberOtp(@PathVariable("email") String email) throws Exception {
        log.info("generate otp for email={}", email);
        otpService.senEmailOtp(email);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "verify phone-number otp")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "verified email otp successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/emails/{email}/verify", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity verifyPhoneNumberOtp(@PathVariable("email") String email) throws Exception {
        log.info("verify otp for email={}", email);
        otpService.verifyEmailOtp(email);
        return ResponseEntity.ok(null);
    }

}
