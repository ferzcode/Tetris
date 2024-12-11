package observer;

/**
 * Интерфейс, представляющий наблюдаемый объект.
 * Объекты, реализующие этот интерфейс, могут наблюдаться другими объектами, называемыми наблюдателями.
 */
public interface Observable {

    /**
     * Уведомляет всех зарегистрированных наблюдателей об изменении данных.
     *
     * @param data данные, которые будут отправлены наблюдателям
     */
    void notifyObservers(int data);

    /**
     * Добавляет наблюдателя в список наблюдателей.
     *
     * @param observer наблюдатель, который будет добавлен
     */
    void addObserver(Observer observer);

    /**
     * Удаляет наблюдателя из списка наблюдателей.
     *
     * @param observer наблюдатель, который будет удалён
     */
    void deleteObserver(Observer observer);
}

