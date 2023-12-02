/*Group Project for the Computing course COMP1161 at UWI,Mona created by 
Lael Brown, Davia Howard, Gabrielle James and Alexia Archer*/
/* Description
 * Welcome to the Recipe Saver! This program acts as a simple Recipe Tracker/Online Listing Program where it lets you 
 * add, store and save up 10 recipes, their ingredients and the cost of each recipe. You have the option to view 
 * these recipes any time you want. For each recipe, you can edit its name, its ingredients and its cost. 
 * You can also delete the recipe(s) you no longer need/want.
 */

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;
import java.lang.invoke.VarHandle;

public class MainMenu extends JFrame {
  
private JButton cmdShowRecipes;
private JButton cmdAddRecipes;
private JButton cmdCaculateAllRecipes;
private JButton showIngrButton;
private JButton cmdExit;
private ArrayList<Recipe> rp;
private JPanel Display;

private JFrame frame;
 /**
*Represents the MainMenu*/

public MainMenu() {
    createForm();
    rp = loadRecipes("Recipes.txt");
    Display = new JPanel();
    Display.setBackground (Color.GRAY);
    frame.add(Display,BorderLayout.CENTER);
    frame.setVisible(true);
    
    cmdShowRecipes = new JButton("Show Recipes");
    cmdShowRecipes.addActionListener(new show_listener());
    cmdShowRecipes.setBackground (Color.yellow);
    cmdCaculateAllRecipes = new JButton("Total");
    cmdCaculateAllRecipes.addActionListener(new cal_listener());
    cmdCaculateAllRecipes.setBackground (Color.yellow);
    cmdAddRecipes = new JButton("Add Recipe");
    cmdAddRecipes.addActionListener(new add_listener());
    cmdAddRecipes.setBackground (Color.yellow);
    showIngrButton = new JButton("Show Ingredients");
    showIngrButton.setBackground (Color.yellow);
    cmdExit = new JButton("Exit");
    cmdExit.addActionListener(new exit_listener());
    cmdExit.setBackground (Color.yellow);

    Display.add(cmdShowRecipes);
    Display.add(cmdAddRecipes);
    Display.add(cmdCaculateAllRecipes);
    Display.add(cmdExit);
    frame.pack();
}
    /**
    *Creates a window for the  MainMenu
    */   
public void createForm() {
    frame = new JFrame();
    frame.setTitle("Welcome to the Recipe Saver!");
    frame.setSize(500,500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
private class show_listener implements ActionListener{
     /**
    * Opens ShowRecipeScreen when button is clicked on 
    */   

    public void actionPerformed(ActionEvent e){
        ShowRecipeScreen ss = new ShowRecipeScreen();
    }

    
}
private class add_listener implements ActionListener{
     /**
    * Opens AddScreen when button is clicked on 
    */   

    public void actionPerformed(ActionEvent e){
        AddScreen a = new AddScreen();
    }
}
private class cal_listener implements ActionListener{
    /**
    * Shows total when button is clicked on 
    */   

    public void actionPerformed(ActionEvent e) {
        int total = 0;
        for (Recipe i : rp) {
            total += i.getprice();
        }
        JOptionPane.showMessageDialog(null, "Your total amount is: " + total);
      
    }
}
private class exit_listener implements ActionListener
{
    /**
     * Exits Main Menu when button is clicked
     */
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}
        /**
        * @return ArrayList<Recipe>
        * Loads recipes from an a file
        * 
        */ 
private ArrayList<Recipe> loadRecipes(String pfile) {
    Scanner pscan = null;
    ArrayList<Recipe> plist = new ArrayList<Recipe>();
    try {
        pscan = new Scanner(new File(pfile));
        while (pscan.hasNext()) {
            String[] nextLine = pscan.nextLine().split("-");
            String name = nextLine[0];
            String n2 = nextLine[1];
            int price = Integer.parseInt(nextLine[2]);
            Recipe rp = new Recipe(n2, name, price);
            plist.add(rp);
        }

        pscan.close();
    } catch (IOException e) {
    }

    return plist;
}


}