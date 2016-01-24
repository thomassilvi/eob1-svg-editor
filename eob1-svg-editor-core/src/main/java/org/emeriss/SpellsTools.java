package org.emeriss;

import java.util.HashMap;
import java.util.List;

public class SpellsTools {

    private static final String[][] MAGE_SPELLS = {
            // spell level, spell name, code
            // Level 1
            { "1", "Armor",             "1" }, // 0x73
            { "1", "Burning Hands",     "2" },
            { "1", "Detect Magic",      "4" },
            { "1", "Magic Missile",     "8"}, 
            { "1", "Shield",            "32" },
            { "1", "Shocking Grasp",    "64" },
            // Level 2
            { "2", "Knock",             "256" }, // 0x74
            { "2", "Melf's Acid Arrow", "512" },
            { "2", "Stinking Cloud",    "1024" },
            // Level 3            
            { "3", "Dispel Magic",      "2048" },
            { "3", "Fireball",          "4096" },
            { "3", "Flame Arrow",       "8192" },
            { "3", "Haste",             "16384" },
            { "3", "Hold Person",       "32768" },
            { "3", "Invisibility 10'",  "65536" }, // 0x75
            { "3", "Lightning Bolt",    "131072" },
            { "3", "Vampiric Touch",    "262144" },
            // Level 4
            { "4", "Fear",              "524288" },
            { "4", "Ice Storm",         "1048576" },
            { "4", "Stoneskin",         "2097152" },            
            // Level 5
            { "5", "Cloudkill",          "4194304" },
            { "5", "Cone of Cold",       "8388608" },
            { "5", "Hold Monster",       "16777216" } // 0x76
            // end
        
    };
            
    
    private SpellsTools() {
    }
    
    public static Spells getMageSpells(int code) {
        Spells result = new Spells();
        int levelTmp, i, codeTmp;
        Spell spellTmp;
        boolean isKnownTmp;
        
        for (i=0;i<MAGE_SPELLS.length;i++) {
            levelTmp = Integer.parseInt(MAGE_SPELLS[i][0].trim());
            codeTmp = Integer.parseInt(MAGE_SPELLS[i][2].trim());
            isKnownTmp = (code & codeTmp) == codeTmp;
            spellTmp = new Spell(MAGE_SPELLS[i][1].trim(),levelTmp, isKnownTmp);
            result.addSpell(spellTmp);
        }
        result.sort();
        return result;
    }
    
    public static int getMageSpellCode(Spells s) {
        int result, i, codeTmp;
        List<Spell> spellsTmp;
        
        HashMap<String,Integer> tmpHashMap = new HashMap<String,Integer>();
        for (i=0;i<MAGE_SPELLS.length;i++) {
            codeTmp = Integer.parseInt(MAGE_SPELLS[i][2].trim());
            tmpHashMap.put(MAGE_SPELLS[i][1].trim(), codeTmp);
        }
        
        spellsTmp = s.getSpells();
        result = 0;        
        for (Spell spellTmp : spellsTmp) {
            if ((tmpHashMap.containsKey(spellTmp.getName())) && (spellTmp.isKnown())) {
                codeTmp = tmpHashMap.get(spellTmp.getName());
                result = result + codeTmp;
            }
        }
        
        return result;
    }
    
}
