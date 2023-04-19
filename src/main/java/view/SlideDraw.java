package view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import model.Slide;
import model.SlideItem;

public class SlideDraw {
	
	  public final static int WIDTH = 1200;
	  public final static int HEIGHT = 800;
	
	  
	  public void drawSlide(Graphics g, Rectangle area, ImageObserver view, Slide slide) {
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
		  }

}
