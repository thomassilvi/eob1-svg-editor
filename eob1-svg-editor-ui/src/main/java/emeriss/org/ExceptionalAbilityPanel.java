package emeriss.org;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ExceptionalAbilityPanel extends IntSelectorPanel {

    protected TextField tfExceptionalScore;
    protected int exceptionalScore;
    protected int exceptionaNullValue, exceptionaMinValue, exceptionaMaxValue;
    
    public ExceptionalAbilityPanel(int parentNullValue, int parentMinValue, 
            int parentMaxValue, int nullValue, int minValue, int maxValue) {
        
        super(parentNullValue, parentMinValue, parentMaxValue);
        exceptionaNullValue =  nullValue;
        exceptionaMinValue = minValue;
        exceptionaMaxValue = maxValue;
        exceptionalScore = exceptionaNullValue;
        
        Label lTmp = new Label("/");
        lTmp.setFont(Eob1SvgEditorFonts.FONT_1);
        add(lTmp);
        tfExceptionalScore = new TextField("",3);
        tfExceptionalScore.setEditable(false);
        add(tfExceptionalScore);
        
        Button ePlus = new Button("+");
        ePlus.addActionListener(new ActionAddExActionListener());
        ePlus.setFont(Eob1SvgEditorFonts.FONT_1);
        add(ePlus);
        Button eMinus = new Button("-");
        eMinus.addActionListener(new ActionMinusExActionListener());
        eMinus.setFont(Eob1SvgEditorFonts.FONT_1);
        add(eMinus);
    }
    
    public void setExceptionalScore(int value) {
        exceptionalScore = value;
        updateUI();
    }

    public int getExceptionalScore() {
        return exceptionalScore;
    }
    
    @Override
    protected void updateUI() {
        super.updateUI();
        tfExceptionalScore.setText(Integer.toString(exceptionalScore));
    }    
    
    @Override
    protected void decrement() {
        if (value == super.maxValue) {
            exceptionalScore = exceptionaMinValue;
        }
        if (value > exceptionaMinValue) {
            value--;
            updateUI();
        }
    }
    
    protected void incrementExceptional() {
        if ((value==super.maxValue) && (exceptionalScore < exceptionaMaxValue)) {
            exceptionalScore++;
            updateUI();
        }

    }
    
    protected void decrementExceptional() {
        if ((value==super.maxValue) && (exceptionalScore > exceptionaMinValue)) {
            exceptionalScore--;
            updateUI();
        }
    }
    
    // inner classes
    
    class ActionAddExActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            incrementExceptional();
        }
    }
    
    class ActionMinusExActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            decrementExceptional();
        }        
    }    
}
