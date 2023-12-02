import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddScreen extends JFrame {
    /** Attrubute for the Name text field */ private JTextField txtName;
    /** Attrubute for the Price text field */private JTextField Price;
    /** Attrubute for the Ingredients text area */private JTextArea  txtIngredients; 
    /**Attribute for the save button */ private JButton     cmdSave;
    private JButton     cmdClose;
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private JFrame      frame;
    
    /**
    * A screen that pops up to let you add a recipe
    */
    public AddScreen(){
        createForm();
        pnlCommand = new JPanel();
        pnlCommand.setBackground (Color.GRAY);
        pnlDisplay = new JPanel();
        pnlDisplay.setBackground(Color.GRAY);
        
        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(20);
        txtName.setBackground (Color.yellow);
        pnlDisplay.add(txtName);

        pnlDisplay.add(new JLabel("Ingredients:"));
        txtIngredients = new JTextArea();
        txtIngredients.setBackground (Color.yellow);
        pnlDisplay.add(txtIngredients);

        pnlDisplay.add(new JLabel("Price"));
        Price = new JTextField(20);
        Price.setBackground(Color.yellow);
        pnlDisplay.add(Price);

        pnlDisplay.setLayout(new GridLayout(3,4));
        cmdSave      = new JButton("Save");
        cmdSave.setBackground (Color.yellow);
        cmdClose   = new JButton("Return");
        cmdClose.setBackground(Color.yellow);
        cmdSave.addActionListener(new add_stuff());
        cmdClose.addActionListener(new close_listener());
        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
    }
    /**
    *Creates a window for the AddScreen
    */   
    private void createForm() {

        frame = new JFrame();
        frame.setBackground(Color.GRAY);
        frame.setLayout(new GridLayout(2, 1));
        frame.setTitle("Creating a new recipe");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     * Allows a recipe to be added
     */
    public void add_recipe() {
        String x = "";
        String i = " ";
        int pe = 0;
        try {
            if (txtName.getText().equals("") && txtIngredients.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need your ingredients and a Recipe Name!!!");
            } else if (txtIngredients.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need your ingredients!!!");
            } else if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You need a name!!!");
            } else if (Price.getText().equals("")) {
                   JOptionPane.showMessageDialog(null, "You need a price on this");
                
            }
            else {
                i = txtName.getText();
                x = txtIngredients.getText();
                pe = Integer.parseInt(Price.getText());
                FileWriter fw = new FileWriter("Recipes.txt", true); // Set true for append mode
                BufferedWriter bw = new BufferedWriter(fw);
                fw.write(i + "-" + x + "-" + pe);
                bw.newLine();
                bw.close();
             
            }

        } catch (IOException e) {
            // TODO: handle exception
        }
        catch(NumberFormatException t){
            JOptionPane.showMessageDialog(null,"Price is not a number!!!");
        }
        frame.setVisible(false);

    }

    private class add_stuff implements ActionListener {
    /**
     * Allows a recipe to be added when a button is clicked
     */
        public void actionPerformed(ActionEvent e) {
            add_recipe();
        }
    }
    private class close_listener implements ActionListener
{
    /**Exits the current screen */
    public void actionPerformed(ActionEvent e)
    {
        frame.setVisible(false);
    }
}

}