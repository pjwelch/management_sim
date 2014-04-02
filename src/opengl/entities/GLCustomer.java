package mftoth.restaurantsim.ogl;

import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.util.pathfinding.*;
import java.util.Random;

public class GLCustomer extends GLMoveableEntity{

	private Customer logical_customer;

	public GLCustomer(GameContainer gc, OGLMap map, StateGame sg, Customer logical_customer) throws SlickException{
		super(gc, map, sg);
		
		this.logical_customer=logical_customer;
		this.location=logical_customer.getLocation();


		//this.
		//default some values		
		x=16;
		y=432;
		
		dx=0.2;
		dy=0.2;

		
		//Set the spritesheet path. Will use default if not indicated
		setSpriteSheet("man_whiteshirt.png");
	}


	//@Override
	public void render(GUIContext gc, Graphics g){
		

		//leave this at the end
		super.render(gc,g);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta){
		sync();
		
		//update the gl destination from the customers
		
		System.out.println(isPathInProgress());
			//This should update the path when the destination is changed;
		if(!isPathInProgress() && (destination!=location || destination==Locations.RANDOM)){

			//If we arent going to the foodline, get the tile
			if(destination!=Locations.FOODLINE){
				 GLTile destTile = loc_handler.getTile(destination);
				 setPath(destTile);
			
			//Otherwise add them to the foodline object
			}else{
				sg.foodline.add(this);
				location=destination;
			}
		}


		
		
		//leave this at the end of this call. Call some parent functionality
		super.update(gc,game,delta);

		logical_customer.update();
	}

	//sync the gl and logical customers
	public void sync(){

		//get logic destination
		this.destination = logical_customer.getWaypoint();


		//set logic location according to 
		logical_customer.setLocation(this.location);
	}


	public String toString(){
		String output = "";
		output+="<Customer>\n";
		output+="|xpos (absolute): " + x + "\n";
		output+="|ypos (absolute): " + y + "\n";
		output+="|xtile          : " + map.getTileX(x) + "\n";
		output+="|ytile          : " + map.getTileY(y) + "\n";

		return output;
	}


}