package Mkv;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class showRules {
	JTable ruleTable ;
	JScrollPane ruleTablePane;
	String[] columnNames = {"Rule Number" , "First","Second","Terminal?"};
	JFrame frame;
	JLabel mainLabel;
	JButton closeButton;
	JList ruleListTable;
	
	showRules()
	{
		frame = new JFrame("Rules");
		frame.setPreferredSize(new Dimension(320,320));
		mainLabel = new JLabel();
		mainLabel.setBounds(0, 0, 320, 320);
		frame.add(mainLabel);
		ArrayList<String> rows = new ArrayList<String>();
		rows.add(MarkovMain.dictionary);
		for( rule r : MarkovMain.ruleList)
		{
			rows.add(r.getA() + "->" + r.getB());
		}
		ruleListTable = new JList(rows.toArray());
		ruleTablePane = new JScrollPane(ruleListTable,
	    		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  
		ruleTablePane.setBounds(10,20,150,200);
		mainLabel.add(ruleTablePane);
		ruleTablePane.setVisible(true);
		closeButton = new JButton("Close");
		closeButton.setBounds(50,240,100,30);
		mainLabel.add(closeButton);
		closeButton.addActionListener(new ActionListener()
		        {
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		frame.dispose();
		        	}
		 });
		        	    
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	

}
