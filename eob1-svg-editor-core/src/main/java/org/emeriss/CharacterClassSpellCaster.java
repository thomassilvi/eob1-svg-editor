package org.emeriss;

import java.util.ArrayList;
import java.util.List;

public class CharacterClassSpellCaster extends CharacterSingleClass {

    protected Spells spells;

    public CharacterClassSpellCaster(int value, String name) {
        super(value,name);
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

    public void updateSpells(List<Spell> sourceList) {
        for (Spell spellTmp : sourceList) {
            spells.getSpellById(spellTmp.getId()).update(spellTmp);
        }
    }    
    
}
