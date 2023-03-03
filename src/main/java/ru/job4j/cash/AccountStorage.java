package ru.job4j.cash;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) != null;
    }

    public synchronized Account update(Account account) {
        return accounts.replace(account.id(), account);
    }

    public synchronized Account delete(int id) {
        return accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = true;
        if (getById(fromId).isEmpty() || getById(toId).isEmpty()) {
            return false;
        }
        update(new Account(fromId, accounts.get(fromId).amount() - amount));
        update(new Account(toId, accounts.get(toId).amount() + amount));
        return result;
    }
}
