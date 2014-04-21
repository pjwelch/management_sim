package mftoth.restaurantsim.ogl;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
import java.util.Random;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Set;
import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.UnicodeFont;
import org.lwjgl.input.Mouse;

public class GLScrollablePanel extends GLEntity{

	private TrueTypeFont ttfont;
	private Font font;
	private int fontSize = 15;
	private Restaurant restaurant;
	private ArrayList<String> items;
	private int itemheight;
	private int itemindex; //index of the first item;
	private int content_height;
	private int padding;
	private int item_hovered;
	private int item_selected;

	public GLScrollablePanel(Restaurant restaurant, GameContainer gc, int x, int y, int width, int height){
		super(gc,x,y,width,height, false);
		this.restaurant=restaurant;
		

		padding=5;

		itemheight=25;
		itemindex=0;
		content_height=0;
		items = new ArrayList<String>();
		//this.active = true;
		item_hovered=-1;
		item_selected=-1;

 	 	font = new Font("Verdana", Font.BOLD, fontSize);
		ttfont = new TrueTypeFont(font, false);
	}

	public void add(String item){
		items.add(item);
		content_height+=itemheight;
	}

	public void render(GUIContext gc, Graphics g){

		//int content_height;
		g.setColor(Color.green);
		g.fillRect(x-(padding/2),y-(padding/2),width+padding,height+padding);

		g.setFont(ttfont);
		
		double initx=x+padding/2;
		double inity=y+padding/2;

		int location = 0;
		for(int i=itemindex; i<items.size(); i++){

			if(location<(int)(height/itemheight)){

				String content = items.get(i);

				double rely = inity + location*itemheight;


				if(item_hovered!=-1 && location==item_hovered || item_selected==i){
					g.setColor(Color.yellow);
				}else{
					g.setColor(Color.white);
				}

				g.fillRect((float)x,(float)rely,width,itemheight);
		


				

				g.setColor(Color.black);
				g.drawString(content,(int)(initx), (int)(rely));


				location++;	
			}
		}

		// This is used to determine the distance between buttons
		//int yDif = (int)(height/(buttons.size()+1));

	   	//g.setColor(Color.white);
		//g.fillRect(x,y, width, height);

 	 }

  	public int getSelectedIndex(){
  		return item_selected;
  	}

  	public String getSelectedString(){
  		if(item_selected!=-1 && item_selected<=items.size()){
  			return items.get(item_selected);
  		}else{
  			return "";
  		}
  	}


 	public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
       

     //	Input input = gc.getInput();
      	
        //int mousex = input.getMouseX();

        scroll();
      //  int mousey = input.getMouseY();
          //Move the employee to where we left click
       /// if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        
      ///   
       // }

 	
    }

    public void scroll() {
		if(content_height>height){
		    int dWheel = Mouse.getDWheel();
		    if (dWheel < 0) {
		        //scroll down
		    	if(itemindex>0)
		    		itemindex--;

		    } else if (dWheel > 0){
		        //up
		        int max_scroll = (int)((content_height-height)/itemheight);
		        if(itemindex<max_scroll){
		        	itemindex++;
		        }

		    }
		}
	 
	}


  	public void mouseReleased(int button, int x, int y){
  		
  		
  		//mouseDown=false; //change flag back to false after click is done
  	} 
  	public void mousePressed(int button,
                  int x,
                  int y){

  		if(item_hovered!=-1){
  			item_selected = item_hovered+itemindex;
  		}

  	}

  	public void mouseMoved(int oldx, int oldy, int newx, int newy){

  		if(inBounds(newx, newy)){

  			int rely = newy-y;

  			item_hovered = (int)(rely / itemheight);



  			System.out.println(item_hovered);

  		}else{
  			item_hovered=-1;
  		}
  	}
  

}
