package quadtrees;

public class DataNode {

    private Point point;
    private String data;

    public DataNode(Point point, String data) {
        this.point = point;
        this.data = data;
    }

    public Point getPoint() {
        return point;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "DataNode{" +
                "point=" + point +
                ", data='" + data + '\'' +
                '}';
    }
}
