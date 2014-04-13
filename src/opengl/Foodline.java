package mftoth.restaurantsim.ogl;
import mftoth.restaurantsim.logic.*;
import java.util.ArrayList;

public class Foodline{

	private final static int CAPACITY = 14;

	private ArrayList<GLCustomer> customers;
	private ArrayList<GLTile> linetiles; 
	private boolean beingHelped = false;

	public Foodline(){

		linetiles = new ArrayList<GLTile>();

		linetiles.add(new GLTile(8,17));
		linetiles.add(new GLTile(9,17));
		linetiles.add(new GLTile(10,17));
		linetiles.add(new GLTile(11,17));
		linetiles.add(new GLTile(12,17));
		linetiles.add(new GLTile(13,17));
		linetiles.add(new GLTile(14,17));
		linetiles.add(new GLTile(15,17));
		linetiles.add(new GLTile(16,17));
		linetiles.add(new GLTile(17,17));
		linetiles.add(new GLTile(18,17));
		linetiles.add(new GLTile(19,17));
		linetiles.add(new GLTile(20,17));
		linetiles.add(new GLTile(21,17));
		linetiles.add(new GLTile(22,17));



		customers = new ArrayList<GLCustomer>();
	}

	public boolean add(GLCustomer glcust){

		//If  not at max capacity, add customer
		if(customers.size()<CAPACITY){
			customers.add(glcust);

			//get the tile for the spot in the line
			GLTile linespot = linetiles.get(customers.size()-1);

			glcust.setPath(linespot.getX(), linespot.getY());
			//customer.setDirection(FigureDirection.LEFT);
			return true;

		}else{
			return false;
		}

	}

	public boolean hasNext(){
		if(customers.size()>0){
			return true;
		}else{
			return false;
		}
	}

	public Customer getNext(){
		
		if(customers.size()>0){
			beingHelped = true;
			//System.out.println("Customer line size: " + customers.size());
			//Move each customer up one spot
		
			
			//get the first customer
			GLCustomer nextcust = customers.get(0);


			
			return nextcust.getLogical();
		}else{
			return null;
		}
	}

	public boolean isHelped(){
		return beingHelped;
	}

	public void next(){

		//remove the first customer
		if(customers.size()>0){
			customers.remove(0);
			beingHelped = false;

			//Move the rest of the customers forward
			for(int i=0; i<customers.size(); i++){
				GLCustomer glcust = customers.get(i);
				GLTile linespot = linetiles.get(i);
				//System.out.println("linespot x: " + linespot.getX() + " linespot y: " + linespot.getY());
				glcust.setPath(linespot.getX(), linespot.getY());
			}

		}
	}


}