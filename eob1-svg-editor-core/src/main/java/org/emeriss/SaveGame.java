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
    
    protected static final int OFFSET_XP_1 = 0x27;
    protected static final int OFFSET_LVL_1 = 0x24;

    protected static final int OFFSET_MAGE_KNOWN_SPELL = 0x73;
    protected static final int OFFSET_MAGE_SPELL_START = 0x37;
    
    protected static final int OFFSET_CLERIC_PALADIN_SPELL_START = 0x55;
    
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
        CharacterClass ccTmp;
        int fpos,i;
        byte bGender;
        
        try {
            Path p = Paths.get(fileName);
            data = Files.readAllBytes(p);
    
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
                
                characters[i].setAlignment(Alignment.valueOf(data[fpos+33]));
                characters[i].setPortrait(data[fpos+34]);
                characters[i].setFood(data[fpos+35]);
                
                // set class properties
                
                ccTmp = CharacterClassFactory.getClass(data[fpos+32]);
                characters[i].setCharacterClass(ccTmp);
                
                if (ccTmp.isMultiClass()) {
                    loadMultiCharacterClass(ccTmp,fpos);
                } else {
                    loadSingleCharacterClass(ccTmp,fpos);
                }
                
                // load spells

                loadMageSpells(i,fpos);
                loadClericSpells(i,fpos);
                loadPaladinSpells(i,fpos);

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
    
    public void reload() throws SaveGameException {
        load(savedFileName);
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

            // save class properties

            if (characters[i].getCharacterClass().isMultiClass()) {
                writeMultiCharacterClass(characters[i].getCharacterClass(),fpos);
            } else {
                writeSingleCharacterClass(characters[i].getCharacterClass(),fpos);
            }
            
            // save spells

            saveMageSpells(i,fpos);
            saveClericSpells(i,fpos);
            savePaladinSpells(i,fpos);
            
            // misc
            
            data[fpos+27] = SaveGameTools.intToUnsignedByte(characters[i].getHitPoints());
            data[fpos+35] = characters[i].getFood();

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

    public void loadSingleCharacterClass (CharacterClass c, int fpos) {
        CharacterSingleClass cscTmp = (CharacterSingleClass) c;
        cscTmp.setLevel(data[fpos+OFFSET_LVL_1]);
        int xpTmp = readInt(fpos+OFFSET_XP_1);
        cscTmp.setExperience(xpTmp);        
    }
    
    public void loadMultiCharacterClass (CharacterClass c, int fpos) {
        CharacterMultiClass cmcTmp = (CharacterMultiClass) c;
        int xpTmp;
        
        for (byte j=0;j<cmcTmp.getClassCount();j++) {
            cmcTmp.getClassAt(j).setLevel(data[fpos+OFFSET_LVL_1+j]);
            xpTmp = readInt(fpos+OFFSET_XP_1+(j*4));
            cmcTmp.getClassAt(j).setExperience(xpTmp);
        }
    }
    
    public int readInt(int fpos) {
        return SaveGameTools.bytesToInt(data[fpos], 
            data[fpos+1], data[fpos+2], data[fpos+3]);
    }
    
    public void writeInt(int value, int fpos) {
        byte[] bytesXpTmp = SaveGameTools.intTo4Bytes(value);
        for (byte i=0;i<4;i++) {
            data[fpos+i] = bytesXpTmp[i];
        }        
    }
    
    public void writeSingleCharacterClass (CharacterClass c, int fpos) {
        CharacterSingleClass cscTmp = (CharacterSingleClass) c;
        writeInt(cscTmp.getExperience(),fpos+OFFSET_XP_1);
    }

    public void writeMultiCharacterClass(CharacterClass c, int fposInitial) {
        CharacterMultiClass cmcTmp = (CharacterMultiClass) c;
        CharacterSingleClass cscTmp;
        int fpos = fposInitial + OFFSET_XP_1;
        for (byte i=0;i<cmcTmp.getClassCount();i++) {
            cscTmp = cmcTmp.getClassAt(i);
            writeInt(cscTmp.getExperience(),fpos);
            fpos = fpos + 4;
        }
    }
 
    
    public void loadMageSpells(int characterIndex, int fpos) {
        CharacterClass cc = characters[characterIndex].getCharacterClass();
        CharacterClassMage cm = (CharacterClassMage) 
                CharacterClassTools.getSingleClassWithName(cc,CharacterClassMage.CLASS_NAME);
        
        if (cm==null) {
            return;
        }
        
        int fposTmp, codeTmp, i, j;
        
        // load known spells
        
        fposTmp = fpos + OFFSET_MAGE_KNOWN_SPELL;
        codeTmp = SaveGameTools.bytesToInt(data[fposTmp], data[fposTmp+1], 
                data[fposTmp+2], data[fposTmp+3]);
        Spells spellsTmp = MageSpellsTools.getSpells(codeTmp);
        
        // load memorized and loaded spells
        
        fposTmp = fpos + OFFSET_MAGE_SPELL_START;
        for (i = 1; i <= MageSpellsTools.getMaxLevelsCount(); i++) {
            for (j=0;j<MageSpellsTools.getMaxSpellsCountByLevel(i);j++) {
                if (data[fposTmp]!=0) {
                    MageSpellsTools.updateSpellBook(spellsTmp,data[fposTmp]);
                }
                fposTmp++;
        }
            
        cm.setSpells(spellsTmp);  
    }        
        
        
    }
    
    public void saveMageSpells(int characterIndex, int fpos) {
        CharacterClass cc = characters[characterIndex].getCharacterClass();
        CharacterClassMage mage = (CharacterClassMage) 
                CharacterClassTools.getSingleClassWithName(cc,CharacterClassMage.CLASS_NAME);

        if (mage==null) {
            return;
        }
        
        int i,j;
        
        // save known spells
        
        int newCode = MageSpellsTools.getSpellCode(mage.getSpells());
        byte[] bytesTmp = SaveGameTools.intTo4Bytes(newCode);
        int fposTmp = fpos + OFFSET_MAGE_KNOWN_SPELL;
        
        for (i=0;i<4;i++) {
            data[fposTmp+i] = bytesTmp[i];
        }
        
        // save memorized and loaded spells

        byte[] dataTmp;
        fposTmp = fpos + OFFSET_MAGE_SPELL_START;
        
        for (i=1;i<=MageSpellsTools.getMaxLevelsCount();i++) {
            dataTmp = MageSpellsTools.getMemorizedAndGained(mage.getSpells(), i);
            for (j=0;j<dataTmp.length;j++) {
                data[fposTmp] = dataTmp[j];
                fposTmp++;
            }
        }

    }
    
    public void loadClericSpells(int characterIndex, int fpos) {
        CharacterClass cc = characters[characterIndex].getCharacterClass();
        CharacterClassCleric cleric = (CharacterClassCleric) 
                CharacterClassTools.getSingleClassWithName(cc,CharacterClassCleric.CLASS_NAME);

        if (cleric==null) {
            return;
        }

        int fposTmp, i, j;
        Spells spellsTmp = ClericSpellsTools.getSpells();

        // load memorized and loaded spells

        fposTmp = fpos + OFFSET_CLERIC_PALADIN_SPELL_START;
        for (i = 1; i <= ClericSpellsTools.getMaxLevelsCount(); i++) {
            for (j=0;j<ClericSpellsTools.getMaxSpellsCountByLevel(i);j++) {
                if (data[fposTmp]!=0) {
                    ClericSpellsTools.updateSpellBook(spellsTmp,data[fposTmp]);
                }
                fposTmp++;
            }
        }

        cleric.setSpells(spellsTmp);  
    }
    
    
    public void saveClericSpells(int characterIndex, int fpos) {
        CharacterClass cc = characters[characterIndex].getCharacterClass();
        CharacterClassCleric cleric = (CharacterClassCleric) 
                CharacterClassTools.getSingleClassWithName(cc,CharacterClassCleric.CLASS_NAME);

        if (cleric==null) {
            return;
        }

        // save memorized and loaded spells

        int fposTmp, i, j;
        
        byte[] dataTmp;
        fposTmp = fpos + OFFSET_CLERIC_PALADIN_SPELL_START;
        
        for (i=1;i<=ClericSpellsTools.getMaxLevelsCount();i++) {
            dataTmp = ClericSpellsTools.getMemorizedAndGained(cleric.getSpells(), i);
            for (j=0;j<dataTmp.length;j++) {
                data[fposTmp] = dataTmp[j];
                fposTmp++;
            }
        }
    }
    
    public void loadPaladinSpells(int characterIndex, int fpos) {
        CharacterClass cc = characters[characterIndex].getCharacterClass();
        CharacterClassPaladin paladin = (CharacterClassPaladin) 
                CharacterClassTools.getSingleClassWithName(cc,CharacterClassPaladin.CLASS_NAME);

        if (paladin==null) {
            return;
        }      
        int fposTmp, i, j;
        Spells spellsTmp = PaladinSpellsTools.getSpells();

        // load memorized and loaded spells

        fposTmp = fpos + OFFSET_CLERIC_PALADIN_SPELL_START;
        for (i = 1; i <= PaladinSpellsTools.getMaxLevelsCount(); i++) {
            for (j=0;j<PaladinSpellsTools.getMaxSpellsCountByLevel(i);j++) {
                if (data[fposTmp]!=0) {
                    PaladinSpellsTools.updateSpellBook(spellsTmp,data[fposTmp]);
                }
                fposTmp++;
            }
        }

        paladin.setSpells(spellsTmp);  
    }
    
    public void savePaladinSpells(int characterIndex, int fpos) {
        CharacterClass cc = characters[characterIndex].getCharacterClass();
        CharacterClassPaladin paladin = (CharacterClassPaladin) 
                CharacterClassTools.getSingleClassWithName(cc,CharacterClassPaladin.CLASS_NAME);

        if (paladin==null) {
            return;
        }        

        // save memorized and loaded spells

        int fposTmp, i, j;
        
        byte[] dataTmp;
        fposTmp = fpos + OFFSET_CLERIC_PALADIN_SPELL_START;
        
        for (i=1;i<=PaladinSpellsTools.getMaxLevelsCount();i++) {
            dataTmp = PaladinSpellsTools.getMemorizedAndGained(paladin.getSpells(), i);
            for (j=0;j<dataTmp.length;j++) {
                data[fposTmp] = dataTmp[j];
                fposTmp++;
            }
        }
    }
    
}






