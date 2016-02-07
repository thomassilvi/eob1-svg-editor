package org.emeriss;

public class Spell implements Comparable<Spell> {

    protected String name;
    protected boolean known;
    protected int id;
    protected int level;
    protected int memorizedCount;
    protected int gainedCount;
    
    public Spell(int id, String name, int level, boolean isKnown) {
        this.id = id;
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
        return String.format("%1$20s(lvl:%2$1d) : %3$5s  %4$2d / %5$2d (id:%6$2d)", 
                name, level, strKnown, gainedCount, memorizedCount, id);
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
        
        if (o.getId() > this.id) {
            return -1;
        } else if (o.getId() < this.id) {
            return 1;
        }
        return 0;
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
        
        return this.id==s.getId();
    }
    
    @Override
    public int hashCode() {
        int result = 1;
        
        result = result + 31 * this.name.hashCode();
        result = result + 31 * this.level;
        
        return result;
    }
    
    public int getId() {
        return id;
    }
    
    public void incMemorizedCount() {
        this.memorizedCount++;
    }
    
    public void incGainedCount() {
        this.gainedCount++;
        this.memorizedCount++;
    }

    public int getMemorizedCount() {
        return memorizedCount;
    }

    public int getGainedCount() {
        return gainedCount;
    }

    public void setGainedCount(int gainedCount) {
        this.gainedCount = gainedCount;
    }

    public void setMemorizedCount(int memorizedCount) {
        this.memorizedCount = memorizedCount;
    }
    
    public void update(Spell source) {
        this.known = source.known;
        this.memorizedCount = source.memorizedCount;
        this.gainedCount = source.gainedCount;
    }
    
}
