package controller;

import java.util.HashMap;
import org.apache.commons.io.FilenameUtils;

import model.Presentation;

public class FileManager{

    private static HashMap<String, IFileFormat> templates = new HashMap<String, IFileFormat>();

    public FileManager() {

        // XMLAccessor xmlAccessor = new XMLAccessor();
        // templates.put("xml", xmlAccessor);

		JSONFormat jsonFormat = new JSONFormat();
		templates.put("json", jsonFormat);
	}

    public Presentation load(String filePath) {
        try{
            String extension = FilenameUtils.getExtension(filePath);
            return getTemplate(extension).load(filePath);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
	}

	public void save(Presentation presentation, String filePath) {

        try {
            String extension = FilenameUtils.getExtension(filePath);
            getTemplate(extension).save(presentation, filePath);            
        } catch (Exception e) {
            // TODO: handle exception
        }
	}

	private static IFileFormat getTemplate(String extension) {
		return templates.get(extension);
	}




}