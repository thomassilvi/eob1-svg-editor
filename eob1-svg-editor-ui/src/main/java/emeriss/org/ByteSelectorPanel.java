package emeriss.org;

import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ByteSelectorPanel extends Panel {

    protected TextField tfValue;
    protected byte value;
    protected byte nullValue, minValue, maxValue;
    
    public ByteSelectorPanel(byte nullValue, byte minValue, byte maxValue) {
        super();
        this.nullValue = nullValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        value = nullValue;
        tfValue = new TextField("",2);
        tfValue.setEditable(false);
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
    
    public void setValue(byte v) {
        value = v;
        updateUI();
    }
    
    public byte getValue() {
        return value;
    }
    
    protected void updateUI() {
        tfValue.setText(Integer.toString(value));
    }
    
    protected void increment() {
        if ((value!=nullValue) && (value < maxValue)) {
            value++;
            updateUI();
        }
    }
    
    protected void decrement() {
        if ((value!=nullValue) && (value > minValue)) {
            value--;
            updateUI();
        }
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

