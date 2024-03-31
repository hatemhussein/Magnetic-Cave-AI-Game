import java.util.ArrayList;
import java.util.List;

public class TreeOfBricks {
    int data;
    int x;
    int y;

    ArrayList<TreeOfBricks> child;


    public TreeOfBricks(int data,int x,int y ) {
        this.data = data;
        this.child = new ArrayList<>();
        this.x=x;
        this.y=y;

    }

    public TreeOfBricks() {

    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TreeOfBricks getChildren(int index) {
        return child.get(index);
    }

    public void addChild(TreeOfBricks childs) {
        child.add(childs);
    }
}
