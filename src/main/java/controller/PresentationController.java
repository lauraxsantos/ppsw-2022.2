package controller;

import java.io.IOException;

import model.DemoPresentation;
import model.Presentation;
import view.SlideViewerFrame;

public class PresentationController {
	//criando um controlador para a apresentação
	//separando responsabilidades
	
	 protected static final String JABVERSION = "Jabberpoint 1.6 -";
	
	public Presentation load(String...args) throws IOException {
		
		Presentation presentation = new Presentation();
		DemoPresentation demoPresentation = new DemoPresentation();
		
		new SlideViewerFrame(JABVERSION, presentation);	
		
		if (args.length == 0) {
			demoPresentation.loadFile(presentation, "");
			// Accessor.getDemoAccessor().loadFile(presentation, "");
		} else {
			new XMLAccessor().loadFile(presentation, args[0]);
		}
		
		presentation.setSlideNumber(0);
		
		return presentation;
	}
	    
	  
}
