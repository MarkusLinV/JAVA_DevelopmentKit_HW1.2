package org.example.model.impl;

import org.example.model.Message;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Message message);
}
