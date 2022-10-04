package seamcarving;

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class DualGradientEnergyFunction implements EnergyFunction {
    @Override
    public double apply(Picture picture, int x, int y) {
        double result = 0;
        double dx = 0;
        double dy = 0;
        if (((x == 0)||(x == picture.width() -1))&&((y == 0)||(y == picture.height() - 1))){ //accounts for all 4 corners
            int deltaX = 0;
            int deltaY = 0;
            if (x == 0) { //top left corner
                deltaX = 1;
            } else if (x == picture.width() -1) { //at right & top
                deltaX = -1;
            }
            if (y == 0) { //at right & bottom
                deltaY = 1;
            } else if (y == picture.height() - 1) {
                deltaY = -1;
            }
            dx = forwardBackwardDiffX_RED(picture, x, y, deltaX)
                + forwardBackwardDiffX_GREEN(picture, x, y, deltaX)
                + forwardBackwardDiffX_BLUE(picture, x, y, deltaX);
            dy = forwardBackwardDiffY_RED(picture, x, y, deltaY)
                + forwardBackwardDiffY_GREEN(picture, x, y, deltaY)
                + forwardBackwardDiffY_BLUE(picture, x, y, deltaY);

        } else if (((x == 0)||(x == picture.width() -1))) { //left & right edge
            int deltaX = 0;
            if (x == 0) { //at top and left edge
                deltaX = 1;
            } else if (x == picture.width() -1) { //at right & top
                deltaX = -1;
            }
            dx = forwardBackwardDiffX_RED(picture, x, y, deltaX)
                + forwardBackwardDiffX_GREEN(picture, x, y, deltaX)
                + forwardBackwardDiffX_BLUE(picture, x, y, deltaX);
            dy = centralDifferenceY_RED(picture, x, y)
                + centralDifferenceY_GREEN(picture, x, y)
                + centralDifferenceY_BLUE(picture, x, y);
        } else if (((y == 0))||(y == picture.height() - 1)) { //top & bottom edge
            int deltaY = 0;
            if (y == 0) { //at right & bottom
                deltaY = 1;
            } else if (y == picture.height() - 1) {
                deltaY = -1;
            }
            dx = centralDifferenceX_RED(picture, x, y)
                + centralDifferenceX_GREEN(picture, x, y)
                + centralDifferenceX_BLUE(picture, x, y);
            dy = forwardBackwardDiffY_RED(picture, x, y, deltaY)
                + forwardBackwardDiffY_GREEN(picture, x, y, deltaY)
                + forwardBackwardDiffY_BLUE(picture, x, y, deltaY);
        } else { //in the middle
            dx = centralDifferenceX_RED(picture, x, y)
                + centralDifferenceX_GREEN(picture, x, y)
                + centralDifferenceX_BLUE(picture, x, y);
            dy = centralDifferenceY_RED(picture, x, y)
                + centralDifferenceY_GREEN(picture, x, y)
                + centralDifferenceY_BLUE(picture, x, y);
        }
        result = Math.sqrt(dx + dy);
        return result;
    }
    private double centralDifferenceX_RED (Picture picture, int x, int y) {
        //f_x = f(x+1 , y) - f(x-1 , y)
        Color color1 = picture.get(x+1, y);
        Color color2 = picture.get(x-1, y);
        return Math.pow((color1.getRed() - color2.getRed()), 2);
    }
    private double centralDifferenceX_GREEN (Picture picture, int x, int y) {
        //f_x = f(x+1 , y) - f(x-1 , y)
        Color color1 = picture.get(x+1, y);
        Color color2 = picture.get(x-1, y);
        return Math.pow((color1.getGreen() - color2.getGreen()), 2);
    }
    private double centralDifferenceX_BLUE (Picture picture, int x, int y) {
        //f_x = f(x+1 , y) - f(x-1 , y)
        Color color1 = picture.get(x+1, y);
        Color color2 = picture.get(x-1, y);
        return Math.pow((color1.getBlue() - color2.getBlue()), 2);
    }

    private double centralDifferenceY_RED (Picture picture, int x, int y) {
        //f_y = f(x , y + 1) - f(x , y-1)
        Color color1 = picture.get(x, y+1);
        Color color2 = picture.get(x, y-1);
        return Math.pow((color1.getRed() - color2.getRed()), 2);
    }
    private double centralDifferenceY_GREEN (Picture picture, int x, int y) {
        //f_y = f(x , y + 1) - f(x , y-1)
        Color color1 = picture.get(x, y+1);
        Color color2 = picture.get(x, y-1);
        return Math.pow((color1.getGreen() - color2.getGreen()), 2);
    }
    private double centralDifferenceY_BLUE (Picture picture, int x, int y) {
        //f_y = f(x , y + 1) - f(x , y-1)
        Color color1 = picture.get(x, y+1);
        Color color2 = picture.get(x, y-1);
        return Math.pow((color1.getBlue() - color2.getBlue()), 2);
    }

    private double forwardBackwardDiffX_RED (Picture picture, int x, int y, int delta) {
        //f_x = −3f(x,y)+4f(x+δ,y)−f(x+2δ,y)
        Color color1 = picture.get(x+delta, y);
        Color color2 = picture.get(x+2*delta, y);
        Color color3 = picture.get(x, y);
        return Math.pow((-3*color3.getRed() + 4*color1.getRed() - color2.getRed()), 2);
    }
    private double forwardBackwardDiffX_GREEN (Picture picture, int x, int y, int delta) {
        //f_x = −3f(x,y)+4f(x+δ,y)−f(x+2δ,y)
        Color color1 = picture.get(x+delta, y);
        Color color2 = picture.get(x+2*delta, y);
        Color color3 = picture.get(x, y);
        return Math.pow((-3*color3.getGreen() + 4*color1.getGreen() - color2.getGreen()), 2);
    }
    private double forwardBackwardDiffX_BLUE (Picture picture, int x, int y, int delta) {
        //f_x = −3f(x,y)+4f(x+δ,y)−f(x+2δ,y)
        Color color1 = picture.get(x+delta, y);
        Color color2 = picture.get(x+2*delta, y);
        Color color3 = picture.get(x, y);
        return Math.pow((-3*color3.getBlue() + 4*color1.getBlue() - color2.getBlue()), 2);
    }

    private double forwardBackwardDiffY_RED (Picture picture, int x, int y, int delta) {
        //f_y = −3f(x,y)+4f(x,y+δ)−f(x,y+2δ)
        Color color1 = picture.get(x, y+delta);
        Color color2 = picture.get(x, y+2*delta);
        Color color3 = picture.get(x, y);
        return Math.pow((-3*color3.getRed() + 4*color1.getRed() - color2.getRed()), 2);
    }
    private double forwardBackwardDiffY_GREEN (Picture picture, int x, int y, int delta) {
        //f_y = −3f(x,y)+4f(x,y+δ)−f(x,y+2δ)
        Color color1 = picture.get(x, y+delta);
        Color color2 = picture.get(x, y+2*delta);
        Color color3 = picture.get(x, y);
        return Math.pow((-3*color3.getGreen() + 4*color1.getGreen() - color2.getGreen()), 2);
    }
    private double forwardBackwardDiffY_BLUE (Picture picture, int x, int y, int delta) {
        //f_y = −3f(x,y)+4f(x,y+δ)−f(x,y+2δ)
        Color color1 = picture.get(x, y+delta);
        Color color2 = picture.get(x, y+2*delta);
        Color color3 = picture.get(x, y);
        return Math.pow((-3*color3.getBlue() + 4*color1.getBlue() - color2.getBlue()), 2);
    }
}
