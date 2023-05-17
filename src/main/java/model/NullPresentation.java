package model;


public class NullPresentation extends Presentation{

    public NullPresentation(){
        super();
        TextItem title = new TextItem(1, "Title");
        super.getShowList().add(new Slide(title));
        super.currentSlideNumber = 0;

    }


    


    
}
