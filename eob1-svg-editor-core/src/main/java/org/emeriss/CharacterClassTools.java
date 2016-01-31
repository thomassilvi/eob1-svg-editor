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
    
    public static boolean isMage(Character c) {
        return c.getCharacterClass().compareWithClassName(CharacterClassMage.CLASS_NAME);
    }

    public static boolean isCleric(Character c) {
        return c.getCharacterClass().compareWithClassName(CharacterClassCleric.CLASS_NAME);
    }
    
    public static boolean isPaladin(Character c) {
        return c.getCharacterClass().compareWithClassName(CharacterClassPaladin.CLASS_NAME);
    }
    
    public static boolean isSpellCaster(Character c) {
        if (isMage(c)) {
            return true;
        }
        if (isCleric(c)) {
            return true;
        }
        if (isPaladin(c)) {
            return true;
        }        
        return false;
    }

}
