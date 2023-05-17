package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.util.ResourceUtils;


public class BitmapItem extends SlideItem {

  public BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "Arquivo ";
  protected static final String NOTFOUND = " não encontrado";

  public BitmapItem(int level, String name) {
    super(level);

    imageName = name;

    try {
      bufferedImage = ImageIO.read(ResourceUtils.getFile(imageName).getAbsoluteFile());
    } catch (IOException e) {
      System.err.println(FILE + imageName + NOTFOUND);
    }

  }

  public BitmapItem() {
    this(0, null);
  }

  public String getName() {
    return imageName;
  }

  public String toString() {
    return "BitmapItem[" + getLevel() + "," + imageName + "]";
  }


}
