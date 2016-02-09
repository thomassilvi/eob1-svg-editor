package org.emeriss;


public abstract class CharacterClass {

    protected static int code;

    public CharacterClass(int value) {
        code = value;
    }
    
    public boolean isMultiClass() {
        return false;
    }
    
    @Override
    public String toString() {
        return "";
    }
    
    public int getCode() {
        return code;
    }
    
    public String getName () {
        return "";
    }
    
    public boolean compareWithClassName(String s) {
        if (s==null) {
            return false;
        }
        return false;
    }

    public void camp() {
    }
    
}

