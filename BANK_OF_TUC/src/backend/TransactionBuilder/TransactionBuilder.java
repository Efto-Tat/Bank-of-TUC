package backend;

public abstract class TransactionBuilder extends InteractionBuilder {
  
  protected enum transactionStatus{
		PENDING,
		SUCCESFUL,
		FAILED
  }
  String date;
  String time;

  public TransactionBuilder setStatus(int status){
    this.transactionStatus = status;
    return this;
  }
  
  public TransactionBuilder setDate(String date){
    this.date = date;
    return this;
  }
  public TransactionBuilder setTime(String time){
    this.time = time;
    return this;
  }
  
  public abstract Interaction build() {
  }
}
