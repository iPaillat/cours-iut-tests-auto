package tp.bar;

import java.util.ArrayList;
import java.util.List;

public class Bar {
    private final String name;
    private int capacity;
    private final List<String> clients = new ArrayList<>();

    public Bar(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean enter(String customerName) {
        if (clients.size() < capacity) {
            clients.add(customerName);
            return true;
        }
        return false;
    }

    public int getClientCount() {
        return clients.size();
    }

    public boolean hasClient(String name) {
        return clients.contains(name);
    }
}
