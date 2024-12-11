package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Конкретная реализация интерфейса Observable.
 * Этот класс представляет наблюдаемый объект, который может иметь наблюдателей и уведомлять их об изменениях.
 */
public class ConcreteObservable implements Observable {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Уведомляет всех зарегистрированных наблюдателей об изменении данных.
     *
     * @param data данные, которые отправляются наблюдателям
     */
    @Override
    public void notifyObservers(int data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }

    /**
     * Добавляет наблюдателя в список наблюдателей.
     *
     * @param observer наблюдатель, который будет добавлен
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Удаляет наблюдателя из списка наблюдателей.
     *
     * @param observer наблюдатель, который будет удалён
     */
    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }
}
