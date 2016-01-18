package emeriss.org;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import org.emeriss.Character;
import org.emeriss.CharacterUpdateException;

@SuppressWarnings("serial")
public class CharacterInfoPanel extends Panel {
    protected TextField name;
    protected TextField characterClass, alignment, race, gender;
    protected ExceptionalAbilityPanel strength;
    protected IntSelectorPanel intelligence, wisdom, dexterity, constitution, charisma;
    protected IntSelectorPanel foodPct;
    protected IntFractionPanel hitPoints;
    protected CharacterClassPanel characterClassPanel;
    protected Panel panelCharacterStats;
    
    public CharacterInfoPanel() {
        super();
        panelCharacterStats = new Panel();
        panelCharacterStats.setLayout(new GridBagLayout());
        
        panelCharacterStats.add(new Label("Name"),GridBagTools.getGridBagConstraint(0,0));
        name = new TextField("",16);
        name.setEditable(false);
        panelCharacterStats.add(name,GridBagTools.getGridBagConstraint(1,0));
        
        panelCharacterStats.add(new Label("Class"),GridBagTools.getGridBagConstraint(0,1));
        characterClass = new TextField("",16);
        characterClass.setEditable(false);
        panelCharacterStats.add(characterClass,GridBagTools.getGridBagConstraint(1,1));
        
        panelCharacterStats.add(new Label("Alignment"),GridBagTools.getGridBagConstraint(0,2));
        alignment = new TextField("",16);
        alignment.setEditable(false);
        panelCharacterStats.add(alignment,GridBagTools.getGridBagConstraint(1,2));
        
        panelCharacterStats.add(new Label("Race"),GridBagTools.getGridBagConstraint(0,3));
        race = new TextField("",16);
        race.setEditable(false);
        panelCharacterStats.add(race,GridBagTools.getGridBagConstraint(1,3));
        
        panelCharacterStats.add(new Label("Gender"),GridBagTools.getGridBagConstraint(0,4));
        gender = new TextField("",16);
        gender.setEditable(false);
        panelCharacterStats.add(gender,GridBagTools.getGridBagConstraint(1,4));
        
        // ability score
        
        panelCharacterStats.add(new Label("Strength"),GridBagTools.getGridBagConstraint(0,5));
        strength = new ExceptionalAbilityPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE,
                Character.NULL_EXCEPTIONAL_SCORE, Character.MIN_EXCEPTIONAL_SCORE, 
                Character.MAX_EXCEPTIONAL_SCORE);
        panelCharacterStats.add(strength,GridBagTools.getGridBagConstraint(1,5));
        
        panelCharacterStats.add(new Label("Intelligence"),GridBagTools.getGridBagConstraint(0,6));
        intelligence = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(intelligence,GridBagTools.getGridBagConstraint(1,6));
        
        panelCharacterStats.add(new Label("Wisdom"),GridBagTools.getGridBagConstraint(0,7));
        wisdom = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(wisdom,GridBagTools.getGridBagConstraint(1,7));
        
        panelCharacterStats.add(new Label("Dexterity"),GridBagTools.getGridBagConstraint(0,8));
        dexterity = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(dexterity,GridBagTools.getGridBagConstraint(1,8));
        
        panelCharacterStats.add(new Label("Constitution"),GridBagTools.getGridBagConstraint(0,9));
        constitution = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(constitution,GridBagTools.getGridBagConstraint(1,9));
        
        panelCharacterStats.add(new Label("Charisma"),GridBagTools.getGridBagConstraint(0,10));
        charisma = new IntSelectorPanel(Character.NULL_SCORE, Character.MIN_SCORE, Character.MAX_SCORE);
        panelCharacterStats.add(charisma,GridBagTools.getGridBagConstraint(1,10));
        
        // misc
        
        panelCharacterStats.add(new Label("Food %"),GridBagTools.getGridBagConstraint(0,11));
        foodPct = new IntSelectorPanel(Character.NULL_FOOD_PCT, Character.MIN_FOOD_PCT, Character.MAX_FOOD_PCT);
        panelCharacterStats.add(foodPct,GridBagTools.getGridBagConstraint(1,11));
        
        panelCharacterStats.add(new Label("HP"),GridBagTools.getGridBagConstraint(0,12));
        hitPoints = new IntFractionPanel(0,0,255);
        panelCharacterStats.add(hitPoints,GridBagTools.getGridBagConstraint(1,12));    
        
        // class / xp / lvl
        
        characterClassPanel = new CharacterClassPanel();
        GridBagConstraints cbcTmp = GridBagTools.getGridBagConstraint(0,13);
        cbcTmp.gridwidth = 2;
        cbcTmp.ipady = 10;
        panelCharacterStats.add(characterClassPanel,cbcTmp);
        
        add(panelCharacterStats);
    }

    public void updateWithCharacter(Character c) {
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
    
    public void active() {
        name.setEditable(true);
    }        
}
