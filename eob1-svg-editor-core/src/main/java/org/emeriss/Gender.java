package org.emeriss;

public enum Gender {
    MALE(0), FEMALE(1), UNDEFINED(32);
    
    private final int n;
    
    private Gender (int n) {
        this.n = n;
    }

    public static Gender valueOf(int n) {
        
        for (Gender g : Gender.values()) {
            if (g.n == n) {
                return g;
            }
        }   
        return null;
    }    
    
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
    
}

