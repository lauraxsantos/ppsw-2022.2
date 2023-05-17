package view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import controller.KeyController;
import controller.MenuController;
import model.Presentation;

public class SlideViewerFrame extends JFrame {

  private static final long serialVersionUID = 3227L;

  private static final String JABTITLE = "Jabberpoint 1.6";

  public final static int WIDTH = 1200;
  public final static int HEIGHT = 800;
  public Presentation presentation2;

  public SlideViewerFrame(String title, Presentation presentation) {
    super(title);

    this.presentation2 = presentation;

    SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);

    presentation.setShowView(slideViewerComponent);

    setupWindow(slideViewerComponent, presentation);
  }

  public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
    setTitle(JABTITLE);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    getContentPane().add(slideViewerComponent);
    addKeyListener(new KeyController(presentation));
    setMenuBar(new MenuController(this, presentation));
    setSize(new Dimension(WIDTH, HEIGHT));

    setVisible(true);
  }

  public final Presentation currentPresentation(){
    return this.presentation2;
  }
  

}
