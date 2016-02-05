package emeriss.org;

import java.awt.TextField;

@SuppressWarnings("serial")
public class IntFractionPanel extends IntSelectorPanel {

    protected TextField tfDenominator;
    protected int denominator;
    
    public IntFractionPanel(int nullValue, int minValue, int maxValue) {
            super(nullValue, minValue, maxValue);
            setDenominator(maxValue);
    }
    
    public void setDenominator (int value) {
        denominator = value;

        int nColumns=1;
        if (denominator>99) {
            nColumns=3;
        } else if (denominator>9) {
            nColumns=2;
        }
        nColumns = nColumns * 2 + 1;
        tfValue.setColumns(nColumns);
        this.maxValue = denominator;
        updateUI();
    }

    @Override
    protected void updateUI() {
        tfValue.setText(Integer.toString(value) + " / " + Integer.toString(denominator));
    }

    public int getDenominator() {
        return denominator;
    }

}




