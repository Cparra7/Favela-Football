package Data;
					//Y1

               //////////////////////////////
               //							//	
               //							//	
               //							//
               //							//
        //X1   //							//   //X2
               //							//	
               //							//
               //							//
               //							//	
               //////////////////////////////
					//Y2



public class BoundingBox {
    private int x1;
    private int y1;
    private int x2; // Act as width
    private int y2; // ACt as Height

    // Constructor
    public BoundingBox(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    // Getters and setters
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getx2() {
        return x2;
    }

    public void setx2(int x2) {
        this.x2 = x2;
    }

    public int gety2() {
        return y2;
    }

    public void sety2(int y2) {
        this.y2 = y2;
    }

    //Profs BOunding Box algo
    //box1.X1 > box2.X2
//    box1.X2 < box2.X1
//    box1.Y1 > box2.Y2
//    box1.Y2 < box2.Y1
    
    
    // compare to second box/equipment
    
    public boolean isColliding(BoundingBox secondBox) {
        return (x1 < secondBox.x1 + secondBox.x2 && x1 + x2 > secondBox.x1 &&
                y1 < secondBox.y1 + secondBox.y2 && y1 + y2 > secondBox.y1);
    }
}
