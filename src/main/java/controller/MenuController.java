package controller;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.AboutBox;
import model.NullPresentation;
import model.Presentation;
import model.accessor.AccessorSave;
import model.accessor.AccessorLoad;
// import model.accessor.FileManager;
import model.factories.LoadManager;
import model.factories.SaveManager;
import view.SlideViewerComponent;

//controla os itens de menu

public class MenuController extends MenuBar {

  private static final long serialVersionUID = 227L;

  private Frame parent;
  private Presentation presentation;

  protected static final String ABOUT = "Sobre";
  protected static final String FILE = "Arquivo";
  protected static final String EXIT = "Sair";
  protected static final String GOTO = "Pular para";
  protected static final String HELP = "Ajuda";
  protected static final String NEW = "Novo";
  protected static final String NEXT = "Próximo";
  protected static final String OPEN = "Abrir";
  protected static final String PAGENR = "Número do Slide?";
  protected static final String PREV = "Anteior";
  protected static final String SAVE = "Salvar";
  protected static final String VIEW = "Visualizar";

  // // protected static final String TESTFILE = "classpath:test.xml";
  // // protected static final String SAVEFILE = "classpath:dump.xml";
  protected static final String TESTFILE = "src/main/resources/test.json";
  protected static final String SAVEFILE = "src/main/resources";

  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Erro ao carregar";
  protected static final String SAVEERR = "Erro ao salvar";

  public SlideViewerComponent slideViewer;

  public MenuController(Frame frame, Presentation pres) {
    parent = frame;

    if (pres == null){
      presentation = new NullPresentation();
    } else {
      presentation = pres;
    }

    MenuItem menuItem;

    Menu fileMenu = new Menu(FILE);
    fileMenu.add(menuItem = mkMenuItem(OPEN));
    menuItem.addActionListener(actionEvent -> {
		  JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			FileNameExtensionFilter filter = new FileNameExtensionFilter("json, xml, html", "json", "xml", "html");

			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.addChoosableFileFilter(filter);

			int returnValue = jfc.showOpenDialog(parent);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = jfc.getSelectedFile();
				String path = selectedFile.getAbsolutePath();

        String extension = path.substring(path.lastIndexOf("."), path.length());
				
        presentation.clear();
        
        LoadManager manager = new LoadManager();
        // FileManager manager = new FileManager();
        //Accessor format = manager.setFormat(extension);
        AccessorLoad format = manager.setFormat(extension);
        
        try {
          format.loadFile(presentation, path);
          presentation.setSlideNumber(0);
        } catch (IOException e) {
          JOptionPane.showMessageDialog(parent, IOEX + e, LOADERR, JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
        } 
                
        parent.repaint();
        
			}
    });
    
    fileMenu.add(menuItem = mkMenuItem(NEW));
    
    menuItem.addActionListener(actionEvent -> {
        Presentation presen = new NullPresentation();
        
        presen.clear();
        
        parent.repaint();
      
    });
    
    fileMenu.add(menuItem = mkMenuItem(SAVE));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("json, html, xml", "json", "xml", "html");
        
        jfc.setDialogTitle("Save");;
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.addChoosableFileFilter(filter);
        
        int returnValue = jfc.showOpenDialog(parent);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = jfc.getSelectedFile();
          String path = selectedFile.getAbsolutePath();
          
          
          String extension = path.substring(path.lastIndexOf("."), path.length());

          SaveManager manager = new SaveManager();
          
          // FileManager manager = new FileManager();
 
          // Accessor format = manager.setFormat(extension);
          AccessorSave format = manager.setFormat(extension);

        try {
          format.saveFile(presentation, path);
        } catch (IOException exc) {
          JOptionPane.showMessageDialog(parent, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  });

    fileMenu.addSeparator();

    fileMenu.add(menuItem = mkMenuItem(EXIT));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
      }
    });

    add(fileMenu);

    Menu viewMenu = new Menu(VIEW);
    viewMenu.add(menuItem = mkMenuItem(NEXT));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.nextSlide();
      }
    });

    viewMenu.add(menuItem = mkMenuItem(PREV));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.prevSlide();
      }
    });

    viewMenu.add(menuItem = mkMenuItem(GOTO));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        if(pageNumber>0 && pageNumber <= presentation.getSize()) {
        	presentation.setSlideNumber(pageNumber - 1);
        	
        }
      }
    });

    add(viewMenu);

   //executado quando alguem clica
    Menu helpMenu = new Menu(HELP);
    helpMenu.add(menuItem = mkMenuItem(ABOUT));

    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        AboutBox.show(parent);
      }
    });

    setHelpMenu(helpMenu);
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
