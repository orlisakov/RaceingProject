package utilities;

import java.util.Observable;

public interface MyObserver {
	public void update(MyObservable o, Object arg);
}
