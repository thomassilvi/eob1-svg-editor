package org.emeriss;


public abstract class CharacterSingleClass extends CharacterClass {
    
    protected String name;
    protected int experience;
    protected byte level;
    
    public CharacterSingleClass(int value, String name) {
        super(value);
        this.name = name;
        this.experience = 0;
        this.level = 0;
    }

    @Override
    public boolean isMultiClass() {
        return false;
    }

    public int getExperience() {
        return experience;
    }
    
    public void setExperience(int value) {
        this.experience = value;
    }
    
    public byte getLevel() {
        return level;
    }
    
    public void setLevel(byte value) {
        this.level = value;
    }
    
    @Override
    public String toString () {
        return String.format("class:%1$8s xp:%2$7d lvl:%3$2d", name, experience, level);        
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean compareWithClassName(String s) {
        if (name==null) {
                return false;
        }
        return name.equals(s);
    }

}

