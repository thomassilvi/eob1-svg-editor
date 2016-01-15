package org.emeriss;


public class CharacterClassFactory {

    
    private CharacterClassFactory() {
    }
    
    
    public static CharacterClass getSingleClass(int code) {
        CharacterClass result;
        
        switch(code) {
            case 0 : 
                result = new CharacterClassFighter(code);
                break;
            case 1 :
                result = new CharacterClassRanger(code);
                break;
            case 2 :
                result = new CharacterClassPaladin(code);
                break;
            case 3 :
                result = new CharacterClassMage(code);
                break;
            case 4 :
                result = new CharacterClassCleric(code);
                break;
            case 5 :
                result = new CharacterClassThief(code);
                break;
            default :
                result = new CharacterClassUndefined(code);                
        }
        return result;
    }
    
    public static CharacterClass getMultiClass2 (int code) {
        CharacterClass result;
        
        switch(code) {
            case 6 :
                result = getFighterCleric();
                break;
            case 7 :
                result = getFighterThief();
                break;
            case 8 :
                result = getFighterMage();
                break;
            case 10 :
                result = getThiefMage();
                break;
            case 11 :
                result = getClericThief();
                break;
            case 13 :
                result = getRangerCleric();
                break;
            case 14 :
                result = getClericMage();
                break;
            default :
                result = new CharacterClassUndefined(code);
        }
        
        return result;
    }
    
    public static CharacterClass getClass(int code) {
        CharacterClass result;
        
        if ((code>=0) && (code<=5)) {
            return getSingleClass(code);
        }

        switch(code) {
            case 9 :
                result = getFighterMageThief();
                break;
            case 12 :
                result = getFighterClericMage();
                break;
            default :
                result = getMultiClass2(code);
        }
    
        return result;
    }
    
    public static CharacterMultiClass getFighterCleric() {
        // FIGHTER_CLERIC
        CharacterSingleClass c1,c2;
        c1 = (CharacterSingleClass) getClass(0);
        c2 = (CharacterSingleClass) getClass(4);
        return new CharacterMultiClass(6,c1,c2,null);
    }
    
    public static CharacterMultiClass getFighterThief() {
        CharacterSingleClass c1,c2;
        c1 = (CharacterSingleClass) getClass(0);
        c2 = (CharacterSingleClass) getClass(5);
        return new CharacterMultiClass(7,c1,c2,null);
    }

    public static CharacterMultiClass getFighterMage() {
        CharacterSingleClass c1,c2;
        c1 = (CharacterSingleClass) getClass(0);
        c2 = (CharacterSingleClass) getClass(3);
        return new CharacterMultiClass(8,c1,c2,null);
    }

    public static CharacterMultiClass getFighterMageThief() {
        CharacterSingleClass c1,c2,c3;
        c1 = (CharacterSingleClass) getClass(0);
        c2 = (CharacterSingleClass) getClass(3);
        c3 = (CharacterSingleClass) getClass(5);
        return new CharacterMultiClass(9,c1,c2,c3);
    }
    
    public static CharacterMultiClass getThiefMage() {
        CharacterSingleClass c1,c2;
        c1 = (CharacterSingleClass) getClass(5);
        c2 = (CharacterSingleClass) getClass(3);
        return new CharacterMultiClass(10,c1,c2,null);
    }

    public static CharacterMultiClass getClericThief() {
        CharacterSingleClass c1,c2;
        c1 = (CharacterSingleClass) getClass(4);
        c2 = (CharacterSingleClass) getClass(5);
        return new CharacterMultiClass(11,c1,c2,null);
    }
    
    public static CharacterMultiClass getFighterClericMage() {
        CharacterSingleClass c1,c2,c3;
        c1 = (CharacterSingleClass) getClass(0);
        c2 = (CharacterSingleClass) getClass(4);
        c3 = (CharacterSingleClass) getClass(3);
        return new CharacterMultiClass(12,c1,c2,c3);
    }
    
    public static CharacterMultiClass getRangerCleric() {
        CharacterSingleClass c1,c2;
        c1 = (CharacterSingleClass) getClass(1);
        c2 = (CharacterSingleClass) getClass(4);
        return new CharacterMultiClass(13,c1,c2,null);
    }
    
    public static CharacterMultiClass getClericMage() {
        CharacterSingleClass c1,c2;
        c1 = (CharacterSingleClass) getClass(4);
        c2 = (CharacterSingleClass) getClass(3);
        return new CharacterMultiClass(14,c1,c2,null);
    }    
}
