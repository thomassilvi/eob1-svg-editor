package org.emeriss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class Spells {
  
    public static final int MAX_LEVELS_COUNT = 5;
    public static final int MAX_SPELLS_BY_LEVEL = 8;
    
    protected HashMap<Integer,Spell> allSpells; // Id,Spell
    
    private static final Logger LOGGER = Logger.getLogger(Spells.class);
    
    public Spells() {
        allSpells = new HashMap<Integer,Spell>();
    }

    public void show() {
        for (Spell s : getSpells()) {
            LOGGER.info(s.toString());
        }
    }

    public void addSpell(Spell s) {
        allSpells.put(s.getId(),s);
    }
    
    public List<Spell> getSpells() {
        List<Spell> result = new ArrayList<Spell>();
        
        for (Spell s : allSpells.values()) {
            result.add(s);
        }
        Collections.sort(result);
        
        return result;
    }
    
    public List<Spell> getSpells(int level) {
        List<Spell> result = new ArrayList<Spell>();
        
        for (Spell s : allSpells.values()) {
            if (s.getLevel()==level) {
                result.add(s);
            }
        }
        Collections.sort(result);
        
        return result;
    }
    
    public Spell getSpellById(int id) {
        if (allSpells.containsKey(id)) {
            return allSpells.get(id);
        }
        return null;
    }
    
    public void setKnown(Spell s) {
        allSpells.get(s.getId()).setKnown(true);
    }
    
    public void incMemorizedCount(int spellId) {
        allSpells.get(spellId).incMemorizedCount();
    }
    
    public void incGainedCount(int spellId) {
        allSpells.get(spellId).incGainedCount();
    }

    public void setGainedCount(int spellId, int gainedCount) {
        allSpells.get(spellId).setGainedCount(gainedCount);
    }
}
