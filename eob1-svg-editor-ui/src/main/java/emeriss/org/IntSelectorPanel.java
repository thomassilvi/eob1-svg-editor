package emeriss.org;

import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class IntSelectorPanel extends Panel {

    protected TextField tfValue;
    protected int value;
    protected int nullValue, minValue, maxValue;
    
    public IntSelectorPanel(int nullValue, int minValue, int maxValue) {
        super();
        this.nullValue = nullValue;
        this.minValue = minValue;
        value = nullValue;
        tfValue = new TextField("",1);
        tfValue.setEditable(false);
        setMaxValue(maxValue);
        add(tfValue);
        Button plus = new Button("+");
        plus.addActionListener(new ActionAddActionListener());
        plus.setFont(Eob1SvgEditorFonts.FONT_1);
        add(plus);
        Button minus = new Button("-");
        minus.addActionListener(new ActionMinusActionListener());
        minus.setFont(Eob1SvgEditorFonts.FONT_1);
        add(minus);
    }
    
    public void setMaxValue(int value) {
        this.maxValue = value;
        int nColumns=1;
        if (maxValue>99) {
            nColumns=3;
        } else if (maxValue>9) {
            nColumns=2;
        }
        tfValue.setColumns(nColumns);
    }
    
    public void setValue(int v) {
        value = v;
        updateUI();
    }
    
    public int getValue() {
        return value;
    }
    
    protected void updateUI() {
        tfValue.setText(Integer.toString(value));
    }
    
    protected void increment() {
        if (value < maxValue) {
            value++;
            updateUI();
        }
    }
    
    protected void decrement() {
        if (value > minValue) {
            value--;
            updateUI();
        }
    }
    
    @Override
    public String toString() {
        String result;
        result = "nullValue: " + nullValue;
        result = result + " minValue: " + minValue;
        result = result + " maxValue: " + maxValue;
        return result;
    }
    
    // inner classes
    
    class ActionAddActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            increment();
        }
    }
    
    class ActionMinusActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            decrement();
        }        
    }
    
}

