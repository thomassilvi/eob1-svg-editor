package org.emeriss;


public class CharacterMultiClass extends CharacterClass {

    public static final byte MAX_CLASSES = 3;    
    protected CharacterSingleClass[] classes;    
    
    public CharacterMultiClass(int value, CharacterSingleClass a, CharacterSingleClass b, CharacterSingleClass c) {
        super(value);
        classes = new CharacterSingleClass[MAX_CLASSES];
        classes[0] = a;
        classes[1] = b;
        classes[2] = c;
    }

    @Override
    public boolean isMultiClass() {
        return true;
    }    
    
    public CharacterSingleClass getClassAt(int index) {
        if ((index>=0) && (index<MAX_CLASSES)) {
            return classes[index];
        } else {
            return null;
        }
    }
    
    @Override
    public String toString() {
        String result = "";
        boolean bPrev = false;
        
        for (byte i=0;i<MAX_CLASSES;i++) {
            if (classes[i] != null) {
                if (bPrev) {
                    result = result + " / ";
                } else {
                    bPrev = true;
                }
                result = result + classes[i].toString();
            }
        }
        result = result + "\n";
        return result;
    }
    
    
    @Override
    public String getName () {
        String result = "";
        boolean bPrev = false;
        
        for (byte i=0;i<MAX_CLASSES;i++) {
            if (classes[i] != null) {
                if (bPrev) {
                    result = result + " / ";
                } else {
                    bPrev = true;
                }
                result = result + classes[i].getName();
            }
        }
        result = result + "\n";
        return result;
    }
    
    public byte getClassCount() {
        byte result = 0;
        for (byte i=0;i<MAX_CLASSES;i++) {
            if (classes[i] != null) {
                result++;
            }
        }
        return result;
    }
    
    @Override
    public boolean compareWithClassName(String s) {
        for (byte i=0;i<MAX_CLASSES;i++) {
            if ((classes[i] != null) && (classes[i].compareWithClassName(s))) {
                return true;
            }
        }
        return false;
    }    

    public CharacterSingleClass getClassWithName(String s) {
        CharacterSingleClass result;

        result = null;
        for (byte i=0;i<MAX_CLASSES;i++) {
            if ((classes[i] != null) && (classes[i].compareWithClassName(s))) {
                result = classes[i];
            }
        }
        
        return result;
    }

}
