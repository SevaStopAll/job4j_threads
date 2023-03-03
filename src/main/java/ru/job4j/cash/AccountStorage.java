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

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = true;
        Optional<Account> from = getById(fromId);
        Optional<Account> to = getById(toId);
        if (from.isEmpty() || to.isEmpty()) {
            return false;
        }
        update(new Account(fromId, from.get().amount() - amount));
        update(new Account(toId, to.get().amount() + amount));
        return result;
    }
}
