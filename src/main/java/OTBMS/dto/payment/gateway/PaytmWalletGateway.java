package OTBMS.dto.payment.gateway;


import org.springframework.stereotype.Component;

@Component
public class PaytmWalletGateway extends AbstractPaymentGateway {
    @Override
    public InitiateResponse initiate(InitiateRequest request) {
        return null;
    }

    @Override
    public DebitResponse debit(DebitRequest request) {
        return null;
    }

    @Override
    public VerifyResponse verify(VerifyRequest request) {
        return null;
    }
}
