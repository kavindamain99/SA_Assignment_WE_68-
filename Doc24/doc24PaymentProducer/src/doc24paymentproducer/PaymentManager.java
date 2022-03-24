package doc24paymentproducer;

import java.io.IOException;

public interface PaymentManager {
 public void newPayment(String payId, String dId, String pId,String desc, double amount)  throws IOException;
 public void viewPayment() throws IOException;
 public void billPayment(String pId) throws IOException;
}
