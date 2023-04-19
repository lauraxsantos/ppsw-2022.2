package controller;

import java.io.IOException;

import model.Presentation;


//modelo a ser seguido por determinado tipo

public interface Accessor {

    void loadFile(Presentation presentation, String fileName) throws IOException;

     void saveFile(Presentation presentation, String fileName) throws IOException;

}
