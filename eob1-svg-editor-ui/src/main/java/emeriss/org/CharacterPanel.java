package emeriss.org;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JTabbedPane;

import org.emeriss.Character;
import org.emeriss.CharacterClassTools;
import org.emeriss.CharacterUpdateException;


@SuppressWarnings("serial")
public class CharacterPanel extends Panel {
    protected byte characterId;
    protected TextField title;
    
    protected Panel panelCharacterStats;
    protected CharacterInfoPanel infoPanel;
    protected CharacterSpellPanel spellPanel;
    protected JTabbedPane mainTabbedPane;
    
    private static final String LABEL_INFO = "Info";
    private static final String LABEL_SPELLS = "Spells";
    
    public CharacterPanel() {
        super();
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);
        title = new TextField("",Character.NAME_MAX_LENGTH);
        title.setEditable(false);
        infoPanel = new CharacterInfoPanel();
        spellPanel = new CharacterSpellPanel(); 
        mainTabbedPane = new JTabbedPane();        
        mainTabbedPane.add(LABEL_INFO, infoPanel);
        mainTabbedPane.add(LABEL_SPELLS, spellPanel);
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

    public void updateSpellsWithCharacter(Character c) {
        int idx = mainTabbedPane.indexOfTab(LABEL_SPELLS);
        if (CharacterClassTools.isMage(c)) {
            if (idx==-1) {
                if (spellPanel==null) {
                    spellPanel = new CharacterSpellPanel();
                }
                mainTabbedPane.add(LABEL_SPELLS, spellPanel);  
                spellPanel.updateWithCharacter(c);
            }
            spellPanel.updateWithCharacter(c);
        } else {
            if (idx==-1) {
                return;
            } else {
                mainTabbedPane.remove(idx);        
            }
        }
    }

    public void updateCharacterSpells(Character c) throws CharacterUpdateException {
        int idx = mainTabbedPane.indexOfTab(LABEL_SPELLS);
        if (idx==-1) {
                return;
        }
        spellPanel.updateCharacter(c);
    }
    
    public void updateWithCharacter(Character c) {
        setTitle(c.getName());
        setCharacterId(c.getId());
        infoPanel.updateWithCharacter(c);
        updateSpellsWithCharacter(c);
    }

    public void updateCharacter(Character c) throws CharacterUpdateException {
        infoPanel.updateCharacter(c);
        updateCharacterSpells(c);
    }
    
}
