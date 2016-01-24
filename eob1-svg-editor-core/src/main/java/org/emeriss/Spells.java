package org.emeriss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

public class Spells {
  
    public static final int MAX_LEVELS_COUNT = 5;
    public static final int MAX_SPELLS_BY_LEVEL = 8;
    
    protected List<Spell> allSpells;
    
    private static final Logger LOGGER = Logger.getLogger(Spells.class);
    
    public Spells() {
        allSpells = new ArrayList<Spell>();
    }

    public void show() {
        for (Spell s : allSpells) {
            LOGGER.info(s.toString());
        }
    }

    public void addSpell(Spell s) {
        allSpells.add(s);
    }
    
    public void sort() {
        Collections.sort(allSpells);
    }
    
    public List<Spell> getSpells() {
        return allSpells;
    }
    
    public void setKnown(Spell s) {
        for (Spell spellTmp : allSpells) {
            if (spellTmp.getName().equals(s.getName())) {
                spellTmp.setKnown(true);
            }
        }
    }
}
