package com.epam.multithreading.model.clients;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFactory<T extends Client> {
    public abstract T create();

    public List<T> createList(int amount) {
        List<T> list = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            T element = create();
            element.setHelpDesk(HelpDeskImpl.getInstance());
            list.add(element);
        }

        return list;
    }
}
