package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


import javax.swing.JComponent;
import javax.swing.JFrame;

import model.Presentation;
import model.Slide;
import model.TextItem;

//desenha a representação do objeto slide na tela

public class SlideViewerComponent extends JComponent {
  private static final long serialVersionUID = 227L;

  private static final Color BGCOLOR = Color.white;
  private static final Color COLOR = Color.black;
  private static final String FONTNAME = "Dialog";
  private static final int FONTSTYLE = Font.BOLD;
  private static final int FONTHEIGHT = 10;
  private static final int XPOS = 1100;
  private static final int YPOS = 20;

  private Slide slide;
  protected TextItem title;
  private Font labelFont = null;
  private Presentation presentation = null;
  private JFrame frame = null;
  private SlideDraw draw = new SlideDraw();
  
  /*public final static int WIDTH = 1200;
  public final static int HEIGHT = 800;*/

  public SlideViewerComponent(Presentation pres, JFrame frame) {
    setBackground(BGCOLOR);
    presentation = pres;
    labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
    this.frame = frame;
  }

  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void update(Presentation presentation, Slide data) {
    if (data == null) {
      repaint();
      return;
    }

    this.presentation = presentation;
    this.slide = data;
    repaint();
    frame.setTitle(presentation.getTitle());
  }

  public void paintComponent(Graphics g) {
    g.setColor(BGCOLOR);
    g.fillRect(0, 0, getSize().width, getSize().height);

    if (presentation.getSlideNumber() < 0 || slide == null) {
      return;
    }

    g.setFont(labelFont);
    g.setColor(COLOR);
    g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(),
        XPOS, YPOS);

    Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
    

    draw.drawSlide(g, area, this, slide);
  }
  
  /*public void draw(Graphics g, Rectangle area, ImageObserver view, Slide slide) {
	    float scale = getScale(area);

	    int y = area.y;

	    SlideItem slideItem = slide.getTitle2();
	    Style style = Style.getStyle(slideItem.getLevel());
	    slideItem.draw(area.x, y, scale, g, style, view);

	    y += slideItem.getBoundingBox(g, view, scale, style).height;

	    for (int number = 0; number < slide.getSize(); number++) {
	      slideItem = (SlideItem) slide.getSlideItems().elementAt(number);

	      style = Style.getStyle(slideItem.getLevel());
	      slideItem.draw(area.x, y, scale, g, style, view);

	      y += slideItem.getBoundingBox(g, view, scale, style).height;
	    }
	  }
  private float getScale(Rectangle area) {
	    return Math.min(((float) area.width) / ((float) WIDTH),
	        ((float) area.height) / ((float) HEIGHT));
	  }*/

}
