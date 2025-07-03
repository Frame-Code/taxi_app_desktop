package service.impl.payment_module;

import domain.entities.Payment;
import service.interfaces.payment_module.PaymentFactory;
import shared.enums.PAYMENT_METHOD;

import java.util.UUID;

/**
 *
 * @author Daniel Mora Cantillo
 */
public class PaymentFactoryImpl implements PaymentFactory {
    @Override
    public Payment create(PAYMENT_METHOD paymentMethod, double amount) {
        return Payment.builder()
                .amount(amount)
                .paymentMethod(paymentMethod)
                .transactionCode(UUID.randomUUID())
                .build();
    }
}
