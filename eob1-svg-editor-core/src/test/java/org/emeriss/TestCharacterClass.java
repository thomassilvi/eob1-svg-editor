package org.emeriss;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCharacterClass extends TestCase {
    
    public TestCharacterClass(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestCharacterClass.class);
    }

    public void testCharacterClassFighter() {
        CharacterClass cc = CharacterClassFactory.getClass(0);
        assertFalse(cc.isMultiClass());
        CharacterSingleClass csc = (CharacterSingleClass) cc;
        assertTrue("fighter".equals(csc.getName()));
    }
    
    public void testCharacterClassRanger() {
        CharacterClass cc = CharacterClassFactory.getClass(1);
        assertFalse(cc.isMultiClass());
        CharacterSingleClass csc = (CharacterSingleClass) cc;
        assertTrue("ranger".equals(csc.getName()));
    }

    public void testCharacterClassPaladin() {
        CharacterClass cc = CharacterClassFactory.getClass(2);
        assertFalse(cc.isMultiClass());
        CharacterSingleClass csc = (CharacterSingleClass) cc;
        assertTrue("paladin".equals(csc.getName()));
    }

    public void testCharacterClassMage() {
        CharacterClass cc = CharacterClassFactory.getClass(3);
        assertFalse(cc.isMultiClass());
        CharacterSingleClass csc = (CharacterSingleClass) cc;
        assertTrue("mage".equals(csc.getName()));
    }

    public void testCharacterClassCleric() {
        CharacterClass cc = CharacterClassFactory.getClass(4);
        assertFalse(cc.isMultiClass());
        CharacterSingleClass csc = (CharacterSingleClass) cc;
        assertTrue("cleric".equals(csc.getName()));
    }
    
    public void testCharacterClassThief() {
        CharacterClass cc = CharacterClassFactory.getClass(5);
        assertFalse(cc.isMultiClass());
        CharacterSingleClass csc = (CharacterSingleClass) cc;
        assertTrue("thief".equals(csc.getName()));
    }
    
    public void testCharacterClassFighterCleric() {
        CharacterClass cc = CharacterClassFactory.getClass(6);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==2);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("fighter".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("cleric".equals(cc2.getName()));
    }

    public void testCharacterClassFighterThief() {
        CharacterClass cc = CharacterClassFactory.getClass(7);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==2);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("fighter".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("thief".equals(cc2.getName()));
    }    

    public void testCharacterClassFighterMage() {
        CharacterClass cc = CharacterClassFactory.getClass(8);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==2);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("fighter".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("mage".equals(cc2.getName()));
    }

    public void testCharacterClassFighterMageThief() {
        CharacterClass cc = CharacterClassFactory.getClass(9);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==3);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("fighter".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("mage".equals(cc2.getName()));
        CharacterSingleClass cc3 = cmc.getClassAt(2);
        assertFalse(cc3==null);
        assertTrue("thief".equals(cc3.getName()));
    }
    
    public void testCharacterClassThiefMage() {
        CharacterClass cc = CharacterClassFactory.getClass(10);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==2);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("thief".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("mage".equals(cc2.getName()));
    }

    public void testCharacterClassClericThief() {
        CharacterClass cc = CharacterClassFactory.getClass(11);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==2);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("cleric".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("thief".equals(cc2.getName()));
    }

    public void testCharacterClassFighterClericMage() {
        CharacterClass cc = CharacterClassFactory.getClass(12);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==3);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("fighter".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("cleric".equals(cc2.getName()));
        CharacterSingleClass cc3 = cmc.getClassAt(2);
        assertFalse(cc3==null);
        assertTrue("mage".equals(cc3.getName()));        
    }

    public void testCharacterClassRangerCleric() {
        CharacterClass cc = CharacterClassFactory.getClass(13);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==2);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("ranger".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("cleric".equals(cc2.getName()));
    }    

    public void testCharacterClassClericMage() {
        CharacterClass cc = CharacterClassFactory.getClass(14);
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc;
        assertTrue(cmc.getClassCount()==2);
        CharacterSingleClass cc1 = cmc.getClassAt(0);
        assertFalse(cc1==null);
        assertTrue("cleric".equals(cc1.getName()));
        CharacterSingleClass cc2 = cmc.getClassAt(1);
        assertFalse(cc2==null);
        assertTrue("mage".equals(cc2.getName()));
    }    

    public void testCharacterCompareWith() {
        CharacterClass cleric = CharacterClassFactory.getClass(4);
        assertTrue("cleric",cleric.compareWithClassName("cleric"));
        CharacterClass fighter = CharacterClassFactory.getClass(0);
        assertTrue("fighter",fighter.compareWithClassName("fighter"));
        assertFalse("fighter is not cleric",
                fighter.compareWithClassName(CharacterClassCleric.CLASS_NAME));
        CharacterMultiClass fighterCleric = CharacterClassFactory.getFighterCleric();
        assertTrue("fighter/cleric is fighter",
                fighterCleric.compareWithClassName("fighter"));
        assertTrue("fighter/cleric is cleric",
                fighterCleric.compareWithClassName("cleric"));
        assertFalse("fighter/cleric is not ranger",
                fighterCleric.compareWithClassName(CharacterClassRanger.CLASS_NAME));
    }
    
}

