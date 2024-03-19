package Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdGenerator {
    private Set<Integer> uniqueIds;
    private Random random;

    public IdGenerator() {
        uniqueIds = new HashSet<>();
        random = new Random();
        uniqueIds.add(123123);
    }

    public Integer generateUniqueId() {
        Integer newId;
        do {
            newId = 100000 + random.nextInt(900000);
        } while (uniqueIds.contains(newId));
        uniqueIds.add(newId);
        return newId;
    }
}