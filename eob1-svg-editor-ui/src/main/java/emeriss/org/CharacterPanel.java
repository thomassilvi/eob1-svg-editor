package emeriss.org;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JTabbedPane;

import org.emeriss.Character;
import org.emeriss.CharacterUpdateException;


@SuppressWarnings("serial")
public class CharacterPanel extends Panel {
    protected byte characterId;
    protected TextField title;
    
    protected Panel panelCharacterStats;
    protected CharacterInfoPanel infoPanel;
    protected CharacterSpellPanel spellPanel;
    
    public CharacterPanel() {
        super();
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);
        
        title = new TextField("",Character.NAME_MAX_LENGTH);
        title.setEditable(false);
 
        infoPanel = new CharacterInfoPanel();
        spellPanel = new CharacterSpellPanel(); 

        JTabbedPane mainTabbedPane = new JTabbedPane();        
        mainTabbedPane.add("Info", infoPanel);
        mainTabbedPane.add("Spell", spellPanel);
        
        add(title,GridBagTools.getGridBagConstraint(0,0));
        add(mainTabbedPane,GridBagTools.getGridBagConstraint(0,1));
    }

    public void setTitle(String s) {
        title.setText(s);
    }
    
    public void active() {
        infoPanel.active();
    }

    public byte getCharacterId() {
        return characterId;
    }

    public void setCharacterId(byte characterId) {
        this.characterId = characterId;
    }

    public void updateWithCharacter(Character c) {
        setTitle(c.getName());
        setCharacterId(c.getId());
        infoPanel.updateWithCharacter(c);
        spellPanel.updateWithCharacter(c);
    }

    public void updateCharacter(Character c) throws CharacterUpdateException {
        infoPanel.updateCharacter(c);
        spellPanel.updateCharacter(c);
    }
    
}
