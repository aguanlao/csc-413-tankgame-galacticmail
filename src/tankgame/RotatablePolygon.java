package tankgame;

import java.awt.*;

public class RotatablePolygon extends Polygon {

    private final double[] xPrecise, yPrecise;
    private final Point center;

    public RotatablePolygon(int[] xPoints, int[] yPoints, int numPoints) {
        super(xPoints, yPoints, numPoints);
        xPrecise = new double[numPoints];
        yPrecise = new double[numPoints];
        for (int i = 0; i < numPoints; i++) {
            xPrecise[i] = xPoints[i];
            yPrecise[i] = yPoints[i];
        }
        center = new Point();
        calculateCenter();
    }
    
    public double[] getXPoints() {
        return xPrecise;
    }

    public double[] getYPoints() {
        return yPrecise;
    }

    public void updatePoints() {
        for (int i = 0; i < npoints; i++) {
            xpoints[i] = (int) xPrecise[i];
            ypoints[i] = (int) yPrecise[i];
        }
        calculateCenter();
    }

    public void calculateCenter() {
        double centerX, centerY;
        centerX = centerY = 0;
        for (int i = 0; i < npoints; i++) {
            centerX += xPrecise[i];
            centerY += yPrecise[i];
        }

        center.x = (int) centerX / npoints;
        center.y = (int) centerY / npoints;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(int x, int y) {
        center.x = x;
        center.y = y;
    }

    @Override
    public void translate(int translateX, int translateY) {
        super.translate(translateX, translateY);
        for(int i = 0; i < npoints; i++) {
            xPrecise[i]+=translateX;
            yPrecise[i]+=translateY;
        }
        calculateCenter();
    }

    public void rotate(double angle) {
        for (int i = 0; i < npoints; i++) { 
            Point rotated = rotatePoint(xPrecise[i], yPrecise[i], angle);
            xpoints[i] = rotated.x;
            ypoints[i] = rotated.y;
        }
    }

    public Point rotatePoint(double x, double y, double angle) {
        double rads = Math.toRadians(angle);
        double x1 = x - center.x;
        double y1 = y - center.y;

        double rotatedX = x1 * Math.cos(rads) - y1 * Math.sin(rads);
        double rotatedY = x1 * Math.sin(rads) + y1 * Math.cos(rads);
        
        double newX = rotatedX + center.x;
        double newY = -rotatedY + center.y;
        return new Point((int)newX, (int)newY);
    }
}
