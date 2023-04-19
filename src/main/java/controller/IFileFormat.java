package controller;

import model.Presentation;

public interface IFileFormat {
    
    Presentation load(String fileName);
    
    void save(Presentation presentation, String fileName);
}
