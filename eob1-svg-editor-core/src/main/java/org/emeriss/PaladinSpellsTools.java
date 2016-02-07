package org.emeriss;

import java.util.HashMap;

public class PaladinSpellsTools extends SpellsTools {

    private static final int[] MAX_SPELLS_BY_LEVEL = { 0, 6, 6 };
    private static final int MAX_LEVEL_COUNT = 2;
    private static final byte CODE_LAY_ON_HANDS_GAINED = 24;

    private static final String[][] SPELLS = {
            // Level 1
            { "2",  "1", "Cure Light Wounds"   },
            { "5",  "1", "Protect-Evil"  },            
            { "24", "1", "Lay on Hands"  },
            // Level 2
            { "6",  "2", "Aid"                 },
            { "7",  "2", "Flame Blade"         }, 
            { "8",  "2", "Hold Person"         }, 
            { "9",  "2", "Slow Poison"         }
            // end
    };
    
    private static final HashMap<Integer,Spell> DEFAULT_SPELLS = initDefaultSpells(SPELLS,true);

    private PaladinSpellsTools() {
    }

    public static int getMaxLevelsCount() {
        return MAX_LEVEL_COUNT;
    }

    public static int getMaxSpellsCountByLevel(int level) {
        return MAX_SPELLS_BY_LEVEL[level];
    }

    public static Spells getSpells() {
        Spells result = new Spells();
        
        int levelTmp, i, idTmp;
        Spell spellTmp;
        String nameTmp;
        
        for (i=0;i<SPELLS.length;i++) {
            idTmp = Integer.parseInt(SPELLS[i][0].trim());
            levelTmp = Integer.parseInt(SPELLS[i][1].trim());
            nameTmp = SPELLS[i][2].trim();
            spellTmp = new Spell(idTmp,nameTmp,levelTmp, true);
            result.addSpell(spellTmp);
        }
        
        return result;
    }    
    
    public static void updateSpellBook(Spells s, byte code) {
        int codeTmp;
        
        codeTmp = SaveGameTools.unsignedByteToInt(code);
        
        if (DEFAULT_SPELLS.containsKey(codeTmp)) {
            s.incGainedCount(codeTmp);
            return;
        }
        
        codeTmp = 256 - codeTmp;
        if (DEFAULT_SPELLS.containsKey(codeTmp)) {
            s.incMemorizedCount(codeTmp);            
        }        
    }
    
    public static byte[] getMemorizedAndGained(Spells s, int level) {
        byte[] result;

        result = getInternalMemorizedAndGained(MAX_SPELLS_BY_LEVEL[level],s,level);

        if ((level==1) && (result[0]!=CODE_LAY_ON_HANDS_GAINED) 
                && (result[1]!=CODE_LAY_ON_HANDS_GAINED) ) {
                result[2] = CODE_LAY_ON_HANDS_GAINED;
        }

        return result;
    }    
}
