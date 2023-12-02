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
public class ShowRecipeScreen extends JFrame{
    private  JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JButton editButton;
    private JButton deleteButton;
    private JButton sortByPrice;
    private JButton sortByName;
    private JButton exit;
    private JFrame frame;
    public ArrayList<Recipe> list_r;
    private JPanel  pnlCommand;
    private JPanel   pnlDisplay;
    
    /**
    *Represents a  ShowRecipeScreen
    */
    public ShowRecipeScreen(){
        createForm();
        list_r = loadPersons("Recipes.txt");
       
        String column_names[]= {"Name","List of Ingredients","Cost"};
        model = new DefaultTableModel(column_names, 0);
        for (Recipe i : list_r) {
            String name = i.getRecipeName();
            String ingre = i.geting();
            int p = i.getprice();
            String x = "" + p;
            String[] item = { name, ingre, x};
            model.addRow(item);
        }
        table=new JTable(model);
        table.setBackground(Color.yellow);
       
        
        JScrollPane scrollPane= new  JScrollPane(table);
        pnlCommand = new JPanel(new GridLayout(0, 1));
        pnlCommand.add(scrollPane,BorderLayout.CENTER);
    
        JButton editButton = new JButton("Edit Recipe");
        editButton.addActionListener(new edit_listener());
        editButton.setBackground (Color.yellow);
        JButton sortByName = new JButton("Sort by name");
        sortByName.setBackground(Color.yellow);
        sortByName.addActionListener(new r_name());
        JButton sortByPrice = new JButton("Sort by price");
        sortByPrice.addActionListener(new sort_price1());
        sortByPrice.setBackground (Color.yellow);
        JButton deleteButton= new JButton("Delete");
        deleteButton.addActionListener(new del_listener());
        deleteButton.setBackground (Color.yellow);
        JButton  exit= new JButton("Return");
        exit.addActionListener(new exit_listener());
        exit.setBackground (Color.yellow);
        pnlDisplay = new JPanel();
        pnlDisplay.setBackground (Color.GRAY);
       pnlDisplay.add(editButton);
        pnlDisplay.add(sortByName);
        pnlDisplay.add(sortByPrice);
        pnlDisplay.add(deleteButton);
        pnlDisplay.add(exit);
        frame.add(pnlDisplay);
        frame.add(pnlCommand);

        frame.pack();

    }
    /**
    *Creates a window for the ShowRecipeScreen
    */   
    private void createForm() {
    
    frame = new JFrame();
    frame.setLayout(new GridLayout(2,1));
    frame.setTitle("Show Menu");
    frame.setSize(500,500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);



    }
    private class edit_listener implements ActionListener{
         /**
        * Opens EditScreen when button is clicked on 
        */   
        public void actionPerformed(ActionEvent e){
            EditScreen es = new EditScreen();
        }
    
        
    }
    private class del_listener implements ActionListener{
        /**
        * Opens DeleteScreen when button clicked on 
        */   
        public void actionPerformed(ActionEvent e){
            DeleteScreen d = new DeleteScreen();
        }
    }

    private class exit_listener implements ActionListener {
        /**
        * exits ShowRecipescreeen when button is clicked on 
        */   
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
        }
    }

    public class sort_price implements Comparator<Recipe> {
         /**
        * Sorts recipes by price
        */   
        public int compare(Recipe o1, Recipe o2) {
            if (o1.getprice() > o2.getprice()) {
                return 1;
            } else if (o1.getprice() < o2.getprice()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    private class sort_price1 implements ActionListener {
        /**
        * Sorts recipes by price when button clicked on 
        */   
        public void actionPerformed(ActionEvent e) {
            Collections.sort(list_r, new sort_price());
            model.setRowCount(0);
            for (Recipe i : list_r) {
                String name = i.getRecipeName();
                String ingre = i.geting();
                int p = i.getprice();
                String x = "" + p;
                String[] item = { name, ingre, x };
                model.addRow(item);
            }

        }
    }
    

    public class sort_name implements Comparator<Recipe> {
         /**
        * Sorts recipes by name  
        */   
        public int compare(Recipe o1, Recipe o2) {
            return o1.getRecipeName().compareTo(o2.getRecipeName());
        }
    }

    private class r_name implements ActionListener {
        /**
        * Sorts recipes by name when button is clicked on 
        */   
        public void actionPerformed(ActionEvent e) {
            Collections.sort(list_r, new sort_name());
            model.setRowCount(0);
            for (Recipe i : list_r) {
                String name = i.getRecipeName();
                String ingre = i.geting();
                int p = i.getprice();
                String x = "" + p;
                String[] item = { name, ingre, x };
                model.addRow(item);
            }
            
        }
    }

    /**
     * Loads a list using data from Recipe.txt
     * @param pfile
     * @return - A Recipe List
     */
    private ArrayList<Recipe> loadPersons(String pfile) {
        Scanner pscan = null;
        ArrayList<Recipe> plist = new ArrayList<Recipe>();
        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split("-");
                String name = nextLine[0];
                String n2 = nextLine[1];
                int price = Integer.parseInt(nextLine[2]);
                Recipe rp = new Recipe(n2, name,price);
                plist.add(rp);
            }

            pscan.close();
        } catch (IOException e) {
        }

        return plist;
    }

}