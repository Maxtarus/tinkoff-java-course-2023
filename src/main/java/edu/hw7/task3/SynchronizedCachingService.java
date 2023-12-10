package edu.hw7.task3;

import java.util.List;

public class SynchronizedCachingService extends CashingService {
    @Override
    public synchronized void add(Person person) {
        synchronized (peopleById) {
            super.add(person);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (peopleById) {
            super.delete(id);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (peopleByName) {
            return super.findByName(name);
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        synchronized (peopleByAddress) {
            return super.findByAddress(address);
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        synchronized (peopleByPhone) {
            return super.findByPhone(phone);
        }
    }
}
