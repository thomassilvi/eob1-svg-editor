package emeriss.org;

import java.awt.GridBagConstraints;

public class GridBagTools {

    private GridBagTools() {
    }

    public static GridBagConstraints getGridBagConstraint(int gridx, int gridy) {
        GridBagConstraints result = new GridBagConstraints();
        result.gridx = gridx;
        result.gridy = gridy;
        return result;
    }
    
}
