package emeriss.org;

import org.emeriss.Character;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestIntSelectorPanel extends TestCase
{

    public TestIntSelectorPanel( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( TestIntSelectorPanel.class );
    }

    public void testInit() {
        IntSelectorPanel p = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        assertTrue( p.getValue() == Character.NULL_SCORE);
    }

    public void testSetValue() {
        IntSelectorPanel p = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        p.setValue(Character.MAX_SCORE);
        assertTrue( p.getValue() == Character.MAX_SCORE);
    }

    public void testIncrement() {
        IntSelectorPanel p = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        p.setValue(Character.MIN_SCORE);
        for (byte i=0;i<(Character.MAX_SCORE+1);i++) {
            p.increment();
        }
        assertTrue( p.getValue() == Character.MAX_SCORE);
    }

    public void testDecrement() {
        IntSelectorPanel p = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        p.setValue(Character.MIN_SCORE);
        for (byte i=0;i<(Character.MAX_SCORE+1);i++) {
            p.decrement();
        }
        assertTrue( p.getValue() == Character.MIN_SCORE);
    }
    
}
