package quadtrees;

public class Boundary {

    private Point bottomLeft;
    private Point topRight;

    public Boundary(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public Boundary(int xMin, int yMin, int xMax, int yMax) {
        this.bottomLeft = new Point(xMin, yMin);
        this.topRight = new Point(xMax, yMax);
    }

    public boolean containsPoint(Point point) {
        return point.getX() >= bottomLeft.getX() && point.getX() <= topRight.getX()
                && point.getY() >= bottomLeft.getY() && point.getY() <= topRight.getY();
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public Point getTopRight() {
        return topRight;
    }

    @Override
    public String toString() {
        return "Boundary{" +
                "bottomLeft=" + bottomLeft +
                ", topRight=" + topRight +
                '}';
    }
}
