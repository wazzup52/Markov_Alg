package Mkv;

import java.util.List;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;

public class MarkovMain {
	JFrame frame ;
	JTextField newRuleField, inputStringField,modifyExistingRuleField , pathField , dictionaryField;
	JLabel newRuleLabel , inputStringLabel , mainLabel ,modifyExistingRuleLabel , pathLabel ;
	JButton newRuleBtn , deleteRulesBtn ,inputStringBtn , showRulesBtn  ,modifyExistingRuleBtn , pathBtn , dictionaryBtn;
	JTextPane resultText;
	//Markov algoritm declarations
	static List <rule> ruleList   = new Vector<rule>();
	static String dictionary = new String();
	
	public static void getRules(String path)
	{
		ruleList = new Vector<rule>();
        String fileName = new String(path);
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            if((line = bufferedReader.readLine()) != null)
					dictionary = line;
            while((line = bufferedReader.readLine()) != null) 
            {
            		String[] parts = line.split(" -> ");
            		addRule(new rule(parts[0],parts[1]));
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) 
        {
            System.out.println(  "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) 
        { 
        	System.out.println(  "Error reading file '"+ fileName + "'");                  
        }
	}
	public static void addRule(rule newRule)
	{
		ruleList.add(newRule);
	}
	public static void modifyExistingRule(int index , rule newRule)
	{
		ruleList.add(index, newRule);
		ruleList.remove(index+1);
	}
	
	MarkovMain()
	{
		frame = new JFrame("Markov algorithm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(320,480));
		
		
		//GUI
		mainLabel = new JLabel();
		mainLabel.setBounds(0, 0, 320, 480);
		frame.add(mainLabel);
		dictionaryField = new JTextField();
		dictionaryField.setBounds(10,10,150, 25);
		mainLabel.add(dictionaryField);
		dictionaryBtn = new JButton("Add dictionary");
		dictionaryBtn.setBounds(165,10,125, 25);
		mainLabel.add(dictionaryBtn);
		dictionaryBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				String d = dictionaryField.getText();
				if(!d.equals(""))
				{
					dictionary = d;
					JOptionPane.showMessageDialog(null, "Vocabularr adaugat cu succes");
					dictionaryField.setText("");
				}
				else JOptionPane.showMessageDialog(null, "Scrieti un vocabular");
			}
		});		
		{
			newRuleLabel = new JLabel("Introduceti regula noua de forma a->b.");
			newRuleLabel.setBounds(10, 50, 300, 25);
			mainLabel.add(newRuleLabel);
			
			newRuleField = new JTextField();
			newRuleField.setBounds(20, 70, 200, 25);
			mainLabel.add(newRuleField);
			
			newRuleBtn = new JButton("Add rule");
			newRuleBtn.setBounds(20, 100, 150, 25);
			mainLabel.add(newRuleBtn);
			newRuleBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String s = newRuleField.getText();
					if(s.contains("->"))
					{
						String[] sParts = s.split(" -> ");
						MarkovMain.addRule(new rule(sParts[0], sParts[1]));
						JOptionPane.showMessageDialog(null,"Regula adaugata cu succes");
						newRuleField.setText("");
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null,"Formatul regulei este gresit");
						newRuleField.setText("");
					}
				}
			});
		}
		{
			deleteRulesBtn = new JButton("Delete rules");
			deleteRulesBtn.setBounds(180, 100, 120, 25);
			mainLabel.add(deleteRulesBtn);
			deleteRulesBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					ruleList = new Vector<rule>();
				}
			});
		}
		{
			showRulesBtn = new JButton("Show Rules");
			showRulesBtn.setBounds(20,130,150,25);
			mainLabel.add(showRulesBtn);
			showRulesBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					showRules sR = new showRules();
				}
			});
		}
		{
			modifyExistingRuleLabel = new JLabel("Introduceti de forma : x/a->b");
			modifyExistingRuleLabel.setBounds(10, 160, 300, 25);
			mainLabel.add(modifyExistingRuleLabel);
			
			modifyExistingRuleField = new JTextField();
			modifyExistingRuleField.setBounds(20, 190, 200, 25);
			mainLabel.add(modifyExistingRuleField);
			
			modifyExistingRuleBtn = new JButton("Modify rule");
			modifyExistingRuleBtn.setBounds(20, 220, 150, 25);
			mainLabel.add(modifyExistingRuleBtn);
			modifyExistingRuleBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String s = modifyExistingRuleField.getText();
					if(s.contains("/"))
					{
						String[] sParts1 = s.split(" / ");
						int index = Integer.parseInt(sParts1[0]);
						boolean t = Boolean.parseBoolean(sParts1[2]);
						if(sParts1[1].contains(" -> "))
						{
							String[] sParts2 = sParts1[1].split("->");
							MarkovMain.modifyExistingRule(index-1, new rule(sParts2[0],sParts2[1]));
							JOptionPane.showMessageDialog(null,"Regula modificata cu succes");
							modifyExistingRuleField.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Formatul regulei este gresit");
							modifyExistingRuleField.setText("");
						}
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null,"Formatul regulei este gresit");
						modifyExistingRuleField.setText("");
					}
				}
			});
		}
		{
			inputStringLabel = new JLabel("Introduceti string-ul pentru prelucrat");
			inputStringLabel.setBounds(10, 250, 250, 25);
			mainLabel.add(inputStringLabel);
			
			inputStringField = new JTextField();
			inputStringField.setBounds(20, 280, 200, 25);
			mainLabel.add(inputStringField);
			
			inputStringBtn = new JButton("Rewrite");
			inputStringBtn.setBounds(20, 310, 150, 25);
			mainLabel.add(inputStringBtn);
			inputStringBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String s = inputStringField.getText();
					if(!s.equals(""))
					{
						if(MarkovMain.checkIfWordOverDictionary(s))
						{	
							s = MarkovMain.markovAlg(s);
							resultText = new JTextPane();
							resultText.setText(s);
							resultText.setEditable(false);
							inputStringField.setText("");
							resultText.setSize(new Dimension(300, 300));
							resultText.setPreferredSize(new Dimension(300, 200));
					
							JOptionPane.showMessageDialog(null,resultText,"Result",JOptionPane.INFORMATION_MESSAGE);
						
						}
						else JOptionPane.showMessageDialog(null, "Cuvantul introdus nu este cuvant peste dictionarul curent");
						
					}
					else JOptionPane.showMessageDialog(null, "Introduceti un string");
					
				}
			});
		}
		{
			pathLabel = new JLabel("Introduceti calea catre fisier");
			pathLabel.setBounds(10, 340, 250, 25);
			mainLabel.add(pathLabel);
			
			pathField = new JTextField();
			pathField.setBounds(20, 370, 200, 25);
			mainLabel.add(pathField);
			
			pathBtn = new JButton("Get rules from file");
			pathBtn.setBounds(20,400, 150, 25);
			mainLabel.add(pathBtn);
			pathBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String s = pathField.getText();
					if(!s.equals(""))
					{
						MarkovMain.getRules(s);
						JOptionPane.showMessageDialog(null, "Reguli importate");
						
					}
					else JOptionPane.showMessageDialog(null, "Introduceti un string");
				}
			});
		}

		//
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

	public static boolean checkIfWordOverDictionary(String s)
	{
		boolean ok = false;
		for(char c : s.toCharArray())
		{
			ok = false;
			for (char cc : dictionary.toCharArray())
			{
				if(c == cc)
					ok = true;
			}
			if(!ok)
				return false;
		}
		return true;
	}
	public static String markovAlg(String s)
	{
		for (rule rule : ruleList)
		{
		    if (rule.matches(s))
		    	{
		    		String temp = rule.applyRule(s);
		    		//System.out.println(temp);
		    		if(rule.isTerminal()) 
		    			return temp;
		    		else
		    			return markovAlg(temp );
		    	}
		  }
		return s;
	}
	
	public static void main(String[] args)
	{
		MarkovMain mM = new MarkovMain();
	}
	
}
