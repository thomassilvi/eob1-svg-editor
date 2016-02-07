package emeriss.org;

import java.awt.Panel;
import java.util.List;

import javax.swing.JTabbedPane;

import org.emeriss.Character;
import org.emeriss.CharacterClass;
import org.emeriss.CharacterClassCleric;
import org.emeriss.CharacterClassMage;
import org.emeriss.CharacterClassPaladin;
import org.emeriss.CharacterClassTools;
import org.emeriss.CharacterUpdateException;
import org.emeriss.ClericSpellsTools;
import org.emeriss.MageSpellsTools;
import org.emeriss.PaladinSpellsTools;
import org.emeriss.Spell;


@SuppressWarnings("serial")
public class CharacterSpellPanel extends Panel {

    
    
    protected SpellsPanel mageSpellPanel, clericSpellPanel, paladinSpellPanel;
    protected JTabbedPane mainSpellsTabPanel;
    
    private static final String LABEL_MAGE_SPELLS = "mage spells";
    private static final String LABEL_CLERIC_SPELLS = "cleric spells";
    private static final String LABEL_PALADIN_SPELLS = "paladin spells";
    
    public CharacterSpellPanel() {
        super();
        mainSpellsTabPanel = new JTabbedPane();
        mageSpellPanel = null;
        clericSpellPanel = null;
        paladinSpellPanel = null;
        add(mainSpellsTabPanel);
    }
    
    public void updateWithCharacter(Character c) {
        CharacterClass cc = c.getCharacterClass();
        updateWithMageCharacterClass(cc);
        updateWithClericCharacterClass(cc);
        updateWithPaladinCharacterClass(cc);
    }
    
    public void updateCharacter(Character c) throws CharacterUpdateException {
        CharacterClass cc = c.getCharacterClass();
        updateCharacterMageSpells(cc);
        updateCharacterClericSpells(cc);
        updateCharacterPaladinSpells(cc);
    }

    protected void updateWithMageCharacterClass(CharacterClass cc) {
        if (cc.compareWithClassName(CharacterClassMage.CLASS_NAME)) {
            CharacterClassMage mageClass = (CharacterClassMage)
                CharacterClassTools.getSingleClassWithName(cc, 
                CharacterClassMage.CLASS_NAME);
            showMageSpellsPanel();
            List<Spell> mageSpells = mageClass.getAllSpells();
            mageSpellPanel.load(mageSpells);
        } else {
            hideSpellsPanel(LABEL_MAGE_SPELLS);
        }
    }
    
    protected void updateWithClericCharacterClass(CharacterClass cc) {
        if (cc.compareWithClassName(CharacterClassCleric.CLASS_NAME)) {
            CharacterClassCleric clericClass = (CharacterClassCleric)
                CharacterClassTools.getSingleClassWithName(cc, 
                CharacterClassCleric.CLASS_NAME);
            showClericSpellsPanel();
            List<Spell> clericSpells = clericClass.getAllSpells();
            clericSpellPanel.load(clericSpells);
        } else {
            hideSpellsPanel(LABEL_CLERIC_SPELLS);
        }        
    }

    protected void updateWithPaladinCharacterClass(CharacterClass cc) {
        if (cc.compareWithClassName(CharacterClassPaladin.CLASS_NAME)) {
            CharacterClassPaladin paladinClass = (CharacterClassPaladin)
                CharacterClassTools.getSingleClassWithName(cc, 
                        CharacterClassPaladin.CLASS_NAME);
            showPaladinSpellsPanel();
            List<Spell> paladinSpells = paladinClass.getAllSpells();
            paladinSpellPanel.load(paladinSpells);
        } else {
            hideSpellsPanel(LABEL_PALADIN_SPELLS);
        }   
    }
    
    protected void showMageSpellsPanel() {
        if (mageSpellPanel==null) {
            mageSpellPanel = new SpellsPanel(true);
            List<Spell> mageSpells = MageSpellsTools.getSpells(0).getSpells();
            mageSpellPanel.load(mageSpells);
        }
        mainSpellsTabPanel.addTab(LABEL_MAGE_SPELLS, mageSpellPanel);        
    }
    
    protected void showClericSpellsPanel() {
        if (clericSpellPanel==null) {
            clericSpellPanel = new SpellsPanel(false);
            List<Spell> clericSpells = ClericSpellsTools.getSpells().getSpells();
            clericSpellPanel.load(clericSpells);
        }
        mainSpellsTabPanel.addTab(LABEL_CLERIC_SPELLS, clericSpellPanel);        
    }
    
    protected void showPaladinSpellsPanel() {
        if (paladinSpellPanel==null) {
            paladinSpellPanel = new SpellsPanel(false);
            List<Spell> paladinSpells = PaladinSpellsTools.getSpells().getSpells();
            paladinSpellPanel.load(paladinSpells);
        }
        mainSpellsTabPanel.addTab(LABEL_PALADIN_SPELLS, paladinSpellPanel);
    }
    
    protected void hideSpellsPanel(String spellsType) {
        int idx = mainSpellsTabPanel.indexOfTab(spellsType);
        if (idx!=-1) {
            mainSpellsTabPanel.remove(idx);
        }
    }
    
    protected void updateCharacterMageSpells(CharacterClass cc) {
        if (cc.compareWithClassName(CharacterClassMage.CLASS_NAME)) {
            CharacterClassMage mageClass = (CharacterClassMage)
                CharacterClassTools.getSingleClassWithName(cc, 
                CharacterClassMage.CLASS_NAME); 
            mageClass.updateSpells(mageSpellPanel.getSpellsFromUI());
        }
    }
    
    protected void updateCharacterClericSpells(CharacterClass cc) {
        if (cc.compareWithClassName(CharacterClassCleric.CLASS_NAME)) {
            CharacterClassCleric clericClass = (CharacterClassCleric)
                CharacterClassTools.getSingleClassWithName(cc, 
                CharacterClassCleric.CLASS_NAME);
            clericClass.updateSpells(clericSpellPanel.getSpellsFromUI());
        }
    }
    
    protected void updateCharacterPaladinSpells(CharacterClass cc) {
        if (cc.compareWithClassName(CharacterClassPaladin.CLASS_NAME)) {
            CharacterClassPaladin paladinClass = (CharacterClassPaladin)
                CharacterClassTools.getSingleClassWithName(cc, 
                        CharacterClassPaladin.CLASS_NAME);
            paladinClass.updateSpells(paladinSpellPanel.getSpellsFromUI());
        }          
    }
    
}
