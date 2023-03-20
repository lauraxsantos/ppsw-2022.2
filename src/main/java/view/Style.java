package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import model.BitmapItem;

//estilização

public class Style {

  private static Style[] styles;

  private static final String FONTNAME = "Helvetica";
  public int indent;
  public Color color;
  Font font;
  int fontSize;
  public int leading;

  public static void createStyles() {
    styles = new Style[5];
    styles[0] = new Style(0, Color.red, 48, 20); // nível 0
    styles[1] = new Style(20, Color.blue, 40, 10); // nível 1
    styles[2] = new Style(50, Color.black, 36, 10); // nível 2
    styles[3] = new Style(70, Color.black, 30, 10); // nivel 3
    styles[4] = new Style(90, Color.black, 24, 10); // nível 4
  }

  public static Style getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }

    return styles[level];
  }

  public Style(int indent, Color color, int points, int leading) {
    this.indent = indent;
    this.color = color;
    font = new Font(FONTNAME, Font.BOLD, fontSize = points);
    this.leading = leading;
  }

  public String toString() {
    return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }

public void draw(int x, int y, float scale, Graphics g, BitmapItem bitmapItem, ImageObserver observer) {
    int width = x + (int) (indent * scale);
    int height = y + (int) (leading * scale);

    g.drawImage(bitmapItem.bufferedImage, width, height, (int) (bitmapItem.bufferedImage.getWidth(observer) * scale),
        (int) (bitmapItem.bufferedImage.getHeight(observer) * scale), observer);
  }
}
