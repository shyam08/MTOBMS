package OTBMS.dto.payment.gateway;

public interface PaymentGateway {
    InitiateResponse initiate(InitiateRequest request);

    DebitResponse debit(DebitRequest request);

    VerifyResponse verify(VerifyRequest request);
}
