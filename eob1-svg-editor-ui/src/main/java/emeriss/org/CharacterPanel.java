package emeriss.org;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import org.emeriss.Character;
import org.emeriss.CharacterUpdateException;


@SuppressWarnings("serial")
public class CharacterPanel extends Panel {
    private byte characterId;
    private TextField name;
    private TextField characterClass, alignment, race, gender;
    private ExceptionalAbilityPanel strength;
    private IntSelectorPanel intelligence, wisdom, dexterity, constitution, charisma;
    private IntSelectorPanel foodPct;
    private IntFractionPanel hitPoints;
    private CharacterClassPanel characterClassPanel;
    
    public CharacterPanel() {
        super();
        setLayout(new BorderLayout(2,2));
        setBackground(Color.LIGHT_GRAY);
        
        // character properties
        Panel panelCharacterProperties = new Panel();
        panelCharacterProperties.setLayout(new GridBagLayout());
        
        Panel panelCharacterStats = new Panel();
        panelCharacterStats.setLayout(new GridBagLayout());

        panelCharacterStats.add(new Label("Name"),getGridBagConstraint(0,0));
        name = new TextField("",16);
        name.setEditable(false);
        panelCharacterStats.add(name,getGridBagConstraint(1,0));
        
        panelCharacterStats.add(new Label("Class"),getGridBagConstraint(0,1));
        characterClass = new TextField("",16);
        characterClass.setEditable(false);
        panelCharacterStats.add(characterClass,getGridBagConstraint(1,1));
        
        panelCharacterStats.add(new Label("Alignment"),getGridBagConstraint(0,2));
        alignment = new TextField("",16);
        alignment.setEditable(false);
        panelCharacterStats.add(alignment,getGridBagConstraint(1,2));
        
        panelCharacterStats.add(new Label("Race"),getGridBagConstraint(0,3));
        race = new TextField("",16);
        race.setEditable(false);
        panelCharacterStats.add(race,getGridBagConstraint(1,3));
        
        panelCharacterStats.add(new Label("Gender"),getGridBagConstraint(0,4));
        gender = new TextField("",16);
        gender.setEditable(false);
        panelCharacterStats.add(gender,getGridBagConstraint(1,4));
        
        // ability score
        
        panelCharacterStats.add(new Label("Strength"),getGridBagConstraint(0,5));
        strength = new ExceptionalAbilityPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE,
                Character.NULL_EXCEPTIONAL_SCORE, Character.MIN_EXCEPTIONAL_SCORE, 
                Character.MAX_EXCEPTIONAL_SCORE);
        panelCharacterStats.add(strength,getGridBagConstraint(1,5));
        
        panelCharacterStats.add(new Label("Intelligence"),getGridBagConstraint(0,6));
        intelligence = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(intelligence,getGridBagConstraint(1,6));
        
        panelCharacterStats.add(new Label("Wisdom"),getGridBagConstraint(0,7));
        wisdom = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(wisdom,getGridBagConstraint(1,7));
        
        panelCharacterStats.add(new Label("Dexterity"),getGridBagConstraint(0,8));
        dexterity = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(dexterity,getGridBagConstraint(1,8));
        
        panelCharacterStats.add(new Label("Constitution"),getGridBagConstraint(0,9));
        constitution = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(constitution,getGridBagConstraint(1,9));
        
        panelCharacterStats.add(new Label("Charisma"),getGridBagConstraint(0,10));
        charisma = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(charisma,getGridBagConstraint(1,10));
        
        // misc
        
        panelCharacterStats.add(new Label("Food %"),getGridBagConstraint(0,11));
        foodPct = new IntSelectorPanel(Character.NULL_FOOD_PCT, Character.MIN_FOOD_PCT, Character.MAX_FOOD_PCT);
        panelCharacterStats.add(foodPct,getGridBagConstraint(1,11));
        
        panelCharacterStats.add(new Label("HP"),getGridBagConstraint(0,12));
        hitPoints = new IntFractionPanel(0,0,255);
        panelCharacterStats.add(hitPoints,getGridBagConstraint(1,12));    
        
        // class / xp / lvl
        
        characterClassPanel = new CharacterClassPanel();
        GridBagConstraints cbcTmp = getGridBagConstraint(0,13);
        cbcTmp.gridwidth = 2;
        cbcTmp.ipady = 10;
        panelCharacterStats.add(characterClassPanel,cbcTmp);
        
        // 
        
        panelCharacterProperties.add(new Label("Character info"),getGridBagConstraint(0,0));
        panelCharacterProperties.add(panelCharacterStats,getGridBagConstraint(0,1));


        add(panelCharacterProperties,BorderLayout.WEST);
    }

    private static GridBagConstraints getGridBagConstraint(int gridx, int gridy) {
        GridBagConstraints result = new GridBagConstraints();
        result.gridx = gridx;
        result.gridy = gridy;
        return result;
    }

    public void updateWithCharacter(Character c) {
        setCharacterId(c.getId());
        name.setText(c.getName());
        characterClass.setText(c.getCharacterClass().getName()); 
        alignment.setText(c.getAlignment().toString());
        race.setText(c.getRace().toString());
        gender.setText(c.getGender().toString());
        strength.setValue(c.getStrength());
        strength.setExceptionalScore(c.getExceptionalStrength());
        intelligence.setValue(c.getIntelligence());
        wisdom.setValue(c.getWisdom()); 
        dexterity.setValue(c.getDexterity());
        constitution.setValue(c.getConstitution());
        charisma.setValue(c.getCharisma());
        foodPct.setValue(c.getFood());
        hitPoints.setValue(c.getHitPoints());
        hitPoints.setDenominator(c.getMaxHitPoints());
        characterClassPanel.updateWithCharacter(c);
    }
    
    public void active() {
        name.setEditable(true);
    }

    public byte getCharacterId() {
        return characterId;
    }

    public void setCharacterId(byte characterId) {
        this.characterId = characterId;
    }
    
    public boolean isUpdated() {
        return false;
    }

    public void updateCharacter(Character c) throws CharacterUpdateException {
            c.setName(name.getText());
            // ability
            c.setStrength((byte)strength.getValue());
            c.setExceptionalStrength((byte)strength.getExceptionalScore());
            c.setIntelligence((byte)intelligence.getValue());
            c.setWisdom((byte)wisdom.getValue());
            c.setDexterity((byte)dexterity.getValue());
            c.setConstitution((byte)constitution.getValue());
            c.setCharisma((byte)charisma.getValue());
            // misc
            c.setFood((byte)foodPct.getValue());
            c.setHitPoints((short)hitPoints.getValue());
            characterClassPanel.updateCharacter(c);
    }
    
}
