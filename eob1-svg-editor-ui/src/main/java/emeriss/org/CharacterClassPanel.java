package emeriss.org;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import org.emeriss.Character;
import org.emeriss.CharacterClass;
import org.emeriss.CharacterMultiClass;
import org.emeriss.CharacterSingleClass;
import org.emeriss.CharacterUpdateException;

@SuppressWarnings("serial")
public class CharacterClassPanel extends Panel {
    
    protected TextField[] tfClasses, tfExperiences, tfLevels;
    protected Label[] lblClasses;
    
    public CharacterClassPanel() {
        tfExperiences = new TextField[CharacterMultiClass.MAX_CLASSES];
        tfLevels = new TextField[CharacterMultiClass.MAX_CLASSES];
        lblClasses = new Label[CharacterMultiClass.MAX_CLASSES];
        
        super.setLayout(new GridBagLayout());
        
        add(new Label("Experience"),getGridBagConstraint(1,0));
        add(new Label("Level"),getGridBagConstraint(2,0));
        byte rTmp;
        for (byte i=0;i<CharacterMultiClass.MAX_CLASSES;i++) {
            rTmp = i;
            rTmp++;
            lblClasses[i] = new Label("            ");
            add(lblClasses[i],getGridBagConstraint(0,rTmp));
            tfExperiences[i] = new TextField("",6);
            tfExperiences[i].setEditable(true);
            add(tfExperiences[i],getGridBagConstraint(1,rTmp));
            tfLevels[i] = new TextField("",2);
            tfLevels[i].setEditable(false);
            add(tfLevels[i],getGridBagConstraint(2,rTmp));
            if (i>0) {
                tfExperiences[i].setVisible(false);
                tfLevels[i].setVisible(false);
            }
        }
    }

    private static GridBagConstraints getGridBagConstraint(int gridx, int gridy) {
        GridBagConstraints result = new GridBagConstraints();
        result.gridx = gridx;
        result.gridy = gridy;
        return result;
    }

    public void resetToOneClass() {
        for (byte i=1;i<CharacterMultiClass.MAX_CLASSES;i++) {
            lblClasses[i].setText("");
            tfExperiences[i].setVisible(false);
            tfLevels[i].setVisible(false);
        }
    }
    
    public void updateWithSingleClassCharacter(int index,CharacterSingleClass c) {
        lblClasses[index].setText(c.getName());
        tfExperiences[index].setText(Integer.toString(c.getExperience()));
        tfLevels[index].setText(Integer.toString(c.getLevel()));
        tfExperiences[index].setVisible(true);            
        tfLevels[index].setVisible(true);
    }
    
    public void updateWithMultiClassCharacter(CharacterMultiClass cmc) {
        CharacterSingleClass csc;
        
        for (int i=0;i<cmc.getClassCount();i++) {
            csc = cmc.getClassAt(i);
            updateWithSingleClassCharacter(i,csc);
        }
    }
    
    public void updateWithCharacter(Character c) {
        CharacterClass cc = c.getCharacterClass();

        resetToOneClass();        
        
        if (cc.isMultiClass()) {
            updateWithMultiClassCharacter((CharacterMultiClass) cc);
        } else {
            updateWithSingleClassCharacter(0,(CharacterSingleClass) cc);
        }

        this.invalidate();
        this.validate();
    }
    
    public void updateCharacterSingleClass(int index, CharacterSingleClass c) 
            throws CharacterUpdateException {
        String sTmp = tfExperiences[index].getText();
        int xpTmp = Integer.parseInt(sTmp);
        c.setExperience(xpTmp);
    }
    
    public void updateCharacterMultiClass(CharacterMultiClass c) 
            throws CharacterUpdateException {
        CharacterSingleClass cscTmp;
        for (byte i=0;i<CharacterMultiClass.MAX_CLASSES;i++) {
            cscTmp = c.getClassAt(i);
            if (cscTmp != null) {
                updateCharacterSingleClass(i,cscTmp);
            }
        }
    }
    
    public void updateCharacter(Character c) throws CharacterUpdateException {
        if (c.isMultiClass()) {
            updateCharacterMultiClass((CharacterMultiClass) c.getCharacterClass());
        } else {
            updateCharacterSingleClass(0,(CharacterSingleClass) c.getCharacterClass());
        }
    }
}
