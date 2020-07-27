package consistenthash;

public class ConsistentHashingRunner {

    public static void main(String[] args) {

        ConsistentHashing consistentHashing = new ConsistentHashing();

        consistentHashing.addServer("189.189.189.189");
        consistentHashing.addServer("23.23.23.23");
        consistentHashing.addServer("140.30.140.30");
        consistentHashing.addServer("12.12.60.60");

        consistentHashing.printMap();

        System.out.println();
        System.out.println("Getting servers for some keys");
        for (int i = 0; i < 10; i++) {
            String randomKey = "Key:" + (int) (Math.random() * 1000);
            consistentHashing.getServer(randomKey);
        }
    }
}
