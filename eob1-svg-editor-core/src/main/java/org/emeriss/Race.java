package org.emeriss;


public enum Race {
        
    HUMAN(0), ELF(2), HALF_ELF(4), DWARF(6), GNOME(8), HALFLING(10), UNDEFINED(32);
    
    private final int n;
    
    private Race (int n) {
        this.n = n;
    }
    
    public static Race valueOf(int n) {
        for (Race r : Race.values()) {
            if (r.n == n) {
                return r;
            }
        }   
        return null;
    }
    
    @Override
    public String toString() {
        return this.name().replace("_", " ").toLowerCase();
    }
    
}

