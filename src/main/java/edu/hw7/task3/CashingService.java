package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CashingService implements PersonDatabase {
    protected final Map<Integer, Person> peopleById = new HashMap<>();
    protected final Map<String, List<Person>> peopleByName = new HashMap<>();
    protected final Map<String, List<Person>> peopleByAddress = new HashMap<>();
    protected final Map<String, List<Person>> peopleByPhone = new HashMap<>();

    @Override
    public void add(Person person) {
        peopleById.put(person.id(), person);
        peopleByName.computeIfAbsent(person.name(), name -> new ArrayList<>()).add(person);
        peopleByAddress.computeIfAbsent(person.address(), address -> new ArrayList<>()).add(person);
        peopleByPhone.computeIfAbsent(person.phoneNumber(), phone -> new ArrayList<>()).add(person);
    }


    @Override
    public void delete(int id) {
        Person person = peopleById.remove(id);

        if (person != null) {
            peopleByName.get(person.name()).remove(person);
            peopleByAddress.get(person.address()).remove(person);
            peopleByPhone.get(person.phoneNumber()).remove(person);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        if (!peopleByName.containsKey(name)) {
            return List.of();
        }

        return peopleByName.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        if (!peopleByAddress.containsKey(address)) {
            return List.of();
        }

        return peopleByAddress.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        if (!peopleByPhone.containsKey(phone)) {
            return List.of();
        }

        return peopleByPhone.get(phone);
    }
}
