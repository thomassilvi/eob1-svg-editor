package emeriss.org;

import java.awt.Label;
import java.awt.Panel;

import org.emeriss.Character;
import org.emeriss.CharacterUpdateException;

@SuppressWarnings("serial")
public class CharacterEquipmentPanel extends Panel {

    public CharacterEquipmentPanel() {
        super();
        add(new Label("not yet available"));
    }
    

    public void updateWithCharacter(Character c) {
        // TODO
    }

    public void updateCharacter(Character c) throws CharacterUpdateException {
        // TODO
    }
    
}
