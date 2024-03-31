public class Brick {
    private int X;
    private int Y;

    private String color;

    public Brick() {
    }

    public Brick(int X, int Y, String color) {
        this.X = X;
        this.Y = Y;
        this.color = color;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
