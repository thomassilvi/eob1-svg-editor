package org.emeriss;

import java.util.HashMap;


public class ClericSpellsTools extends SpellsTools {

    private static final int[] MAX_SPELLS_BY_LEVEL = { 0, 6, 6, 6, 6, 3 };
    private static final int MAX_LEVEL_COUNT = 5;

    private static final String[][] SPELLS = {
            // Level 1
            { "1",  "1", "Bless"               },
            { "2",  "1", "Cure Light Wounds"   },
            { "3",  "1", "Cause Light Wounds"  },
            { "4",  "1", "Detect Magic"  },
            { "5",  "1", "Protect-Evil"  },
            // Level 2
            { "6",  "2", "Aid"                 },
            { "7",  "2", "Flame Blade"         }, 
            { "8",  "2", "Hold Person"         }, 
            { "9",  "2", "Slow Poison"         },
            // Level 3
            { "10",  "3", "Create Food"         },
            { "11",  "3", "Dispel Magic"        }, 
            { "12",  "3", "Magic Vestment"      },
            { "13",  "3", "Prayer"              },
            { "14",  "3", "Remove Paralysis"    },
            // Level 4
            { "15",  "4", "Cure Serious"      },            
            { "16",  "4", "Cause Serious"      },
            { "17",  "4", "Neutral-Poison"     },
            { "18",  "4", "Protect-Evil 10'"   },
            // Level 5            
            { "20",  "5", "Cure Critical"      },
            { "21",  "5", "Cause Critical"     },
            { "22",  "5", "Flame Strike"       },
            { "23",  "5", "Raise Dead"         }
            // end
    };
    
    private static final HashMap<Integer,Spell> DEFAULT_SPELLS = initDefaultSpells(SPELLS,true);
    
    private ClericSpellsTools() {
        super();
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
        return getInternalMemorizedAndGained(MAX_SPELLS_BY_LEVEL[level],s,level);
    }
}
