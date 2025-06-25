package service.interfaces.payment_module;

import domain.entities.Payment;
import shared.enums.PAYMENT_METHOD;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface PaymentFactory {
    Payment create(PAYMENT_METHOD paymentMethod, double amount);
}
