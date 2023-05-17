package model.accessor;

import model.Presentation;
import model.Slide;

import java.io.IOException;


public abstract class AccessorLoad {
    
    protected static final String DEFAULT_API_TO_USE = "dom";
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
  
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
    int slideNumber, itemNumber, max = 0, maxItems = 0;
    
    public void loadFile(Presentation presentation, String fileName) throws IOException{
        presentation.setTitle("Nova apresentação");
        Slide slide = new Slide();
        slide.setTitle("Título da Apresentação");
        slide.append(1, "Subtítulo");

        presentation.append(slide);
    };
}
