package org.emeriss;

import java.util.HashMap;
import java.util.List;

public class MageSpellsTools extends SpellsTools {

    private static final int MAX_LEVEL_COUNT = 5;
    
    private static final int[] MAX_SPELLS_BY_LEVEL = { 0, 6, 6, 6, 6, 3 };
    
    private static final String[][] SPELLS = {
            // Level 1
            { "1",  "1", "Armor"               }, // 0x73
            { "2",  "1", "Burning Hands"       },
            { "3",  "1", "Detect Magic"        },
            { "4",  "1", "Magic Missile"       }, 
            { "6",  "1", "Shield"              },
            { "7",  "1", "Shocking Grasp"      },
            // Level 2
            { "9",  "2", "Knock"               }, // 0x74
            { "10", "2", "Melf's Acid Arrow"   },
            { "11", "2", "Stinking Cloud"      },
            // Level 3            
            { "12", "3", "Dispel Magic"        },
            { "13", "3", "Fireball"            },
            { "14", "3", "Flame Arrow"         },
            { "15", "3", "Haste"               },
            { "16", "3", "Hold Person"         },
            { "17", "3", "Invisibility 10'"    }, // 0x75
            { "18", "3", "Lightning Bolt"      },
            { "19", "3", "Vampiric Touch"      },
            // Level 4
            { "20", "4", "Fear"                },
            { "21", "4", "Ice Storm"           },
            { "22", "4", "Stoneskin"           },            
            // Level 5
            { "23", "5", "Cloudkill"           },
            { "24", "5", "Cone of Cold"        },
            { "25", "5", "Hold Monster"        } // 0x76
            // end
    };
    
    private static final HashMap<Integer,Spell> DEFAULT_SPELLS = initDefaultSpells(SPELLS,false);

    private MageSpellsTools() {
        super();
    }
    
    public static String[][] getSpells() {
        return SPELLS;
    }

    public static int getMaxLevelsCount() {
        return MAX_LEVEL_COUNT;
    }
    
    public static int getMaxSpellsCountByLevel(int level) {
        return MAX_SPELLS_BY_LEVEL[level];
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
    
    public static int getSpellCode(Spells s) {
        int result;
        List<Spell> spellsTmp;

        result = 0;
        spellsTmp = s.getSpells();

        for (Spell spellTmp : spellsTmp) {
            if (spellTmp.isKnown()) {
                result = result + (1 << (spellTmp.getId() - 1));
            }
        }

        return result;
    }

    public static Spells getSpells(int code) {
        Spells result = new Spells();

        int levelTmp, i, codeTmp, idTmp;
        Spell spellTmp;
        boolean isKnownTmp;
        String nameTmp;
        
        for (i=0;i<SPELLS.length;i++) {
            idTmp = Integer.parseInt(SPELLS[i][0].trim());
            levelTmp = Integer.parseInt(SPELLS[i][1].trim());
            nameTmp = SPELLS[i][2].trim();
            codeTmp = 1 << (idTmp - 1);
            isKnownTmp = (code & codeTmp) == codeTmp;
            spellTmp = new Spell(idTmp,nameTmp,levelTmp, isKnownTmp);
            result.addSpell(spellTmp);
        }
        
        return result;
    }
    
    public static byte[] getMemorizedAndGained(Spells s, int level) {
        return getInternalMemorizedAndGained(MAX_SPELLS_BY_LEVEL[level],s,level);
    }
}
