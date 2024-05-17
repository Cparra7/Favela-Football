//Cristian Parra CSC130

package Main;

import java.awt.Color;

import java.util.LinkedList;//Imported for Linked List
import java.util.Queue; //Imported for Queue
import java.util.StringTokenizer;
import java.util.ArrayList; //Imported for Array
import java.util.HashMap;

import Data.BoundingBox;
import Data.Vector2D; //Imported from Data Package for Vector2D
import Data.spriteInfo;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;



public class Main{
	// Fields (Static) below...
	public static Color c = new Color(16,236,55);
	public static boolean isImageDrawn = false; //automatically set to false
	public static stopWatchX timer = new stopWatchX(100); //The Lower the Smoother it goes across screen
	
	//Sprites
//	public static spriteInfo s1 = new spriteInfo(new Vector2D(100,52), "Ronaldo"); //may cause an issue later on if it stays stuck at 520
//	public static spriteInfo s2 = new spriteInfo(new Vector2D(150,520), "walk");
//	public static spriteInfo s3 = new spriteInfo(new Vector2D(200,520), "run");
//	public static spriteInfo s4 = new spriteInfo(new Vector2D(275,520), "sprint");
	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
     public static int currentSpriteIndex = 0;
	
	//Queue to hold animation movements provided by Prof
	public static Queue<Vector2D> vecs1 = new LinkedList<>();//hold initial coordinates
	public static Queue<Vector2D> vecs2 = new LinkedList<>();//overflow
	public static Vector2D currentVec = new Vector2D(-100, -100);
	
	//Hashmap for text
	 public static HashMap<String, String> Map = new HashMap<>();
	 
	 //For key processor
	 public static String trigger = "";
	
	 //Bounding Box
	 
	
    public static ArrayList<BoundingBox> bounds = new ArrayList<>();
    public static ArrayList<BoundingBox> screenBounds = new ArrayList<>();
    public static ArrayList<BoundingBox> equipmentBounds = new ArrayList<>();
    
	public static BoundingBox cleatsBox, soccerBallBox, screenBoxR, screenBoxL, screenBoxT, screenBoxB;
	public static boolean allowMovementW = true;
	public static boolean allowMovementA = true;
	public static boolean allowMovementS = true;
	public static boolean allowMovementD = true;
	public static boolean touchedBall = false;

	
	//To "Start Game"
	public static boolean gameStarted = false;
	public static boolean isBackgroundDrawn = false;
	public static boolean yKeyPressed = false;
	
	
	
	// End Static fields...
	
	public static void main(String[] args) {
		textFileQuotes(); //Needs to retrieve text file 
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							
		
	}
	
	 public static void textFileQuotes() {
	        EZFileRead ezr = new EZFileRead("Quotes.txt");
	        for (int i = 0; i < ezr.getNumLines(); i++) {
	            String raw = ezr.getLine(i);
	            StringTokenizer st = new StringTokenizer(raw, "*");
	            String key = st.nextToken();
	            String quote = st.nextToken();
	            Map.put(key, quote);
	           //left off here 
	           
	        }
	    }

	
	/* This is your access to things BEFORE the game loop starts */
	 public static void start() {
		    // Initialize sprites at fixed positions
		  
          
//		
//		//Cleat box
//		cleatsBox = new BoundingBox (new Vector2D(740, 110), 100, 0);
//		bounds.add(cleatsBox);
//		
//		//Soccer Box
//		soccerBallBox = new BoundingBox (new Vector2D(1090, 480), 5, 30);
//		bounds.add(soccerBallBox);
////		 
		 
		 
		 
		 

		 int y = 350; // Initial y-coordinate
		 int x = 250;
		  
		// vecs1.add(new Vector2D(x, y));
		    // Position for Ronaldo 0
		   /// vecs1.add(new Vector2D(x, y));
		      // Position for walk     1
		  //  vecs1.add(new Vector2D(x, y));
		       // Position for run       2
		 //  vecs1.add(new Vector2D(x, y));
		 
		 // Define bounding box for cleats
//	     cleatsBox = new BoundingBox(740, 110, 300, 300); //  coordinates, then height and width
//		 bounds.add(cleatsBox);
		    // Define bounding box for soccer ball
		 
		 
		 //NEED TO FIX X1 of screenBoXR, cleatsBox, and soccerBallBox, lets me go through them on that side 
		 
		 
		 //leave cooridnates as they are for sweet spot almost
		 soccerBallBox = new BoundingBox(1110, 470, 1,1); 
		 equipmentBounds.add(soccerBallBox);
		    // Add bounding boxes to the list of bounds
		 cleatsBox = new BoundingBox(685,695,1,1);
		 equipmentBounds.add(cleatsBox);
		    
		 screenBoxT = new BoundingBox(0,-10,1920,0);
		 screenBounds.add(screenBoxT);
		
		 screenBoxB = new BoundingBox(0,1020,1920,-10);
		 screenBounds.add(screenBoxB);
		 
		 screenBoxL = new BoundingBox(0,0,50,1080);
		 screenBounds.add(screenBoxT);
		 //NEED TO FIX
		 screenBoxR = new BoundingBox(1920,0,-80,1080);
		 screenBounds.add(screenBoxR);
	 
		 
		 
	//Loops to add sprites to bound boxes
		 
		 int numVariations = 4;
		 int numDirections = 4; 

		 String[] directions = {"forward", "left", "faceback", "frontface"};

		 for (int dirIndex = 0; dirIndex < numDirections; dirIndex++) {
		     String direction = directions[dirIndex];
		     
		     for (int variationIndex = 1; variationIndex <= numVariations; variationIndex++) {
		         String spriteName = direction + variationIndex;
		         
		         sprites.add(new spriteInfo(new Vector2D(x, y), spriteName));
		         bounds.add(new BoundingBox(x, y, 128, 128));
		     }
		 }
	 }

	 
	 

	
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	 public static void update(Control ctrl) {
		 //ctrl.addSpriteToFrontBuffer(0, 0, "TestBackground");
		 
		 //so y doesnt toggle again for startscreen
		 
		 if (trigger.equals("YPressed")) {
		        // Toggle between test background and main background only if Y key has not been pressed before
		        if (!yKeyPressed) {
		            isBackgroundDrawn = !isBackgroundDrawn;
		            yKeyPressed = true; //  Y key as pressed so its true
		        }
		        trigger = ""; // Resets the trigger
		        // If game has not started yet, mark it as started
		        if (!gameStarted) {
		            gameStarted = true;
		        }
		    }
		    // Display backgrounds based on game state
		    if (isBackgroundDrawn) {
		        ctrl.addSpriteToFrontBuffer(0, 0, "Background");
		    } else {
		        ctrl.addSpriteToFrontBuffer(0, 0, "StartScreen");
		    }
		  
		  
		  spriteInfo currentSprite = sprites.get(currentSpriteIndex);
		  ctrl.addSpriteToFrontBuffer((int)currentSprite.getCoords().getX(), currentSprite.getCoords().getY(), currentSprite.getTag());
		 
	
		
		    // Move the currentVec
		    if (isImageDrawn && currentVec != null) {
		        currentVec.setX(currentVec.getX() + 5);
		    }

		    // Check the trigger to determine sprite animation
		    switch (trigger) {
		        case "FaceBack": // W KEY
		            if (timer.isTimeUp()) {
		                currentSpriteIndex++;
		                if (currentSpriteIndex > 11) {
		                    currentSpriteIndex = 8; // reset to the first sprite of the direction
		                }
		                timer.resetWatch();
		                trigger = "";     // helps it stop on whatever sprite when I dont press
		            }
		            break;
		        case "FrontFace": // S KEY
		            if (timer.isTimeUp()) {
		                currentSpriteIndex++;
		                if (currentSpriteIndex > 15) {
		                    currentSpriteIndex = 12; 
		                }
		                timer.resetWatch();
		                trigger = "";
		            }
		            break;
		        case "Left":  // A KEY
		            if (timer.isTimeUp()) {
		                currentSpriteIndex++;
		                if (currentSpriteIndex > 7) {
		                    currentSpriteIndex = 4; 
		                }
		                timer.resetWatch();
		                trigger = "";
		            }
		            break;
		        case "Forward": // D KEY
		            if (timer.isTimeUp()) {
		                currentSpriteIndex++;
		                if (currentSpriteIndex > 3) {
		                    currentSpriteIndex = 0; // 
		                }
		                timer.resetWatch();
		                trigger = "";
		            }
		            
		 //****If I have time make text on background
		        case "SpaceBarPressed": 
	                
	                BoundingBox boundsA = new BoundingBox(currentSprite.getCoords().getX() - 31,
	                        currentSprite.getCoords().getY(), 128, 128);
	                BoundingBox boundsD = new BoundingBox(currentSprite.getCoords().getX() + 31,
	                        currentSprite.getCoords().getY(), 128, 128);
	                BoundingBox boundsS = new BoundingBox(currentSprite.getCoords().getX(),
	                        currentSprite.getCoords().getY() + 31, 128, 128);
	                BoundingBox boundsW = new BoundingBox(currentSprite.getCoords().getX(),
	                        currentSprite.getCoords().getY() - 31, 128, 128);

	                if (trigger.equals("SpaceBarPressed") &&
	                        (boundsA.isColliding(soccerBallBox) || boundsD.isColliding(soccerBallBox)
	                                || boundsS.isColliding(soccerBallBox) || boundsW.isColliding(soccerBallBox))) {
	                    String string1 = Map.get("string1");
	                    if (string1 != null) {
	                        ctrl.drawString(1227, 435, string1, Color.white);
	                    }
	                }
	                if (trigger.equals("SpaceBarPressed") &&
	                        (boundsA.isColliding(cleatsBox) || boundsD.isColliding(cleatsBox)
	                                || boundsS.isColliding(cleatsBox) || boundsW.isColliding(cleatsBox))) {
	                    String string2 = Map.get("string2");
	                    if (string2 != null) {
	                        ctrl.drawString(545, 576, string2, Color.white);
	                    }
	                }
	                break;
	            default:
	                // Handle other trigger values if needed
	                break;
	        }


		    
		
		
		    BoundingBox boundsA = new BoundingBox(currentSprite.getCoords().getX() -31, 
		    		currentSprite.getCoords().getY(),128,128);
		    BoundingBox boundsD = new BoundingBox(currentSprite.getCoords().getX() +31,
		    		currentSprite.getCoords().getY(),128,128);
		    BoundingBox boundsS = new BoundingBox(currentSprite.getCoords().getX(), 
		    		currentSprite.getCoords().getY()+31,128,128);
		    BoundingBox boundsW = new BoundingBox(currentSprite.getCoords().getX(), 
		    		currentSprite.getCoords().getY()-31,128,128);
		   
		    
		    //for text if character tries running off, without trigger - figure out later
	   
		    	    if(boundsA.isColliding(screenBoxL) || 
		    	     boundsD.isColliding(screenBoxL) || 
		    	     boundsS.isColliding(screenBoxL) || 
		    	     boundsW.isColliding(screenBoxL)) {
		    	    String string1 = Map.get("string6");
		    	    if (string1 != null) {
		    	        ctrl.drawString(100, 250, string1, Color.red);
		    	    }
		    	}
		    	    if(boundsA.isColliding(screenBoxB) || 
				    	     boundsD.isColliding(screenBoxB) || 
				    	     boundsS.isColliding(screenBoxB) || 
				    	     boundsW.isColliding(screenBoxB)) {
				    	    String string1 = Map.get("string7");
				    	    if (string1 != null) {
				    	        ctrl.drawString(1380, 944, string1, Color.red);
				    	    }
				    	}
		    	    if(boundsA.isColliding(screenBoxT) || 
				    	     boundsD.isColliding(screenBoxT) || 
				    	     boundsS.isColliding(screenBoxT) || 
				    	     boundsW.isColliding(screenBoxT)) {
				    	    String string1 = Map.get("string8");
				    	    if (string1 != null) {
				    	        ctrl.drawString(1380, 944, string1, Color.red);
				    	    }
				    	}
		    	    if(boundsA.isColliding(screenBoxR) || 
				    	     boundsD.isColliding(screenBoxR) || 
				    	     boundsS.isColliding(screenBoxR) || 
				    	     boundsW.isColliding(screenBoxR)) {
				    	    String string1 = Map.get("string9");
				    	    if (string1 != null) {
				    	        ctrl.drawString(1380, 944, string1, c); // green bc hes not yelling
				    	    }
				    	}
		    
		    //if boxes collide I need to disable the key to act as barrier almost 
		    if(boundsW.isColliding(soccerBallBox) || boundsW.isColliding(screenBoxT) || boundsW.isColliding(cleatsBox)) {
		    	allowMovementW = false;
		    }else {
		    	allowMovementW = true;
		    }
		    if(boundsA.isColliding(soccerBallBox) || boundsA.isColliding(screenBoxL) || boundsA.isColliding(cleatsBox)) {
		    	allowMovementA= false;
		    }else {
		    	allowMovementA = true;
		    }
		    if(boundsS.isColliding(soccerBallBox) || boundsS.isColliding(screenBoxB) || boundsS.isColliding(cleatsBox)) {
		    	allowMovementS = false;
		    }else {
		    	allowMovementS = true;
		    }
		    if(boundsD.isColliding(soccerBallBox) || boundsD.isColliding(screenBoxR) || boundsD.isColliding(cleatsBox)) {
		    	allowMovementD = false;
		    }else {
		    	allowMovementD = true;
		    }
		    
		 //   ctrl.addSpriteToFrontBuffer(0, 0, "TestBackground");
		 
		   

		   
		}

	
	 
//	 public static boolean checkCollision(BoundingBox spriteBox, BoundingBox otherBox) {
//		    return !(spriteBox.getX2() < otherBox.getX1() ||
//		             spriteBox.getX1() > otherBox.getX2() ||
//		             spriteBox.getY2() < otherBox.getY1() ||
//		             spriteBox.getY1() > otherBox.getY2());
//		}


	
	// Additional Static methods below...(if needed)

}
