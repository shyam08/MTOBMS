package OTBMS.dto.payment.gateway;

import OTBMS.dao.payment.PaymentGatewayName;
import OTBMS.exception.InvalidPaymentGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentGatewayFactory {
    private static Map<String, PaymentGateway> paymentGatewayMap = new HashMap<>(10);

    @Autowired
    public PaymentGatewayFactory(PaytmWalletGateway paytmWalletGateway) {
        paymentGatewayMap.put(PaymentGatewayName.paytmWallet.name(), paytmWalletGateway);
    }

    public static PaymentGateway getGateway(PaymentGatewayName gatewayName) throws InvalidPaymentGatewayException {
        if (paymentGatewayMap.containsKey(gatewayName.name())) {
            throw new InvalidPaymentGatewayException(String.format("Gateway not found %s", gatewayName.name()));
        }
        return paymentGatewayMap.get(gatewayName.name());
    }


}
