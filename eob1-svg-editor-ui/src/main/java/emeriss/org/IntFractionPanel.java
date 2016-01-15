package emeriss.org;

import java.awt.Label;
import java.awt.TextField;

@SuppressWarnings("serial")
public class IntFractionPanel extends IntSelectorPanel {

    protected TextField tfDenominator;
    protected int denominator;
    
    public IntFractionPanel(int nullValue, int minValue, int maxValue) {
            super(nullValue, minValue, maxValue);
            denominator = maxValue;
            
            Label lTmp = new Label("/");
            lTmp.setFont(Eob1SvgEditorFonts.FONT_1);
            add(lTmp);
            
            tfDenominator = new TextField("",3);
            tfDenominator.setEditable(false);
            add(tfDenominator);
    }
    
    public void setDenominator (int value) {
        denominator = value;
        int nColumns=1;
        if (denominator>99) {
            nColumns=3;
        } else if (denominator>9) {
            nColumns=2;
        }
        tfDenominator.setColumns(nColumns);
        tfDenominator.setText(Integer.toString(denominator));
        super.setMaxValue(denominator);
    }

}
