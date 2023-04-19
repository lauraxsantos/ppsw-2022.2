package controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import model.Presentation;


public class JSONFormat implements IFileFormat{
    
    public Presentation load(String fileName) {
		
		try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
			Presentation presentation= gson.fromJson(reader, Presentation.class);
            reader.close();
			return presentation;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return null;
	}

	@Override
	public void save(Presentation presentation, String fileName) {
		Path path = Paths.get(fileName);

		try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
	        Gson gson = new GsonBuilder().create();
	        JsonElement json = gson.toJsonTree(presentation);      
	        gson.toJson(json, writer);
	        
		} catch (IOException e) {
			e.printStackTrace();
			
		}

	}


}