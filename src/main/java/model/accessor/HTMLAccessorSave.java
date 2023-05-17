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

public class HTMLAccessorSave extends AccessorSave{

    @Override
    public void saveFile(Presentation presentation, String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        out.println("<!DOCTYPE>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + presentation.getTitle() + "</title>");
        out.println("</head>");
        out.println("<body>");
        
        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++){
            Slide slide = presentation.getSlide(slideNumber);
            out.println("<div class= 'slide" + slideNumber + "'>");
            out.println("<h1 id='titulo" + slideNumber + "'>" + slide.getTitle() + "</h1>");
            Vector<SlideItem> slideItems = slide.getSlideItems();
            for(int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++){
                SlideItem slideItem = slideItems.elementAt(itemNumber);
                slideItem.getLevel();

                if(slideItem instanceof BitmapItem){
                    BitmapItem bitmapItem = (BitmapItem) slideItem;
                    out.println("<img id= 'text" + itemNumber + "'" + "class= 'texto" + slideNumber + "'" + "src=\"" + bitmapItem.getName() + "\"/>");
                }else if (slideItem instanceof TextItem){
                    // String type;
                    // if(slideItem.getLevel() == 1){
                    //     type = "h2";
                    // } else if(slideItem.getLevel() == 2){
                    //     type = "h3";
                    // } else if(slideItem.getLevel() == 3){
                    //     type = "h4";
                    // } else if(slideItem.getLevel() == 4){
                    //     type = "h5";
                    // } else{
                    //     type = "p";
                    // }
                    TextItem textItem = (TextItem) slideItem;
                    out.println("<p class='texto" + slideNumber + "'" + "id='text" + itemNumber + "'>" + textItem.getText() + "</p>");
                }
            }
            out.println("</div>");

        
        }
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
    
    
}
