package org.emeriss;

import org.apache.log4j.Logger;


public class Character {

    public static final byte NULL_SCORE = 0;
    public static final byte MIN_SCORE = 3;
    public static final byte MAX_SCORE = 18;
    public static final byte NAME_MAX_LENGTH = 10;
    public static final byte NULL_EXCEPTIONAL_SCORE = 0;
    public static final byte MIN_EXCEPTIONAL_SCORE = 0;
    public static final byte MAX_EXCEPTIONAL_SCORE = 100;
    public static final byte NULL_FOOD_PCT = 0;
    public static final byte MIN_FOOD_PCT = 0;
    public static final byte MAX_FOOD_PCT = 100;
    
    private static final Logger LOGGER = Logger.getLogger(Character.class);
    
    protected boolean isActive;
    protected String name;
    protected byte id, intelligence, portrait, food;
    protected byte strength, exceptionalStrength, wisdom, dexterity, constitution, charisma;
    protected Alignment alignment;
    protected short hitPoints, maxHitPoints, armorClass;
    protected Gender gender;
    protected Race race;
    protected CharacterClass characterClass;
    
    public Character() {
        id = -1;
        isActive = false;
        name = "none";
        strength = NULL_SCORE;
        exceptionalStrength = NULL_EXCEPTIONAL_SCORE;
        intelligence = NULL_SCORE;
        wisdom = NULL_SCORE;
        dexterity = NULL_SCORE;
        constitution = NULL_SCORE;
        charisma = NULL_SCORE;
        alignment = Alignment.UNDEFINED;
        gender = Gender.UNDEFINED;
        race = Race.UNDEFINED;
        characterClass = CharacterClassFactory.getClass(-1);
        hitPoints = -1;
        maxHitPoints = -1;
        food = -1;
        portrait = -1;
        armorClass = 0;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) throws CharacterUpdateException {
        if (s.length() > NAME_MAX_LENGTH) {
            throw new CharacterUpdateException("character name length must be between 1 and " + NAME_MAX_LENGTH + ".");    
        }
        name = s.toUpperCase();
    }

    public void show() {
        LOGGER.info("id:" + this.id + " active: " + this.isActive + " name: " + this.name);
        LOGGER.info("STR: " + this.strength + " / " + this.exceptionalStrength + 
                " INT: " + this.intelligence + " WIS: " + this.wisdom + 
                " DEX: " + this.dexterity + " CON: " + this.constitution + " CHA: " + this.charisma);
        LOGGER.info("Race: " + this.race + " Gender: " + this.gender 
                + " Class: " + this.characterClass
                + " Alignment: " + this.alignment + " PortraitNumber:" + this.portrait);
        LOGGER.info("HP: " + this.hitPoints + " / " + this.maxHitPoints 
                + " AC:" + this.armorClass + " Food:" + this.food + "\n");
    }

    
    public byte getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(byte value) {
        this.intelligence = value;
    }

    public byte getWisdom() {
        return wisdom;
    }

    public void setWisdom(byte value) {
        this.wisdom = value;
    }

    public byte getDexterity() {
        return dexterity;
    }

    public void setDexterity(byte value) {
        this.dexterity = value;
    }

    public byte getConstitution() {
        return constitution;
    }

    public void setConstitution(byte value) {
        this.constitution = value;
    }

    public byte getCharisma() {
        return charisma;
    }

    public void setCharisma(byte value) {
        this.charisma = value;
    }

    public short getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(short value) {
        this.maxHitPoints = value;
    }

    public short getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(short value) {
        this.hitPoints = value;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public byte getFood() {
        return food;
    }

    public void setFood(byte value) {
        this.food = value;
    }

    public byte getExceptionalStrength() {
        return exceptionalStrength;
    }

    public void setExceptionalStrength(byte value) {
        this.exceptionalStrength = value;
    }

    public byte getStrength() {
        return strength;
    }

    public void setStrength(byte value) {
        this.strength = value;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public byte getPortrait() {
        return portrait;
    }

    public void setPortrait(byte value) {
        this.portrait = value;
    }

    public short getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(short armorClass) {
        this.armorClass = armorClass;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public boolean isMultiClass() {
        return this.characterClass.isMultiClass();
    }
}
