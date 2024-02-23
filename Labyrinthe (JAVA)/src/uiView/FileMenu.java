package uiView;

import javax.swing.*;

/**
 * The FileMenu class extends the JMenu class to create a menu for file settings
 * in a graphical user interface. The class contains three menu items: Quit,
 * Import, and Save.
 */
public class FileMenu extends JMenu {

    private static final long serialVersionUID = 1L;

    
    // Menu items     
                                                                                                                                                                      @SuppressWarnings("unused")
    private final QuitMenuItem quitMenuItem;                                                                                                                                       @SuppressWarnings("unused") 
    private final ImportMenuItem importMenuItem;                                                                                                             @SuppressWarnings("unused") 
    private final SaveMenuItem saveMenuItem;
    

    /**
     * Constructs a FileMenu object with the specified DrawingApp object.
     *
     * @param drawingApp the DrawingApp object associated with the menu
     */
    public FileMenu(DrawingApp drawingApp) {
        super("File settings"); // Text of the menu

        // Create and add menu items
        add(quitMenuItem = new QuitMenuItem(drawingApp));
        add(importMenuItem = new ImportMenuItem(drawingApp));
        add(saveMenuItem = new SaveMenuItem(drawingApp));
    }

}