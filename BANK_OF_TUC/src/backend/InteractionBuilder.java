package backend;

public abstract class InteractionBuilder {
  protected String senderIBAN;
  protected String recieverIBAN;
  protected float paymentSum;

  public InteractionBuilder() {
  }

  public InteractionBuilder setSender(String iban) {
    this.senderIBAN = iban;
    return this;
  }

  public InteractionBuilder setReciever(String iban) {
		this.recieverIBAN = iban;
		return this;
	}
  public InteractionBuilder setPaymentSum(float amount) {
		this.paymentSum = amount;
		return this;
	}

  public abstract Interaction build();
}
