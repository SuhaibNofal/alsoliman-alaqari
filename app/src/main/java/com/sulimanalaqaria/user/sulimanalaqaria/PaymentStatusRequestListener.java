package com.sulimanalaqaria.user.sulimanalaqaria;

public interface PaymentStatusRequestListener {
    void onErrorOccurred();
    void onPaymentStatusReceived(String paymentStatus);
}
