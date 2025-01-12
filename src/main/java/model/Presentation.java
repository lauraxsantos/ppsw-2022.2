package model;

import java.util.ArrayList;

import view.SlideViewerComponent;

public class Presentation {

  private String title;
  protected ArrayList<Slide> showList = null;
  private SlideViewerComponent slideViewComponent = null;
  public int currentSlideNumber = 0;

  public Presentation() {
    // slideViewComponent = null;
    clear();
  }

  public Presentation(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
    clear();
  }
  

  public int getSize() {
    return showList.size();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String nt) {
    title = nt;
  }

  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  public int getSlideNumber() {
    return currentSlideNumber;
  }

  public void setSlideNumber(int number) {
    currentSlideNumber = number;
    if (slideViewComponent != null) {
      slideViewComponent.update(this, getCurrentSlide());
    }
  }

  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  public void nextSlide() {
    if (currentSlideNumber < (showList.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  public void add(Slide slide) {
		if (this.showList == null) {
			this.showList = new ArrayList<>();
		}

		this.showList.add(this.showList.size(), slide);;
	}

  public void clear() {
    showList = new ArrayList<Slide>();
    setSlideNumber(-1);
  }

  public void setShowList(ArrayList<Slide> showList){
    this.showList = showList;
  }

  public ArrayList<Slide> getShowList(){
    return this.showList;
  }

  public void append(Slide slide) {
    showList.add(slide);
  }

  public Slide getSlide(int number) {
    if (number < 0 || number >= getSize()) {
      return null;
    }
    return (Slide) showList.get(number);
  }

  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

}
