package org.emeriss;

import java.util.ArrayList;
import java.util.List;

public class CharacterClassMage extends CharacterSingleClass {

    public static final String CLASS_NAME = "mage";
    
    protected Spells spells;
    
    public CharacterClassMage(int value) {
        super(value,CLASS_NAME);
        spells = new Spells();
    }

    public List<Spell>  getAllSpells() {
        if (spells==null) {
            return new ArrayList<Spell>();
        }
        return spells.getSpells();
    }
    
    public Spells getSpells() {
        return this.spells;
    }

    public void setSpells(Spells spells) {
        this.spells = spells;
    }

    public void setKnownSpells(List<Spell> sourceList) {
        for (Spell spellTmp : sourceList) {
            if (spellTmp.isKnown()) {
                spells.setKnown(spellTmp);    
            }
        }
    }    

}
