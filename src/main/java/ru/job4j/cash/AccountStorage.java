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
        return result;
    }

    public synchronized boolean delete(int id) {
        boolean result = false;
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
        return result;
    }
}
