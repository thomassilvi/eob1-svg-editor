package org.emeriss;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.log4j.Logger;

public class SaveGame {

    public static final int MAX_CHARACTERS = 6;
    public static final int MAX_ACTIVE_CHARACTERS = 4;

    private static final Logger LOGGER = Logger.getLogger(SaveGame.class);
    protected Character[] characters;
    protected byte[] data;
    protected String savedFileName;
    protected boolean loaded;
    
    public SaveGame() {
        characters = new Character[MAX_CHARACTERS];
        for (int i=0;i<MAX_CHARACTERS;i++) {
            characters[i] = new Character();
        }
        data = null;
        savedFileName = null;
        setLoaded(false);
    }
    
    public void load(String fileName) throws SaveGameException {
        try {
            Path p = Paths.get(fileName);
            data = Files.readAllBytes(p);
            
            int fpos,i;
            byte bGender;
    
            fpos = 0;
            
            for (i=0;i<MAX_CHARACTERS;i++)
            {
                characters[i].setId(data[fpos]);        
                characters[i].setActive(data[fpos+1]==1);
                String nameTmp = new String( Arrays.copyOfRange(data,fpos+2,fpos+2+Character.NAME_MAX_LENGTH), "UTF-8");
                characters[i].setName(nameTmp.trim());    
                
                characters[i].setStrength(data[fpos+14]);
                characters[i].setExceptionalStrength(data[fpos+16]);
                characters[i].setIntelligence(data[fpos+18]);
                characters[i].setWisdom(data[fpos+20]);
                characters[i].setDexterity(data[fpos+22]);
                characters[i].setConstitution(data[fpos+24]);
                characters[i].setCharisma(data[fpos+26]);
    
                characters[i].setHitPoints(data[fpos+27]);
                characters[i].setMaxHitPoints(data[fpos+28]);
                characters[i].setArmorClass(data[fpos+29]);
                
                bGender =  (byte) (data[fpos+31] % 2);
                characters[i].setRace(Race.valueOf(data[fpos+31] - bGender));
                characters[i].setGender(Gender.valueOf(bGender));
                characters[i].setCharacterClass(CharacterClass.valueOf(data[fpos+32]));
                characters[i].setAlignment(Alignment.valueOf(data[fpos+33]));
                characters[i].setPortrait(data[fpos+34]);
                characters[i].setFood(data[fpos+35]);
                // next character
                fpos += 243;
            }
            
            savedFileName = fileName;
            setLoaded(true);
        }
        catch (CharacterUpdateException e) {
            LOGGER.error(e);
        }
        catch (IOException e) {
            LOGGER.error(e);
            throw new SaveGameException("Load failed.");
        }
    }
    
    public void show() {
        for (int i=0;i<MAX_CHARACTERS;i++) {
            characters[i].show();
        }
    }
    
    public Character getCharacter(int i) {
        return characters[i];
    }
    
    public void save(String fileName) throws SaveGameException    {
        int fpos,i,j,k;
        String nameTmp;
        
        fpos = 0;
        for (i=0;i<MAX_CHARACTERS;i++) {
            
            // save active state
            
            if (characters[i].isActive()) {
                data[fpos+1] = 1;
            } else {
                data[fpos+1] = 0;
            }
            
            // save name
            
            nameTmp = characters[i].getName();
            k = Character.NAME_MAX_LENGTH - nameTmp.length();
            for (j=0;j<k;j++) {
                nameTmp = nameTmp + " ";
            }

            for (j=0;j<Character.NAME_MAX_LENGTH;j++) {
                data[fpos+2+j] = (byte) nameTmp.charAt(j);
            }

            // ability base scores
            
            data[fpos+14] = characters[i].getStrength();
            data[fpos+13] = data[fpos+14];
            
            data[fpos+16] = characters[i].getExceptionalStrength();
            data[fpos+15] = data[fpos+16];

            data[fpos+18] = characters[i].getIntelligence();
            data[fpos+17] = data[fpos+18];

            data[fpos+20] = characters[i].getWisdom();
            data[fpos+19] = data[fpos+20];
            
            data[fpos+22] = characters[i].getDexterity();
            data[fpos+21] = data[fpos+22];
            
            data[fpos+24] = characters[i].getConstitution();
            data[fpos+23] = data[fpos+24];
            
            data[fpos+26] = characters[i].getCharisma();
            data[fpos+25] = data[fpos+26];

            // next character
            fpos += 243;
        }
        
        try {
            Path p = Paths.get(fileName);
            OutputStream out = Files.newOutputStream(p);
            out.write(data);
        }
        catch (IOException e) {
            LOGGER.error(e);
            throw new SaveGameException(e.getMessage());
        }
    }
    
    public void save() throws SaveGameException    {
        save(savedFileName);
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

}

