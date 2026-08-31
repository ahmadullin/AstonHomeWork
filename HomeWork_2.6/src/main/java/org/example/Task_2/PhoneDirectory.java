package org.example.Task_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PhoneDirectory {
    private Map<String, List<String>> directory;

    public PhoneDirectory() {
        this.directory = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        if (directory.containsKey(lastName)) {
            List<String> phones = directory.get(lastName);
            phones.add(phoneNumber);
        } else {
            List<String> phones = new ArrayList<>();
            phones.add(phoneNumber);
            directory.put(lastName, phones);
        }
    }

    public void get(String lastName) {
        List<String> phones = directory.get(lastName);
        if (phones != null) {
            System.out.print(lastName + ": ");
            for (int i = 0; i < phones.size(); i++) {
                System.out.print(phones.get(i));
                if (i < phones.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
