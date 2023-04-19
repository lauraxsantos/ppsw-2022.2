package br.upe.ppsw.jabberpoint;

import java.io.IOException;
import javax.swing.JOptionPane;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.util.StringUtils;

import controller.FileManager;
import controller.IFileFormat;
import controller.JSONFormat;
import controller.PresentationController;
import model.Presentation;
import view.Style;

@SpringBootApplication
public class JabberPointApplication implements CommandLineRunner {

  protected static final String IOERR = "IO Error: ";
  protected static final String JABERR = "Jabberpoint Error ";
  protected static final String JABVERSION = "Jabberpoint 1.6 -";


  public Presentation presentation;
  private IFileFormat fileformat = new JSONFormat();

  public static void main(String[] argv) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(JabberPointApplication.class);
    builder.headless(false);
    builder.web(WebApplicationType.NONE);
    builder.run(argv);
  }

  public final IFileFormat getFileManager() {
		return fileformat;
	}

  @Override
  public void run(String... args) throws Exception {
    Style.createStyles();

    String file = args == null || args.length == 0 ? null : args[0];

    if(StringUtils.hasLength(file)){
      FileManager manager = new FileManager();
      this.presentation = manager.load(file);

    }else{
      this.presentation = new Presentation();
    }

    try {
      PresentationController presC = new PresentationController();
      presC.load();

    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
    }
  }

}
