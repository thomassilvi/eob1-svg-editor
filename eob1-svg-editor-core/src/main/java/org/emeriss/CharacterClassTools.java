package org.emeriss;

public class CharacterClassTools {

    private CharacterClassTools() {
    }
    
    public static CharacterSingleClass getSingleClassWithName(CharacterClass c, String s) {
        CharacterSingleClass result = null;
        
        if (!c.compareWithClassName(s)) {
            return result;
        }        
        
        if (c.isMultiClass()) {
            CharacterMultiClass cmc = (CharacterMultiClass) c;
            result = cmc.getClassWithName(s);
        } else {
            result = (CharacterSingleClass) c;
        }        
        
        return result;
    }
    

}
