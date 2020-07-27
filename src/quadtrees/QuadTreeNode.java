package quadtrees;

import java.util.ArrayList;
import java.util.List;

public class QuadTreeNode {

    private Boundary boundary;
    List<DataNode> nodeList;
    QuadTreeNode southWest, southEast, northWest, northEast;
    private static int MAX_SIZE = 4;

    public QuadTreeNode(Boundary boundary) {
        this.boundary = boundary;
        this.nodeList = new ArrayList<>();
        southEast = southWest = northEast = northWest = null;

        System.out.println("Creating Node with boundary : " + boundary.toString());
    }

    public List<DataNode> getNeighbors(Point point) {

        if (!boundary.containsPoint(point)) {
            return List.of();
        }

        if (nodeList != null)
            return nodeList;

        if (southEast.boundary.containsPoint(point)) {
            return southEast.getNeighbors(point);
        } else if (southWest.boundary.containsPoint(point)) {
            return southWest.getNeighbors(point);
        } else if (northEast.boundary.containsPoint(point)) {
            return northEast.getNeighbors(point);
        } else {
            return northWest.getNeighbors(point);
        }
    }

    private void split() {
        int xMin = boundary.getBottomLeft().getX();
        int yMin = boundary.getBottomLeft().getY();
        int xMax = boundary.getTopRight().getX();
        int yMax = boundary.getTopRight().getY();

        System.out.println("Splitting Node with boundary : " + boundary.toString());
        System.out.println();

        int xOffset = boundary.getBottomLeft().getX()
                + (boundary.getTopRight().getX() - boundary.getBottomLeft().getX()) / 2;
        int yOffset = boundary.getBottomLeft().getY()
                + (boundary.getTopRight().getY() - boundary.getBottomLeft().getY()) / 2;

        southWest = new QuadTreeNode(new Boundary(xMin, yMin, xOffset, yOffset));
        southEast = new QuadTreeNode(new Boundary(xOffset, yMin, xMax, yOffset));
        northWest = new QuadTreeNode(new Boundary(xMin, yOffset, xOffset, yMax));
        northEast = new QuadTreeNode(new Boundary(xOffset, yOffset, xMax, yMax));
    }

    public boolean addData(Point point, String data) {

        // first thing to find out whether the point exists in the boundary
        if (!boundary.containsPoint(point)) {
            return false;
        }

        // this is indeed a leaf Node
        if (nodeList != null && nodeList.size() < MAX_SIZE) {
            nodeList.add(new DataNode(point, data));
            return true;
        }

        // time to split the node
        if (northEast == null)
            split();

        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }

        nodeList.add(new DataNode(point, data));

        for (DataNode node : nodeList) {
            // check which sub-node it belongs to and put it there
            if (southWest.boundary.containsPoint(node.getPoint())) {
                southWest.addData(node.getPoint(), node.getData());
            } else if (southEast.boundary.containsPoint(node.getPoint())) {
                southEast.addData(node.getPoint(), node.getData());
            } else if (northWest.boundary.containsPoint(node.getPoint())) {
                northWest.addData(node.getPoint(), node.getData());
            } else {
                northEast.addData(node.getPoint(), node.getData());
            }
        }

        nodeList = null;

        return true;
    }
}
