package org.emeriss;

public enum CharacterClass {

    FIGHTER(0), RANGER(1), PALADIN(2), MAGE(3), CLERIC(4), THIEF(5),
    FIGHTER_CLERIC(6), FIGHTER_THIEF(7), FIGHTER_MAGE(8), FIGHTER_MAGE_THIEF(9), THIEF_MAGE(10),
    CLERIC_THIEF(11), FIGHTER_CLERIC_MAGE(12), RANGER_CLERIC(13), CLERIC_MAGE(14),
    UNDEFINED(32);
        
    private final int n;
        
    private CharacterClass (int n) {
        this.n = n;
    }

    public static CharacterClass valueOf(int n) {
            
        for (CharacterClass c : CharacterClass.values()) {
            if (c.n == n) {
                return c;
            }
        }   
        return null;
    }        
    
    @Override
    public String toString() {
        return this.name().replace("_", "/").toLowerCase();
    }
    
}

