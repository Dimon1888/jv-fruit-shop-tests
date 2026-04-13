package strategy.impl;

import dao.FruitDao;
import db.FruitStorage;
import model.FruitTransaction;
import strategy.OperationHandler;

public class ReturnOperation implements OperationHandler {
    // 1. Створюємо приватне поле для DAO
    private final FruitDao fruitDao;

    // 2. Зберігаємо передану залежність у поле класу
    public ReturnOperation(FruitDao fruitDao) {
        this.fruitDao = fruitDao;
    }

    @Override
    public void handle(FruitTransaction transaction) {
        // 3. Отримуємо дані з об'єкта транзакції
        String fruit = transaction.getFruit();
        int quantity = transaction.getQuantity();

        // 4. Робимо розрахунок логіки
        int currentQuantity = fruitDao.getQuantity(fruit);
        int newQuantity = currentQuantity + quantity;

        // 5. Оновлюємо дані ТІЛЬКИ через DAO
        fruitDao.update(fruit, newQuantity);
    }
}
