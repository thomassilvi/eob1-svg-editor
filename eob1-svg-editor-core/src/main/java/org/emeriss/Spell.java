package org.emeriss;

public class Spell implements Comparable<Spell> {

    protected String name;
    protected boolean known;
    protected int level;
    protected int memorizedCount;
    protected int gainedCount;
    
    public Spell(String name, int level, boolean isKnown) {
        this.name = name;
        this.level = level;
        this.known = isKnown;
        this.memorizedCount = 0;
        this.gainedCount = 0;
    }
    
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        String strKnown;
        if (known) {
            strKnown = "known  ";
        } else {
            strKnown = "unknown";
        }
        return String.format("%1$20s(lvl:%2$1d) : %3$5s  %4$2d / %5$2d", 
                name, level, strKnown, gainedCount, memorizedCount);
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    @Override
    public int compareTo(Spell o) {
        if (o.getLevel() > this.level) {
            return -1;
        } else if (o.getLevel() < this.level) {
            return 1;
        }        
        
        return this.name.compareTo(o.getName());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spell s = (Spell) o;
        if (this.level!=s.getLevel()) {
            return false;
        }
        return this.name.equals(s.getName());
    }
    
    @Override
    public int hashCode() {
        int result = 1;
        
        result = result + 31 * this.name.hashCode();
        result = result + 31 * this.level;
        
        return result;
    }
}
