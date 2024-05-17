Favela Football - Game Project
Overview
Favela Football is a single-screen game level developed in Java, utilizing a custom Gaming API. The game features a character navigating through a vibrant environment, interacting with objects and avoiding obstacles.

Features
Character Movement and Animation
Movement: The character can move in four directions: up, down, left, right.
Animation: Each direction features smooth and dynamic animations, enhancing the gameplay experience.
Environment Design
Walls and Boundaries: The edges of the screen are lined with wall images that visually contain the "room", creating a defined play area.
Ground Texture: The room has a detailed ground image under the character's feet, providing a visually appealing environment.
Collision Detection
Bounding Boxes: Collision detection prevents the character from passing through walls and other solid objects, using bounding box techniques to ensure realistic interactions.
Interactive Items
Examinable Objects: There are at least two items within the level that the character can examine when nearby and facing the object.
Interaction: Pressing the space bar when near an item displays a description, allowing players to interact with and learn more about their environment.
Technical Implementation
Character Movement
Class: Character
Methods:
moveUp()
moveDown()
moveLeft()
moveRight()
animate()
Environment and Objects
Class: Room
Methods for drawing walls and ground textures.
Classes: Wall, Ground
Represent the structural elements of the room.
Collision Detection
Class: BoundingBox
Custom data type for handling collision detection.
Class: CollisionManager
Manages collections of bounding boxes using ArrayList.
Interactive Items
Class: Item
Methods for examining items and displaying descriptions.
Class: GameLevel
Manages the logic for item interactions.
Running the Game
Clone the repository to your local machine.
Open the project in Eclipse (or your preferred IDE).
Ensure the Gaming API is installed correctly.
Run the main class FavelaFootball to start the game.
