package model.factories;

import model.accessor.*;

public class LoadManager {
    
    public static final AccessorLoad setFormat(String extension){
        AccessorLoad format;
        if (extension.equals(".json")){
            format = new JSONAccessorLoad();
        } else if (extension.equals(".html")){
            format = new HTMLAccessorLoad();
        } else{
            format = new XMLAccessorLoad();
        }
        return format;

    }
    
}
