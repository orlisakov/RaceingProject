package utilities;
public interface Subject {

	//methods to register and unregister observers
	public void register(MyObserver obj);
	public void unregister(MyObserver obj);
	
	//method to notify observers of change
	public void notifyObservers();
	
	//method to get updates from subject
	public Object getUpdate(MyObserver obj);
	
}