package com.mvcvotos.models;

import java.util.ArrayList;
import java.util.List;

import com.mvcvotos.views.Observer;

public class Model {
    protected List<Observer> observers;

    public void addObserver(Observer observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

}
