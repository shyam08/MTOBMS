package otbms.dao.payment;

public enum PaymentGatewayName {
    paytmWallet(1), paytmUpi(2), phonepeUpi(3), gpay(4);

    private int id;

    PaymentGatewayName(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PaymentGatewayName getEnum(int id) {
        if (id == 1) {
            return paytmWallet;
        } else if (id == 2) {
            return paytmWallet;
        } else if (id == 3) {
            return phonepeUpi;
        } else if (id == 4) {
            return gpay;
        }
        throw new IllegalArgumentException();
    }

}
