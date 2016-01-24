package emeriss.org;

import java.awt.Panel;
import java.util.List;

import javax.swing.JTabbedPane;

import org.emeriss.Character;
import org.emeriss.CharacterClass;
import org.emeriss.CharacterClassMage;
import org.emeriss.CharacterClassTools;
import org.emeriss.CharacterUpdateException;
import org.emeriss.Spell;
import org.emeriss.SpellsTools;

@SuppressWarnings("serial")
public class CharacterSpellPanel extends Panel {

    protected SpellsPanel mageSpellPanel;
    protected JTabbedPane mainSpellsTabPanel;
    
    public CharacterSpellPanel() {
        super();
        
        mainSpellsTabPanel = new JTabbedPane();
        
        mageSpellPanel = new SpellsPanel();
        List<Spell> mageSpells = SpellsTools.getMageSpells(0).getSpells();
        mageSpellPanel.load(mageSpells);
        mainSpellsTabPanel.addTab("mage spells", mageSpellPanel);

        add(mainSpellsTabPanel);
    }
    
    public void updateWithCharacter(Character c) {
        CharacterClass cc = c.getCharacterClass();
        // mage spells
        if (cc.compareWithClassName(CharacterClassMage.CLASS_NAME)) {
            CharacterClassMage mageClass = (CharacterClassMage)
                CharacterClassTools.getSingleClassWithName(cc, 
                CharacterClassMage.CLASS_NAME);
            List<Spell> mageSpells = mageClass.getAllSpells();
            mageSpellPanel.load(mageSpells);
        }
        // cleric spells
        // paladin spells
    }
    
    public void updateCharacter(Character c) throws CharacterUpdateException {
        CharacterClass cc = c.getCharacterClass();
        // mage spells
        if (cc.compareWithClassName(CharacterClassMage.CLASS_NAME)) {
            CharacterClassMage mageClass = (CharacterClassMage)
                CharacterClassTools.getSingleClassWithName(cc, 
                CharacterClassMage.CLASS_NAME);            
            mageClass.setKnownSpells(mageSpellPanel.getSpellsFromUI());
        }        
    }

}
