package org.emeriss;


public class SaveGameTools {

    private SaveGameTools() {
    }

    public static int bytesToInt(byte a, byte b, byte c, byte d) {
        int result;
        
        result = unsignedByteToInt(a) 
            + unsignedByteToInt(b) * 256 
            + unsignedByteToInt(c) * 256 * 256
            + unsignedByteToInt(d) * 256 * 256 * 256;
                
        return result;
    }

    public static int unsignedByteToInt(byte b) {
        return b & 0xff;
    }
    
    public static byte intToUnsignedByte(int v) {
        byte result = (byte) v;
        result = (byte) (result & (0xff));
        return result;
    }
    
    public static byte[] intTo4Bytes(int v) {
        byte[] result = new byte[4];

        int divTmp;
        int remainingTmp = v;
        int div2Tmp = 256 * 256 * 256;
        
        for (byte i=0;i<4;i++) {
            divTmp = remainingTmp / div2Tmp;
            remainingTmp = remainingTmp - divTmp * div2Tmp;
            result[3-i] = intToUnsignedByte(divTmp);
            div2Tmp = div2Tmp / 256;            
        }
        
        return result;
    }
    
}

