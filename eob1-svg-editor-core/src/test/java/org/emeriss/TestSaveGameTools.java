package org.emeriss;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

public class TestSaveGameTools extends TestCase {

    private static final Logger LOGGER = Logger.getLogger(TestSaveGameTools.class);
    
    public TestSaveGameTools(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( TestSaveGameTools.class );
    }

    public void testBytesToInt() {
        byte b0, b1, b2, b3;
        int n1, n2;
        String testName;
        
        b0 = (byte) 0x0;
        b1 = (byte) 0x0;
        b2 = (byte) 0x0;
        b3 = (byte) 0x0;
        n1 = 0;
        n2 = SaveGameTools.bytesToInt(b0, b1, b2, b3);
        testName = "test = " + n1;
        assertEquals(testName,n1,n2);
        
        b0 = (byte) 0x88;
        b1 = (byte) 0x13;
        b2 = (byte) 0x0;
        b3 = (byte) 0x0;
        n1 = 5000;
        n2 = SaveGameTools.bytesToInt(b0, b1, b2, b3);
        testName = "test = " + n1;
        assertEquals(testName,n1,n2);        
    }
    
    public void testIntTo4Bytes() {
        byte[] bs1, bs2;
        int n1,i;
        
        bs1 = new byte[4];
        
        bs1[0] = (byte) 0x88;
        bs1[1] = (byte) 0x13;
        bs1[2] = (byte) 0x0;
        bs1[3] = (byte) 0x0;
        n1 = 5000;
        bs2 = SaveGameTools.intTo4Bytes(n1);
        for (i=0;i<4;i++) {
            LOGGER.debug("i:" + i + " :" + bs1[i] + " == " + bs2[i]);
            assertEquals("test byte "+i, bs1[i], bs2[i]);
        }
    }
    
}
