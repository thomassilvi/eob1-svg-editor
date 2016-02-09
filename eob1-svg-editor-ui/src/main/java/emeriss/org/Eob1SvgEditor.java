package emeriss.org;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.apache.log4j.Logger;
import org.emeriss.SaveGame;


public class Eob1SvgEditor {
    protected Frame mainFrame;
    protected SaveGame saveGame;
    protected Label statusBar;
    protected CharacterPanel[] characterPanels;
    
    private static final Logger LOGGER = Logger.getLogger(Eob1SvgEditor.class);

    
    public Eob1SvgEditor() {
        saveGame = new SaveGame();
        buildUI();
    }
    
    public void run() {
        mainFrame.setVisible(true);
    }
    
    protected void buildUI() {
        mainFrame = new Frame();
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setTitle("Eye of Beholder 1 - SaveGame Editor");
        mainFrame.addWindowListener(new CustomWindowListener());
        MenuBar mb = new MenuBar();
        mainFrame.setMenuBar(mb);
        
        Menu mf = new Menu("Files");
        mb.add(mf);
        MenuItem miOpen = new MenuItem("Load");
        miOpen.addActionListener(new ActionLoadListener());
        miOpen.setShortcut(new MenuShortcut(KeyEvent.VK_L));
        mf.add(miOpen);
        MenuItem miSave = new MenuItem("Save");
        miSave.addActionListener(new ActionSaveListener());        
        miSave.setShortcut(new MenuShortcut(KeyEvent.VK_S));
        mf.add(miSave);
        MenuItem miReload = new MenuItem("Reload");
        miReload.addActionListener(new ActionReloadListener());        
        miReload.setShortcut(new MenuShortcut(KeyEvent.VK_R));
        mf.add(miReload);
        mf.addSeparator();
        MenuItem miQuit = new MenuItem("Quit");
        miQuit.setShortcut(new MenuShortcut(KeyEvent.VK_Q));
        mf.add(miQuit);
        miQuit.addActionListener(new ActionQuitListener());
        //
        Menu mMisc = new Menu("Misc");
        mb.add(mMisc);
        MenuItem miCamp = new MenuItem("Camp");
        miCamp.addActionListener(new ActionCampListener());
        mMisc.add(miCamp);
        //
        Panel panelCenter = new Panel();
        panelCenter.setLayout(new FlowLayout());
        
        // characters panel
        
        characterPanels = new CharacterPanel[SaveGame.MAX_CHARACTERS];
        for (int i=0;i<SaveGame.MAX_ACTIVE_CHARACTERS;i++) {
            characterPanels[i] = new CharacterPanel();
            panelCenter.add(characterPanels[i]);
        }
        
        // status bar
        
        Panel panelStatusBar = new Panel();
        panelStatusBar.setLayout(new BorderLayout());
        statusBar = new Label();
        statusBar.setText("Welcome, please load a savegame");
        statusBar.setBackground(Color.LIGHT_GRAY);
        panelStatusBar.add(statusBar);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        mainFrame.add(panelCenter,gbc1);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        mainFrame.add(panelStatusBar,gbc2);
        
        mainFrame.pack();
        statusBar.setMinimumSize(panelStatusBar.getSize());
    }
    
    protected void updateStatusBar(String t) {
        statusBar.setText(t);
    }

    protected void updateStatusBarWithError(Exception e) {
        statusBar.setText("error: " + e.toString());
    }    
    
    protected void quit() {
        System.exit(0); // NOSONAR
    }
    
    public static void main(String[] args) {
        Eob1SvgEditor app = new Eob1SvgEditor();
        app.run();
    }

    public void updateUI() {
        byte cpi = 0;
        for (byte i=0;i<SaveGame.MAX_CHARACTERS;i++) {
            if (saveGame.getCharacter(i).isActive()) {
                characterPanels[cpi].updateWithCharacter(saveGame.getCharacter(i));
                characterPanels[cpi].active();
                cpi++;
            }
        }        
    }
    
    public boolean isSaveGameNotLoaded() {
        if (saveGame.isLoaded()) {
            return false;
        }
        updateStatusBar("You must load a savegame first.");
        return true;
    }
    
    // inner classes

    class CustomWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) { 
            // do nothing
        }

        @Override
        public void windowClosing(WindowEvent e) {
            quit();
        }

        @Override
        public void windowClosed(WindowEvent e) { 
            // do nothing            
        }

        @Override
        public void windowIconified(WindowEvent e) { 
            // do nothing            
        }

        @Override
        public void windowDeiconified(WindowEvent e) { 
            // do nothing            
        }

        @Override
        public void windowActivated(WindowEvent e) { 
            // do nothing            
        }

        @Override
        public void windowDeactivated(WindowEvent e) { 
            // do nothing            
        }
    }
 
    
    class ActionQuitListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            quit();
        }
    }

    class ActionLoadListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog fd = new FileDialog(mainFrame,"Select EOB savegame");
            fd.setMode(FileDialog.LOAD);
            fd.setFilenameFilter(new EobSavFileNameFilter());
            fd.setVisible(true);
            String fileName = fd.getFile();
            String path = fd.getDirectory();
            if ((path == null) || (fileName == null)) {
                updateStatusBar("savegame loading canceled");
                return;
            }
            try {
                saveGame.load(path+fileName);
                updateStatusBar("File loaded.");
                updateUI();
            }
            catch (Exception ex) {
                LOGGER.error(ex);
                updateStatusBarWithError(ex);
        }            
        }
    }

    class ActionSaveListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isSaveGameNotLoaded()) {
                return;
            }

            try {
                for (byte i=0;i<SaveGame.MAX_CHARACTERS;i++) {
                    if (saveGame.getCharacter(i).isActive()) {
                        characterPanels[i].updateCharacter(saveGame.getCharacter(i));
                    }
                }
                saveGame.save();
                updateStatusBar("File saved.");
            }
            catch (Exception ex) {
                LOGGER.error(ex);
                updateStatusBarWithError(ex);                
            }
                
        }
    }
    
    class ActionReloadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isSaveGameNotLoaded()) {
                return;
            }

            try {
                saveGame.reload();
                updateStatusBar("File reloaded");
                updateUI();                
            }
            catch (Exception ex) {
                LOGGER.error(ex);
                updateStatusBarWithError(ex);                
            }            
        }
        
    }
    
    class ActionCampListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isSaveGameNotLoaded()) {
                return;
            }

            saveGame.camp();
            updateUI();            
            updateStatusBar("Camp done.");
        }        
    }
    
}
