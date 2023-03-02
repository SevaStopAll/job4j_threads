package ru.job4j.cash;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        boolean result = true;
        if (accounts.putIfAbsent(account.id(), account) != null) {
            return false;
        }
        return result;
    }

    public synchronized boolean update(Account account) {
        boolean result = false;
        if (getById(account.id()).isEmpty()) {
            return false;
        }
        accounts.replace(account.id(), account);
        return result;
    }

    public synchronized boolean delete(int id) {
        boolean result = true;
        if (getById(id).isEmpty()) {
            return false;
        }
        accounts.remove(id);
        return result;
    }

    public synchronized Optional<Account> getById(int id) {
        for (Account account : accounts.values()) {
            if (account.id() == id) {
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        Account accountFrom = getById(fromId).get();
        Account accountTo = getById(toId).get();
        if (getById(fromId).isEmpty() || getById(toId).isEmpty()) {
            return false;
        }
        update(new Account(fromId, accountFrom.amount() - amount));
        update(new Account(toId, accountTo.amount() + amount));
        return result;
    }
}
