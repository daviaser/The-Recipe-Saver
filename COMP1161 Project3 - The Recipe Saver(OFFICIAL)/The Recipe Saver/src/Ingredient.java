import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ingredient implements Comparable<Ingredient>
{

    protected String ingredientName; 
    private double ingredientCost;
    protected String ingredientFileName = "ingredientFile.dat";
	
	/**
	 * Represents an Ingredient
	 * @param ingredientName
	 * @param ingredientCost
	 */
	public Ingredient(String ingredientName,double ingredientCost)
	{
		this.ingredientName = ingredientName;
		this.ingredientCost = ingredientCost;
	}
 	/** 
     * @return String- Name
     */
    public String getIngredientName() 
	{
		return ingredientName;
	}
	/** 
     * @return double- ingredientCost
     */
	public double getIngredientCost() 
	{
		return ingredientCost;
	}
	/** 
     * Creates Header
     */
    public static String getIHeader()
    {
        String returnval = "ID\tIngredientName\tIngredientCost";
        returnval+="\n---------------------------------";
        return returnval;

    }
	/** 
     * @return String
	 * returnn ingrdient as a string
     */
    public String toString()
    {
        return(getIngredientName()+"\t"+getIngredientCost());
    }

    
    /*public static void resetId()
    {
       nextId=0;   
    }*/
    /**
	 * Sorts ingredients by cost
	 */
    public int compareTo(Ingredient other)
    {
		final double costDiff = this.getIngredientCost() - other.getIngredientCost();  
		if (costDiff > 0) return 1;
		if (costDiff < 0) return -1;
		return 0;
    }

	//Find and return an ingredient in list of ingredients
	/**
	 * Finds ingredient in list
	 */
	public Ingredient findIngredient(ArrayList<Ingredient> ingredientList, String ingredientName) //put in ingredient class
	{
		Ingredient thisIngredient = null;
		for (Ingredient chkIngredient : ingredientList) {
			if (ingredientName.equals(chkIngredient.ingredientName)) {
				thisIngredient = chkIngredient;
				break;
			}
		}
		return thisIngredient;
	}
	
/** 
 * @return double 
 * returns ingredientCost
 */
	public double getPriceOfIngredient(String ingredName) throws Exception //Put in ingredient class
	{
		//try {
		BufferedReader reader = new BufferedReader(new FileReader(ingredientFileName));
		String lineRead = reader.readLine();
		double ingredCost = 0.0;
		
		while(lineRead != null){
			String [] ingredFields = lineRead.split("\\|");
			/*
			Ingredient is an object with an ingredient name and cost.
			*/
			String thisIngredName = (ingredFields[0]);
			if (thisIngredName.equals(ingredName)) {
				ingredCost = Double.parseDouble(ingredFields[0]);
				break;
			}
			lineRead = reader.readLine();
		}
		reader.close();
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
		return ingredCost;
	}
	/**
	 * 
	 * @return 
	 * @throws Exception
	 */
    public ArrayList<Ingredient> getAllIngredients() throws Exception //******Put in ingredient class
	{
		//Declare list of ingredient to return
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

		//Setup reader for ingredient file
        BufferedReader reader = new BufferedReader(new FileReader(ingredientFileName));
 
		//Read first ingredient record in file
		//Ingredient record is a field delimited string of format:
		//	ingredientName|ingredientCost
		
		String lineRead = reader.readLine();
		
        while(lineRead != null){
			//Extract fields in ingredient record into array of strings
            String [] ingredientFields = lineRead.split("\\|");

			//Get ingredient name and cost
			String thisIngredientName = ingredientFields[0];
			double thisIngredientCost = Double.parseDouble(ingredientFields[1]);

			//Create ingredient object and add it to list of ingredients
			Ingredient thisIngredient = new Ingredient(thisIngredientName, thisIngredientCost);
			ingredientList.add(thisIngredient);
		
			//Get next ingredient record until end of file (lineRead = null)
            lineRead = reader.readLine();
        }
		//Close file and return list of ingredients
		reader.close();
		
		return ingredientList;
    }
	/**
	 * @param ingredientList
	 * @throws IOException
	 * Saves ingredients to file
	 */
	public void saveAllIngredients(ArrayList<Ingredient> ingredientList) throws IOException { //Put in Ingredient class

		//Request a file output object having the methods to write to a file
        FileOutputStream fout = new FileOutputStream(ingredientFileName);

		//Write each ingredient in array list to file.
		for (Ingredient thisIngredient : ingredientList) {
            fout.write(thisIngredient.toString().getBytes());
            fout.write('\n'); //put newline character after line written
        }
        System.out.println("DONE");
    }

}