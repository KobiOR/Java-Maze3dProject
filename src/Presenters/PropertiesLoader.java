package Presenters;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static mazeGenerators.Maze3dGenerator.*;



public class PropertiesLoader {
    private static PropertiesLoader instance;
    private Properties properties;


    public Properties getProperties() {
        return properties;
    }
    private PropertiesLoader(){
        try {
            XMLDecoder decoder = new XMLDecoder(new FileInputStream("properties.xml"));
            properties = (Properties)decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED+ANSI_BOLD+"Not found properties.xml file"+ANSI_RESET);
        }
    }
    public static PropertiesLoader getInstance() {
        if (instance == null)
            instance = new PropertiesLoader();
        return instance;
    }


}