package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.BitmapItem;
import model.Slide;
import model.SlideItem;
import model.Style;
import model.TextItem;

public class DrawSlide {
	
	  public final static int WIDTH = 1200;
	  public final static int HEIGHT = 800;

	  public DrawSlide(){}
	
	  
	  	public void drawSlide(Graphics g, Rectangle area, ImageObserver view, Slide slide) {
		    DrawSlide drawer = new DrawSlide();
			float scale = getScale(area);

		    int y = area.y;

		    SlideItem slideItem = slide.getTitle2();
		    Style style = Style.getStyle(slideItem.getLevel());

			if(slideItem instanceof TextItem){
				drawer.drawTextItem(area.x, y, scale, g, style, view, (TextItem) slideItem);
				y += drawer.getBoundingBoxTextItem(g, view, scale, style, (TextItem) slideItem).height;
			} else if (slideItem instanceof BitmapItem){
				drawer.drawBitMap(area.x, y, scale, g, style, view, (BitmapItem) slideItem);
				y += drawer.getBoundingBoxBitMap(g, view, scale, style, (BitmapItem) slideItem).height;				
			}

		    for (int number = 0; number < slide.getSize(); number++) {
		      slideItem = (SlideItem) slide.getSlideItems().elementAt(number);

		      style = Style.getStyle(slideItem.getLevel());

			if(slideItem instanceof TextItem){
				drawer.drawTextItem(area.x, y, scale, g, style, view, (TextItem) slideItem);
				y += drawer.getBoundingBoxTextItem(g, view, scale, style, (TextItem) slideItem).height;
			} else if (slideItem instanceof BitmapItem){
				drawer.drawBitMap(area.x, y, scale, g, style, view, (BitmapItem) slideItem);
				y += drawer.getBoundingBoxBitMap(g, view, scale, style, (BitmapItem) slideItem).height;				
			}
		    }
		}
	  	private float getScale(Rectangle area) {
		    return Math.min(((float) area.width) / ((float) WIDTH),
		        ((float) area.height) / ((float) HEIGHT));
		}

		public Rectangle getBoundingBoxTextItem(Graphics g, ImageObserver observer, float scale, Style myStyle, TextItem textitem) {
			List<TextLayout> layouts = getLayouts(g, myStyle, scale, textitem);
		
			int xsize = 0, ysize = (int) (myStyle.leading * scale);
		
			Iterator<TextLayout> iterator = layouts.iterator();
		
			while (iterator.hasNext()) {
			  TextLayout layout = iterator.next();
			  Rectangle2D bounds = layout.getBounds();
		
			  if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			  }
		
			  if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			  }
			  ysize += layout.getLeading() + layout.getDescent();
			}
		
			return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
		}

		public void drawTextItem(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver o, TextItem textItem) {
			if (textItem.getText() == null || textItem.getText().length() == 0) {
			  return;
			}
		
			List<TextLayout> layouts = getLayouts(g, myStyle, scale, textItem);
			Point pen = new Point(x + (int) (myStyle.indent * scale), y + (int) (myStyle.leading * scale));
		
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(myStyle.color);
		
			Iterator<TextLayout> it = layouts.iterator();
		
			while (it.hasNext()) {
			  TextLayout layout = it.next();
		
			  pen.y += layout.getAscent();
			  layout.draw(g2d, pen.x, pen.y);
		
			  pen.y += layout.getDescent();
			}
		}
		  
		
		private List<TextLayout> getLayouts(Graphics g, Style s, float scale, TextItem textItem) {
			List<TextLayout> layouts = new ArrayList<TextLayout>();
		
			AttributedString attrStr = textItem.getAttributedString(s, scale);
			Graphics2D g2d = (Graphics2D) g;
		
			FontRenderContext frc = g2d.getFontRenderContext();
			LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
		
			float wrappingWidth = (WIDTH - s.indent) * scale;
		
			while (measurer.getPosition() < textItem.getText().length()) {
			  TextLayout layout = measurer.nextLayout(wrappingWidth);
			  layouts.add(layout);
			}
		
			return layouts;
		}

		public Rectangle getBoundingBoxBitMap(Graphics g, ImageObserver observer, float scale, Style myStyle, BitmapItem bitmapItem) {
			return new Rectangle((int) (myStyle.indent * scale), 0,
				(int) (bitmapItem.bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.leading * scale)) + (int) (bitmapItem.bufferedImage.getHeight(observer) * scale));
		}

		public void drawBitMap(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer, BitmapItem bitmapItem) {
			int width = x + (int) (myStyle.indent * scale);
			int height = y + (int) (myStyle.indent * scale);

			g.drawImage(bitmapItem.bufferedImage, width, height,
					(int) (bitmapItem.bufferedImage.getWidth(observer) * scale),
					(int) (bitmapItem.bufferedImage.getHeight(observer) * scale), observer);
		  
		}

 }
