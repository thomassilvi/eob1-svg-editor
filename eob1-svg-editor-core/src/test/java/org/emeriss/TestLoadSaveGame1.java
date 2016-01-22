package org.emeriss;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class TestLoadSaveGame1 extends TestCase {
    
    private SaveGame s;
    private static final Logger LOGGER = Logger.getLogger(TestLoadSaveGame1.class);


    public TestLoadSaveGame1(String testName) {
        super(testName);
        s = new SaveGame();
        
        try {
            Path p = Paths.get("");
            String fileName = p.toAbsolutePath().toString() + "/src/test/resources/test1.sav";
            s.load(fileName);
        }
        catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    public static Test suite() {
        return new TestSuite( TestLoadSaveGame1.class );
    }

    public void testCharacterZero() {
        Character c = s.getCharacter(0);
        assertTrue("Character is active",c.isActive());
        assertEquals("Name","LECHUCK",c.getName());
        assertEquals("STR",c.getStrength(),18);
        assertEquals("Exceptional STR",c.getExceptionalStrength(),2);
        assertEquals("INT",c.getIntelligence(),10);
        assertEquals("WIS",c.getWisdom(),13);
        assertEquals("DEX",c.getDexterity(),15);
        assertEquals("CON",c.getConstitution(),17);
        assertEquals("CHA",c.getCharisma(),12);
        assertEquals("Class",c.getCharacterClass().getName(),"fighter");
        assertEquals("Gender",c.getGender(),Gender.MALE);
        assertEquals("Race",c.getRace(),Race.HUMAN);
        assertEquals("Alignment",c.getAlignment(),Alignment.LAWFUL_GOOD);
        assertEquals("Portrait",c.getPortrait(),0);
        assertEquals("HP",c.getHitPoints(),19);
        assertEquals("Max HP",c.getMaxHitPoints(),19);
        assertEquals("AC",c.getArmorClass(),7);
        assertEquals("Food",c.getFood(),99);
    }
    
    public void testCharacterOne() {
        Character c = s.getCharacter(1);
        assertTrue("Character is active",c.isActive());
        assertEquals("Name","ELAINE",c.getName());
        assertEquals("STR",c.getStrength(),14);
        assertEquals("Exceptional STR",c.getExceptionalStrength(),0);
        assertEquals("INT",c.getIntelligence(),13);
        assertEquals("WIS",c.getWisdom(),16);
        assertEquals("DEX",c.getDexterity(),18);
        assertEquals("CON",c.getConstitution(),15);
        assertEquals("CHA",c.getCharisma(),12);
        assertEquals("Class",c.getCharacterClass().getName(),"ranger");
        assertEquals("Gender",c.getGender(),Gender.FEMALE);
        assertEquals("Race",c.getRace(),Race.HUMAN);
        assertEquals("Alignment",c.getAlignment(),Alignment.NEUTRAL_GOOD);
        assertEquals("Portrait",c.getPortrait(),39);
        assertEquals("HP",c.getHitPoints(),17);
        assertEquals("Max HP",c.getMaxHitPoints(),17);
        assertEquals("AC",c.getArmorClass(),4);
        assertEquals("Food",c.getFood(),99);
    }
    
    public void testCharacterTwo() {
        Character c = s.getCharacter(2);
        assertTrue("Character is active",c.isActive());
        assertEquals("Name","HERMAN",c.getName());
        assertEquals("STR",c.getStrength(),10);
        assertEquals("Exceptional STR",c.getExceptionalStrength(),0);
        assertEquals("INT",c.getIntelligence(),18);
        assertEquals("WIS",c.getWisdom(),12);
        assertEquals("DEX",c.getDexterity(),11);
        assertEquals("CON",c.getConstitution(),14);
        assertEquals("CHA",c.getCharisma(),15);
        assertEquals("Class",c.getCharacterClass().getName(),"mage");        
        assertEquals("Gender",c.getGender(),Gender.MALE);
        assertEquals("Race",c.getRace(),Race.ELF);
        assertEquals("Alignment",c.getAlignment(),Alignment.CHAOTIC_GOOD);
        assertEquals("Portrait",c.getPortrait(),1);
        assertEquals("HP",c.getHitPoints(),9);
        assertEquals("Max HP",c.getMaxHitPoints(),9);
        assertEquals("AC",c.getArmorClass(),10);
        assertEquals("Food",c.getFood(),99);
    }    
    
    public void testCharacterThree() {
        Character c = s.getCharacter(3);
        assertTrue("Character is active",c.isActive());
        assertEquals("Name","GUYBRUSH",c.getName());
        assertEquals("STR",c.getStrength(),12);
        assertEquals("Exceptional STR",c.getExceptionalStrength(),0);
        assertEquals("INT",c.getIntelligence(),13);
        assertEquals("WIS",c.getWisdom(),16);
        assertEquals("DEX",c.getDexterity(),12);
        assertEquals("CON",c.getConstitution(),11);
        assertEquals("CHA",c.getCharisma(),15);
        assertEquals("Class",c.getCharacterClass().getName(),"cleric");        
        assertEquals("Gender",c.getGender(),Gender.MALE);
        assertEquals("Race",c.getRace(),Race.HALF_ELF);
        assertEquals("Alignment",c.getAlignment(),Alignment.LAWFUL_NEUTRAL);
        assertEquals("Portrait",c.getPortrait(),12);
        assertEquals("HP",c.getHitPoints(),5);
        assertEquals("Max HP",c.getMaxHitPoints(),5);
        assertEquals("AC",c.getArmorClass(),8);
        assertEquals("Food",c.getFood(),99);
    }    

    public void testCharacterFour() {
        Character c = s.getCharacter(4);
        assertFalse("Character is active",c.isActive());
    }

    public void testCharacterFive() {
        Character c = s.getCharacter(5);
        assertFalse("Character is active",c.isActive());
    }
    
}

