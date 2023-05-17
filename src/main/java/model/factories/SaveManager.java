package model.factories;

import model.accessor.*;

public class SaveManager {
    
    public static final AccessorSave setFormat(String extension){
        AccessorSave format;
        if (extension.equals(".json")){
            format = new JSONAccessorSave();
        } else if (extension.equals(".html")){
            format = new HTMLAccessorSave();
        } else{
            format = new XMLAccessorSave();
        }
        return format;

    }
}
