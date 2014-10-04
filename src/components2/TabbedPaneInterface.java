
package components2;


import grid.grid;
import controller.Controller;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.GridLayout;

public class TabbedPaneInterface extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int selectedTabIndex;
	Controller control;
	grid current;
	JTabbedPane tabbedPane;
	grid ara;
	grid rsrma;
	grid rsrmap; 
	
    public TabbedPaneInterface(Controller c) {
        super(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();
        control = c;
     
        //change listeners used for tabs when changed
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
              JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
              //grab the current index of teh tab
              selectedTabIndex = sourceTabbedPane.getSelectedIndex();
              //set the current grid to the grid that is now chosen
              current = getCurrGrid();
              //pass the current grid to the controller
              control.setGrid(current);
              //clear the current grid
              current.clear();
            }
         };
         //add change listener to tab
        tabbedPane.addChangeListener(changeListener);
        
        //create individual grids for each of the implemented 
        //algorithms
        ara = new grid("ARA",450,500);
        ara.setLocation(0,0);
        tabbedPane.addTab("ARA",ara);
        
        rsrma = new grid("RSRMA",450,500);
        ara.setLocation(0,0);
        tabbedPane.addTab("RSRMA",rsrma);
        
        
        rsrmap = new grid("RSRMA'",450,500);
        ara.setLocation(0,0);
        tabbedPane.addTab("RSRMA'",rsrmap);
        
             
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        current = ara;
        //The following line enables to use scrolling tabs.
     
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    //return the grid that is currently selected
    public grid getCurrGrid(){
    	if(tabbedPane.getSelectedIndex()==0)
    		return ara;
    	else if(tabbedPane.getSelectedIndex()==1){
    		return rsrma;
    	}
    	else 
    		return rsrmap;
    }
    
    
    

}
