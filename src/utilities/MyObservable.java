package utilities;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MyObservable {

   private boolean changed;

   private LinkedHashSet observers;

   public MyObservable(){
     observers = new LinkedHashSet();
   }
   
   public synchronized void addObserver(MyObserver observer){
     if (observer == null)
       throw new NullPointerException("can't add null observer");
     observers.add(observer);
   }

   protected synchronized void clearChanged() {
     changed = false;
   }

   public synchronized int countObservers() {
     return observers.size();
   }

   public synchronized void deleteObserver(MyObserver victim) {
     observers.remove(victim);
   }

   public synchronized void deleteObservers(){
	   observers.clear();
   }

   public synchronized boolean hasChanged() {
     return changed;
   }

   public void notifyObservers() {
	   notifyObservers(null);
   }

   
   public void notifyObservers(Object obj){
     if (! hasChanged())
       return;

     Set s;
     synchronized (this){
    	 s = (Set) observers.clone();
     }
     int i = s.size();
     Iterator iter = s.iterator();
     while (--i >= 0)
    	 ((MyObserver) iter.next()).update(this, obj);
     	 clearChanged();
   }

    /**
     * Clears all observers from this object
     */
   public void clearObservers(){
       this.observers = new LinkedHashSet();
   }


	protected synchronized void setChanged() {
      changed = true;
    } 
}

