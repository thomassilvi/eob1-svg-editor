package org.emeriss;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class TestLoadSaveGame2 extends TestCase
{
    private SaveGame s;
    private static final Logger LOGGER = Logger.getLogger(TestLoadSaveGame2.class);


    public TestLoadSaveGame2(String testName) {
        super(testName);
        s = new SaveGame();
        
        try {
            Path p = Paths.get("");
            String fileName = p.toAbsolutePath().toString() + "/src/test/resources/test2.sav";
            s.load(fileName);
        }
        catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    public static Test suite() {
        return new TestSuite( TestLoadSaveGame2.class );
    }

    public void testCharacterZero() {
        Character c = s.getCharacter(0);
        assertEquals("Name","SIMPLET",c.getName());
        CharacterClass cc = c.getCharacterClass();
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc; 
        assertEquals("class count",2,cmc.getClassCount());
        CharacterSingleClass csc;
        
        csc = cmc.getClassAt(0);
        assertEquals("class 0","fighter",csc.getName());
        assertEquals("xp 0",3001,csc.getExperience());
        assertEquals("lvl 0",2,csc.getLevel());
        
        csc = cmc.getClassAt(1);
        assertEquals("class 1","thief",csc.getName());
        assertEquals("xp 1",3001,csc.getExperience());
        assertEquals("lvl 1",3,csc.getLevel());
    }
    
    public void testCharacterOne() {
        Character c = s.getCharacter(1);
        assertEquals("Name","DORMEUR",c.getName());
        CharacterClass cc = c.getCharacterClass();
        assertTrue(cc.isMultiClass());
        CharacterMultiClass cmc = (CharacterMultiClass) cc; 
        assertEquals("class count",3,cmc.getClassCount());
        CharacterSingleClass csc;
        
        csc = cmc.getClassAt(0);
        assertEquals("class 0","fighter",csc.getName());
        assertEquals("xp 0",1999,csc.getExperience());
        assertEquals("lvl 0",1,csc.getLevel());
        
        csc = cmc.getClassAt(1);
        assertEquals("class 1","mage",csc.getName());
        assertEquals("xp 1",1999,csc.getExperience());
        assertEquals("lvl 1",1,csc.getLevel());
        
        csc = cmc.getClassAt(2);
        assertEquals("class 2","thief",csc.getName());
        assertEquals("xp 2",1999,csc.getExperience());
        assertEquals("lvl 2",2,csc.getLevel());
    }
    
}

