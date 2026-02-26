public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    protected Position getPosition() {
        return new Position(x, y);
    }

    protected void setPosition(Position B){ this.setX(B.getX());
                                            this.setY(B.getY()); }

    public double getDistance(Position A, Position B) {
        return Math.sqrt((Math.pow((A.getX() - B.getX()), 2) + Math.pow((A.getY() - B.getY()), 2)));
    }

    public boolean checkCollision(Position A, Position B, double distance) {
        return getDistance(A, B) < distance;
    }
}
