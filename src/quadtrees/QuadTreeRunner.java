package quadtrees;

import java.util.List;

public class QuadTreeRunner {

    public static void main(String[] args) {

        QuadTreeNode quadTreeNode = new QuadTreeNode(new Boundary(0, 0, 100, 100));

        quadTreeNode.addData(new Point(20, 20), "Data:" + (int) (Math.random() * 1000));
        quadTreeNode.addData(new Point(35, 35), "Data:" + (int) (Math.random() * 1000));
        quadTreeNode.addData(new Point(49, 49), "Data:" + (int) (Math.random() * 1000));
        quadTreeNode.addData(new Point(17, 17), "Data:" + (int) (Math.random() * 1000));
        quadTreeNode.addData(new Point(10, 10), "Data:" + (int) (Math.random() * 1000));
        quadTreeNode.addData(new Point(9, 9), "Data:" + (int) (Math.random() * 1000));

        System.out.println();
        System.out.println("Here are the neighbors for (17, 17)");
        List<DataNode> neighbors = quadTreeNode.getNeighbors(new Point(17, 17));
        for (DataNode neighbor : neighbors) {
            System.out.println(neighbor.toString());
        }

        System.out.println();
        System.out.println("Here are the neighbors for (48, 48)");
        neighbors = quadTreeNode.getNeighbors(new Point(48, 48));
        for (DataNode neighbor : neighbors) {
            System.out.println(neighbor.toString());
        }
    }
}
