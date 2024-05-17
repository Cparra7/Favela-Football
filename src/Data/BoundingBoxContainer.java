//package Data;
//
//import java.util.ArrayList;
//
//public class BoundingBoxContainer {
//    private ArrayList<BoundingBox> boundingBoxes;
//
//    public BoundingBoxContainer() {
//        boundingBoxes = new ArrayList<>();
//    }
//
//    public void addBoundingBox(BoundingBox box) {
//        boundingBoxes.add(box);
//    }
//
//    public boolean checkCollisions(BoundingBox other) {
//        for (BoundingBox box : boundingBoxes) {
//            if (box.collides(other)) {
//                return true; // Collision detected
//            }
//        }
//        return false; // No collision
//    }
//}
