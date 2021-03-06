package common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;

public abstract class CollidableObject extends GameObject {

    protected boolean isLive;
    protected RotatablePolygon hitbox;

    public CollidableObject(int x, int y, String image) {
        super(x, y, image);
        buildHitbox();
    }

    public CollidableObject(int x, int y, String image, int frameCount) {
        super(x, y, image, frameCount);
        buildHitbox();
    }

    private void buildHitbox() {
        int xPoints[], yPoints[];

        int x2, y2;
        x2 = (int) this.x + sprite.getWidth();
        y2 = (int) this.y + sprite.getHeight();
        xPoints = new int[]{(int) this.x, x2, x2, (int) this.x};
        yPoints = new int[]{(int) this.y, (int) this.y, y2, y2};
        hitbox = new RotatablePolygon(xPoints, yPoints, 4);
    }

    protected void trimHitbox(int trimX1, int trimX2, int trimY1, int trimY2) {
        hitbox.getXPoints()[0] += trimX1;
        hitbox.getXPoints()[1] += trimX2;
        hitbox.getXPoints()[2] += trimX2;
        hitbox.getXPoints()[3] += trimX1;

        hitbox.getYPoints()[0] += trimY1;
        hitbox.getYPoints()[1] += trimY1;
        hitbox.getYPoints()[2] += trimY2;
        hitbox.getYPoints()[3] += trimY2;

        hitbox.updatePoints();
    }

    public boolean collides(CollidableObject object) {
        Area myArea, otherArea;
        myArea = new Area(hitbox);
        otherArea = new Area(object.hitbox);

        myArea.intersect(otherArea);
        return !myArea.isEmpty();
    }

    public boolean isLiveNow() {
        return isLive;
    }

    public void setLive(boolean flag) {
        isLive = flag;
    }
    
    public Point getCenter() {
        return hitbox.getCenter();
    }

    public void moveTo(int destinationX, int destinationY) {
        double oldX, oldY;

        oldX = this.x;
        oldY = this.y;
        this.x = destinationX;
        this.y = destinationY;

        hitbox.translate(((int) this.x - (int) oldX), ((int) this.y - (int) oldY));
    }
    
    public void shiftBy(int shiftX, int shiftY) {
        moveTo((int)this.x + shiftX, (int)this.y + shiftY);
    }

    @Override
    public void repaint(Graphics graphics) {
        graphics.drawImage(sprite.getImage(0), (int) x, (int) y, null);
    }

    public void drawHitbox(Graphics graphics) {
        Graphics2D g2D = (Graphics2D) graphics.create();

        g2D.setColor(Color.BLUE);
        g2D.drawPolygon(hitbox);

        g2D.setColor(Color.RED);
        g2D.drawOval(hitbox.getCenter().x, hitbox.getCenter().y, 3, 3);
        g2D.drawOval((int)this.x, (int)this.y, 3, 3);

        g2D.dispose();
    }
}
