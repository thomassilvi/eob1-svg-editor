package emeriss.org;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

import org.emeriss.Spells;
import org.emeriss.Spell;

@SuppressWarnings("serial")
public class SpellsPanel extends Panel {

    protected JTabbedPane mainTabPanel;
    protected TextField[][] spellsName;
    protected JCheckBox[][] spellsKnown;
    protected int[] rows;
    
    public SpellsPanel() {
        super();
        setLayout(new GridBagLayout());
        mainTabPanel = new JTabbedPane(JTabbedPane.NORTH);
        String strTmp;
    
        rows = new int[Spells.MAX_LEVELS_COUNT];
        spellsName = new TextField[Spells.MAX_LEVELS_COUNT][Spells.MAX_SPELLS_BY_LEVEL];
        spellsKnown = new JCheckBox[Spells.MAX_LEVELS_COUNT][Spells.MAX_SPELLS_BY_LEVEL];
        Panel[] panelsTmp = new Panel[Spells.MAX_LEVELS_COUNT];
        
        for (int i=0;i<Spells.MAX_LEVELS_COUNT;i++) {
            panelsTmp[i] = new Panel();
            panelsTmp[i].setLayout(new GridBagLayout());
            for (int j=0;j<Spells.MAX_SPELLS_BY_LEVEL;j++) {
                spellsName[i][j] = new TextField("",16);    
                spellsName[i][j].setEditable(false);
                panelsTmp[i].add(spellsName[i][j],GridBagTools.getGridBagConstraint(0,j));
                spellsKnown[i][j] = new JCheckBox("",false);
                spellsKnown[i][j].setEnabled(false);
                panelsTmp[i].add(spellsKnown[i][j],GridBagTools.getGridBagConstraint(1,j));
            }
            strTmp = Integer.toString(i+1); 
            mainTabPanel.add(strTmp, panelsTmp[i]);
        }        
        add(mainTabPanel);
    }

    public void reset() {
        for (int i=0;i<rows.length;i++) {
            rows[i] = 0;
            for (int j=0;j<Spells.MAX_SPELLS_BY_LEVEL;j++) {
                spellsKnown[i][j].setEnabled(false);
            }
        }
    }
    
    public void load(List<Spell> ls) {
        int i,j;
        reset();
        
        for (Spell spellTmp : ls) {
            i = spellTmp.getLevel()-1;
            j = rows[i];
            spellsName[i][j].setText(spellTmp.getName());
            spellsKnown[i][j].setSelected(spellTmp.isKnown());
            if (spellTmp.isKnown()) {
                spellsKnown[i][j].setBackground(Color.LIGHT_GRAY);
            } else {
                spellsKnown[i][j].setEnabled(true);
                spellsKnown[i][j].setBackground(Color.GREEN);                
            }
            rows[i] = j + 1;
        }
    }
    
    public List<Spell> getSpellsFromUI() {
        List<Spell> result = new ArrayList<Spell>();
        String spellNameTmp;
        Spell s;
        
        for (int i=0;i<Spells.MAX_LEVELS_COUNT;i++) {
            for (int j=0;j<Spells.MAX_SPELLS_BY_LEVEL;j++) {
                spellNameTmp = spellsName[i][j].getText();
                if ("".equals(spellNameTmp)) {
                    continue;
                }
                s = new Spell(spellNameTmp,i,spellsKnown[i][j].isSelected());
                result.add(s);
            }
        }
        
        return result;
    }
    
}
