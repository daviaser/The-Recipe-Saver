import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.lang.invoke.VarHandle; 
public class EditScreen extends JFrame {
     /** 
     * Attrubute for the Name text field
     */
    private JTextField txtName;
     /** 
     * Attrubute for the Price text field
     */
    private JTextField Price;
     /** 
     * Attrubute for the Ingredients text area
     */    
    private JTextArea txtIngredients;
    private JPanel      pnlDisplay;
    private JPanel      pnlCommand;
    private JFrame frame;
    private ArrayList<Recipe> rp;
    private JButton returnButton;
    private JButton edit_button;
 /**
*Represents a  EditScreen*/

    public EditScreen(){
    createForm();

    rp = loadRecipes("Recipes.txt");

    pnlDisplay = new JPanel(new GridLayout(3, ABORT));
    pnlDisplay.setBackground (Color.GRAY);
    pnlCommand = new JPanel();
    pnlCommand.setBackground(Color.GRAY);

    pnlDisplay.add(new JLabel("Name:"));
    txtName = new JTextField(20);
    txtName.setBackground(Color.yellow);
    pnlDisplay.add(txtName);

    pnlDisplay.add(new JLabel("Ingredients:"));
    txtIngredients = new JTextArea();
    txtIngredients.setBackground(Color.yellow);
    pnlDisplay.add(txtIngredients);

    pnlDisplay.add(new JLabel("Price"));
    Price = new JTextField(20);
    Price.setBackground(Color.yellow);
    pnlDisplay.add(Price);




   
    returnButton = new JButton("Return");
    edit_button = new JButton("Edit Recipe");
    edit_button.setBackground(Color.yellow);
    returnButton.setBackground (Color.yellow);
    pnlCommand.add(returnButton);
    pnlCommand.add(edit_button);
    frame.add(pnlDisplay, BorderLayout.NORTH);
    frame.add(pnlCommand, BorderLayout.SOUTH);
    frame.setVisible(true);
   
    
   
    
   returnButton.addActionListener(new return_listener());
   edit_button.addActionListener(new edit_rr());
    
    frame.pack();


}
/**
    *Creates a window for the EditScreen
    */   
public void createForm() {
    frame = new JFrame();
    frame.setTitle("Edit");
    frame.setSize(500,500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

private class return_listener implements ActionListener {
        /**
        * exits screen when button is clicked on 
        */   
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
    }
}

        /**
        * Allows a recipe to be edited
        */   
public void edit_r(Recipe new_r) {
    String x = "";
    String rn = "";
    int pe = 0;
    
    for (Recipe i : rp) {

        if (i.getRecipeName().equals(new_r.getRecipeName())) {
            rp.remove(i);
            rp.add(new_r);
        }

    }


    try {
        FileWriter tw = new FileWriter("Recipes.txt", false);
        tw.write("");
        tw.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } // write an empty string to the file

    for (Recipe r : rp) {
        x = r.getRecipeName();
        rn = r.geting();
        pe = r.getprice();

        try {
            FileWriter fw = new FileWriter("Recipes.txt", true); // Set true for append mode
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write(x + "-" + rn + "-" + pe);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            // TODO: handle exception
        }

    }
    
    MainMenu mm = new MainMenu();
   

}


private class edit_rr implements ActionListener {
    /**
     *Allows a recipe to be edited when button clicked
     */
    public void actionPerformed(ActionEvent e) {
        String x = "";
        String i = "";
        int pe = 0;
        
        try {
            if (txtName.getText().equals("") && txtIngredients.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need your ingredients and a Name!!!");
            } else if (txtIngredients.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need your ingredients!!!");
            } else if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need a name!!!");
            } else if (Price.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need a price on this");

            } else {
                x = txtName.getText();
                i = txtIngredients.getText();
                pe = Integer.parseInt(Price.getText());
               
                edit_r(new Recipe(i,x, pe));
                frame.setVisible(false);
            }

        }  catch (NumberFormatException t) {
            JOptionPane.showMessageDialog(null, "Price is not a number!!!");
        }

        
        
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
