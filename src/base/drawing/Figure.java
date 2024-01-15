package base.drawing;

import java.awt.*;

public class Figure {
    private int x, y, width;
    private Color clr;

    public Figure(int x, int y, int width, Color clr) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.clr = clr;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawFigure(int n, Graphics2D g) {
        g.setColor(clr);
        g.fillOval(x, y, width, width);
        g.setColor(Color.black);
        int fontSize = (int) (width * 0.7);
        int fontX = (int) (x + width * 0.32);
        int fontY = (int) (y + width * 0.65);
        Font font = new Font("Georgia", Font.ITALIC, fontSize);
        g.setFont(font);
        String text = "" + n;
        g.drawString(text, fontX, fontY);
    }
}
