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

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.lang.invoke.VarHandle; 
public class DeleteScreen {
     /** 
     * Attrubute for the Name text field
     */
    private JTextField txtName;
    private JButton delteButton;
    private JButton cancelButton;
    private JButton returnButton;
    private ArrayList<Recipe> rp;
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private JFrame      frame;
    
/**
* A screen that pops up to let you delete a recipe
*/

public DeleteScreen(){
    createForm();

    rp = loadRecipes("Recipes.txt");

    pnlCommand = new JPanel();
    pnlCommand.setBackground (Color.GRAY);
    pnlDisplay = new JPanel();
    pnlDisplay.setBackground (Color.GRAY);
    frame.setVisible(true);


    pnlDisplay.add(new JLabel("Name:"));
    txtName = new JTextField(20);
    txtName.setBackground(Color.yellow);
    pnlDisplay.add(txtName);
    
   
    cancelButton = new JButton("Cancel");
    cancelButton.setBackground (Color.yellow);
    delteButton = new JButton("Delete");
    delteButton.setBackground (Color.yellow);
    returnButton = new JButton("Return");
    returnButton.setBackground (Color.yellow);
    returnButton.addActionListener(new return_listener());
    delteButton.addActionListener(new delete_rr());
    pnlCommand.add(delteButton);
    pnlCommand.add(cancelButton);
    pnlCommand.add(returnButton);
    frame.add(pnlDisplay, BorderLayout.CENTER);
    frame.add(pnlCommand, BorderLayout.SOUTH);
    frame.pack();



}
/**
    *Creates a window for the DeleteScreen
    */   
public void createForm() {
    frame = new JFrame();
    frame.setTitle("Delete");
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}



/**
 * Allows recipe to be deleted
 * @param t
 */
public void delete_r(String t) {
    String x = "";
    String rn = "";
    int pe = 0;

    for (Recipe i : rp) {

        if (i.getRecipeName().equals(t)) {
            rp.remove(i);
           
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

private class return_listener implements ActionListener {
    /**
     * Exits screen when clicked 
     */
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
    }
}

private class delete_rr implements ActionListener {
    /** 
     * Allows recipe to be deleted when button is clicked
    */
    public void actionPerformed(ActionEvent e) {
        String x = "";
        
        try {
            if (txtName.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "You need your recipe Name!!!");
            }  else {
                x = txtName.getText();
               
                delete_r(x);
                frame.setVisible(false);
            }

        } catch (Exception f) {
           
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
