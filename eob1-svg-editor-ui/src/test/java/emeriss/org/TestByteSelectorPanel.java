package emeriss.org;

import org.emeriss.Character;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestByteSelectorPanel extends TestCase
{

    public TestByteSelectorPanel( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestByteSelectorPanel.class );
    }

    public void testInit() {
        ByteSelectorPanel p = new ByteSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        assertTrue( p.getValue() == Character.NULL_SCORE);
    }

    public void testSetValue() {
        ByteSelectorPanel p = new ByteSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        p.setValue(Character.MAX_SCORE);
        assertTrue( p.getValue() == Character.MAX_SCORE);
    }

    public void testIncrement() {
        ByteSelectorPanel p = new ByteSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        p.setValue(Character.MIN_SCORE);
        for (byte i=0;i<(Character.MAX_SCORE+1);i++) {
            p.increment();
        }
        assertTrue( p.getValue() == Character.MAX_SCORE);
    }

    public void testDecrement() {
        ByteSelectorPanel p = new ByteSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        p.setValue(Character.MIN_SCORE);
        for (byte i=0;i<(Character.MAX_SCORE+1);i++) {
            p.decrement();
        }
        assertTrue( p.getValue() == Character.MIN_SCORE);
    }
    
}
