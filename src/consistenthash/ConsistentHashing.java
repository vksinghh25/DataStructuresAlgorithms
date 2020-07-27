package consistenthash;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {

    private SortedMap<Integer, String> consistentRing;
    private static int SIZE = 360;

    ConsistentHashing() {
        consistentRing = new TreeMap<>();
    }

    public void addServer(String server) {
        int hashCode = Math.abs(server.hashCode());
        int positionOnRing = hashCode % SIZE;
        consistentRing.put(positionOnRing, server);
    }

    public String getServer(Object key) {
        int hashCode = Math.abs(key.hashCode());
        int positionOnRing = hashCode % SIZE;

        Integer serverPosition;
        if (!consistentRing.containsKey(positionOnRing)) {
            SortedMap<Integer, String> tailMap = consistentRing.tailMap(positionOnRing);
            serverPosition = tailMap.isEmpty() ? consistentRing.firstKey() : tailMap.firstKey();
        } else {
            serverPosition = positionOnRing;
        }

        String server = consistentRing.get(serverPosition);
        System.out.println("(" + positionOnRing + " <--> " + server + ")");
        return server;
    }

    public void printMap() {
        Iterator<Map.Entry<Integer, String>> iterator = consistentRing.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.toString());
        }
    }
}
