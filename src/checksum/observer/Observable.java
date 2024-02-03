package checksum.observer;

import java.util.*;

public class Observable {
    private Set<Observer> observers = new HashSet<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unSubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object context) {
        for (var observer : observers) {
            observer.update(this, context);
        }
    }
}
