package view;

import java.util.ArrayList;

import model.Presentation;
import model.Slide;

public class PresentationView {
    
    private Presentation presentation;
    private SlideViewerComponent slideViewComponent;
    private int currentSlideNumber = 0;


    public PresentationView(){}

    public void setShowView(SlideViewerComponent slideViewComponent) {
        this.slideViewComponent = slideViewComponent;
    }

    public void setSlideNumber(int number) {
        currentSlideNumber = number;
        if (slideViewComponent != null) {
          slideViewComponent.update(presentation, getCurrentSlide());
        }
      }

      public Slide getCurrentSlide() {
        return this.presentation.getSlide(currentSlideNumber);
      }
    
      public void prevSlide() {
        if (currentSlideNumber > 0) {
          setSlideNumber(currentSlideNumber - 1);
        }
      }
    
      public void nextSlide() {
        if (currentSlideNumber < (this.presentation.getShowList().size() - 1)) {
          setSlideNumber(currentSlideNumber + 1);
        }
      }
    
      public void clear() {
          this.presentation.setShowList(new ArrayList<Slide>());
        setSlideNumber(-1);
      }
}
