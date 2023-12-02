import java.util.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.w3c.dom.html.HTMLAreaElement;

import java.io.*;
import java.util.*;

public class Recipe 
{
    private String recipeName;
    private String ingredients;
    private int Price;    
    
    
    /**
     * Represents a Recipe
     * @param ingredients
     * @param recipeName
     * @param Price
     */
    public Recipe(String ingredients,  String recipeName , int Price)
    {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.Price = Price;

    }

    
    /** 
     * @return int - Price 
     */
    public int getprice() {
        return Price;
    }
        

    
    /** 
     * @return String
     */
    public String getRecipeName()
    {
        return recipeName;
    }

    
    /** 
     * @return String
     */
    public String geting() {
        return ingredients;
    }



   
    /**
     * @return
     * @throws Exception
     */
    

}