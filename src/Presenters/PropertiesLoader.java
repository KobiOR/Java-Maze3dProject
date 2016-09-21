package Presenters;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static mazeGenerators.Maze3dGenerator.ANSI_BOLD;
import static mazeGenerators.Maze3dGenerator.ANSI_RED;
import static mazeGenerators.Maze3dGenerator.ANSI_RESET;

/**
 * Load the properties from the xml file "properties" at the start of the program
 * @author Tomer, Gilad
 *
 */
public class PropertiesLoader {
    private static PropertiesLoader instance;
    private Properties properties;

    /**
     * Get the properties list
     * @return
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * CTOR
     */
    private PropertiesLoader()
    {
        try {
            XMLDecoder decoder = new XMLDecoder(new FileInputStream("properties.xml"));
            properties = (Properties)decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED+ANSI_BOLD+"Not found properties.xml file"+ANSI_RESET);
        }
    }

    /**
     * Get the instance of the properties loader
     * @return
     */
    public static PropertiesLoader getInstance() {
        if (instance == null)
            instance = new PropertiesLoader();
        return instance;
    }


}