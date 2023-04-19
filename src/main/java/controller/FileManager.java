package controller;

import java.util.HashMap;
import org.apache.commons.io.FilenameUtils;

import model.Presentation;

public class FileManager{

    private  HashMap<String, IFileFormat> tipos = new HashMap<String, IFileFormat>();

    public FileManager() {

        // XMLAccessor xmlAccessor = new XMLAccessor();
        // templates.put("xml", xmlAccessor);

		JSONFormat jsonFormat = new JSONFormat();
		tipos.put("json", jsonFormat);
	}

    public Presentation load(String filePath) {
        try{
            String extension = FilenameUtils.getExtension(filePath);
            return getTipos(extension).load(filePath);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
	}

	public void save(Presentation presentation, String filePath) {

        try {
            String extension = FilenameUtils.getExtension(filePath);
            getTipos(extension).save(presentation, filePath);            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private IFileFormat getTipos(String extension) {
		return tipos.get(extension);
	}




}