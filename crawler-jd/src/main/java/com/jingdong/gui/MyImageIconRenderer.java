package com.jingdong.gui;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class MyImageIconRenderer extends JLabel implements TableCellRenderer{
	Border unSelectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;
	private String imagePath = null;
	
	public MyImageIconRenderer() {
		
	}
	
    public MyImageIconRenderer(String imagePath,boolean isBordered) {
    	
    	this.imagePath = imagePath;
    	this.isBordered = isBordered;
    	this.setOpaque(true);
    	System.out.println("imagePath = "+this.imagePath);
    }
    
	@Override
	public Component getTableCellRendererComponent(JTable table,
			                                      Object imageIcon, 
			                                      boolean isSelected, 
			                                      boolean hasFocus,
			                                      int row, int column) {
		
		ImageIcon icon = (ImageIcon) imageIcon;
		if(null==icon) {
			System.out.println("get default icon is null");
			icon = new ImageIcon(this.imagePath);
		}else {
			System.out.println("icon is not null");
		}
		//ImageIcon icon = createImageIcon("images/middle.gif","testImage");
		if(null!=icon) {
		   this.setIcon(icon);
		}
		if(isBordered) {
			if(null==selectedBorder) {
				selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, 
						                       table.getSelectionBackground());
				
			}
			this.setBorder(selectedBorder);
		}else {
			if(null==unSelectedBorder) {
				unSelectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, 
						           table.getBackground());
			}
			this.setBorder(unSelectedBorder);
		}
		this.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}
}
