package OTBMS.service.user;

public interface OtpService {

    void sendSmsOtp(Long phoneNumber) throws Exception;

    void verifySmsOtp(Long phoneNumber) throws Exception;

    void senEmailOtp(String email) throws Exception;

    void verifyEmailOtp(String email) throws Exception;
}
