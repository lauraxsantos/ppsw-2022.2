package model.accessor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import model.BitmapItem;
import model.Presentation;
import model.Slide;
import model.SlideItem;
import model.TextItem;

public class JSONAccessorSave extends AccessorSave{
    @Override
	public void saveFile(Presentation presentation, String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		
		out.println("{");
		out.print("\"" + "title" + "\"" + ": ");
		out.println("\"" + presentation.getTitle() + "\"" + ",");
		out.println("\"" +"slides" + "\"" + ": " + "[");
		
		for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++){
			Slide slide = presentation.getSlide(slideNumber);
			out.println("{");
			out.print("\"" + "title" + "\"" + ": ");
			out.println("\"" + slide.getTitle() + "\"" + ",");	
			out.println("\"" + "items" + "\"" + ": " + "[");
			
			Vector<SlideItem> slideItems = slide.getSlideItems();
			int itemNumber;
			for(itemNumber = 0; itemNumber < slideItems.size(); itemNumber++){
				SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
				out.println("{");
				out.println("\"" + "level" + "\"" + ": " + slideItem.getLevel() + ",");
				if(slideItem instanceof TextItem){
					out.print("\"" + "text" + "\"" + ": ");
					out.println("\"" + ((TextItem) slideItem).getText() + "\"" + ",");
					out.println("\"" + "kind" + "\"" + ":" + "\"" + "text" + "\"");
				} else{
					if(slideItem instanceof BitmapItem){
						out.println("\"" + "image" + "\"" + ": ");
						out.println("\"" + ((BitmapItem) slideItem) .getName() + "\"" + ",");
						out.println("\"" + "kind" + "\"" + ":" + "\"" +"image" + "\"" );
					}
				}

				if(itemNumber == (slideItems.size() - 1)){
					out.println("}");
				} else{
					out.println("}" + ",");
				}
			}

			out.println("]");
			if(slideNumber == presentation.getSize() - 1){
				out.println("}");
			} else {
				out.println("}" + ",");
			}
		}

		out.println("]");
		out.println("}");

		out.close();

	}


    
}
