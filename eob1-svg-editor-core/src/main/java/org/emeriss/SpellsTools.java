package org.emeriss;

import java.util.HashMap;
import java.util.List;

public abstract class SpellsTools {

    protected SpellsTools() {
    }
    
    protected static byte[] getInternalMemorizedAndGained(int spellsCount, Spells s, int level) {
        byte[] result = new byte[spellsCount];
        int i,j,n1,n2;
        byte codeTmp;
        List<Spell> ls = s.getSpells(level);
        
        i = 0;
                
        for (Spell spellTmp : ls) {
            n1 = spellTmp.getGainedCount();
            if (n1>0) {
                codeTmp = SaveGameTools.intToUnsignedByte(spellTmp.getId()); 
                for (j=0;j<n1;j++) {
                    result[i] = codeTmp;
                    i++;
                }
            } 
            n2 = spellTmp.getMemorizedCount() - n1;
            if (n2>0) {
                codeTmp = SaveGameTools.intToUnsignedByte(256 - spellTmp.getId());
                for (j=0;j<n2;j++) {
                    result[i] = codeTmp;
                    i++;
                }
            }
        }
        while (i<spellsCount) {
            result[i] = 0;
            i++;
        }
        
        return result;
    }

    protected static HashMap<Integer,Spell> initDefaultSpells(String[][] allSpells, boolean initKnown) {
        HashMap<Integer,Spell> result = new HashMap<Integer,Spell>();
        int levelTmp, i, idTmp;
        String nameTmp;
        Spell spellTmp;
        
        for (i=0;i<allSpells.length;i++) {
            idTmp = Integer.parseInt(allSpells[i][0].trim());
            levelTmp = Integer.parseInt(allSpells[i][1].trim());
            nameTmp = allSpells[i][2].trim();
            spellTmp = new Spell(idTmp,nameTmp,levelTmp, initKnown);
            result.put(idTmp,spellTmp);
        }
        
        return result;
    }    
    
}
