package model.accessor;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.BitmapItem;
import model.Presentation;
import model.Slide;
import model.SlideItem;
import model.TextItem;

public class HTMLAccessorLoad extends AccessorLoad {
    @Override
    public void loadFile(Presentation presentation, String fileName) throws IOException {
        try{    
            File input = new File(fileName);
            Document doc = (Document) Jsoup.parse(input, "UTF-8");
        
            String title = doc.select("title").text();;
            presentation.setTitle(title);

            Elements slides = doc.getElementsByTag("div");


            for (int slideNumber = 0; slideNumber < slides.size(); slideNumber++) {
                
                Element oneSlide = (Element) slides.get(slideNumber);
                Slide slide = new Slide();
                
                String id = String.valueOf(slideNumber);
                Element titles = oneSlide.getElementById("titulo" + id);
                slide.setTitle(titles.text());
                
                int itemNumber = 0;
                for (Element item : doc.getElementsByClass("texto" + id)) {
                    String in = String.valueOf(itemNumber);
                    Element textElement = oneSlide.getElementById("text" + in);
                    Elements imgElement = textElement.getElementsByTag("img");
                    String img = imgElement.attr("src");

                    String text = textElement.text();

                    SlideItem slideItem = null;
                    
                    itemNumber += 1;
                    Integer level = 2;

                    if(text.isBlank()){
                        text = " ";
                    }
     
                    if(imgElement.size() > 0){
                        slideItem = new BitmapItem(level, img);
                    } else {
                        slideItem = new TextItem(level, text);
                    }

                    slide.append(slideItem);

                }
                presentation.append(slide);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
}
