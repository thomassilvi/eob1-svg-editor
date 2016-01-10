package org.emeriss;

public enum Alignment {
     
    LAWFUL_GOOD(0), NEUTRAL_GOOD(1), CHAOTIC_GOOD(2),
    LAWFUL_NEUTRAL(3), TRUE_NEUTRAL(4), CHAOTIC_NEUTRAL(5),
    LAWFUL_EVIL(6), NEUTRAL_EVIL(7), CHAOTIC_EVIL(8), UNDEFINED(32);
    
    private final int n;
    
    private Alignment (int n) {
        this.n = n;
    }

    public static Alignment valueOf(int n) {
        
        for (Alignment a : Alignment.values()) {
            if (a.n == n) {
                return a;
            }
        }   
        return null;
    }        
    
    @Override
    public String toString() {
        return this.name().replace("_", " ").toLowerCase();
    }
}

