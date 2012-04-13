===============================
README: Vooga - Group 2
===============================

Names:
	- Siyang Chen (sc146)
	- Hui Dong (hd37)
	- Kevin Han (kh156)
	- Ian McMahon (icm9)
	- Eric Mercer (ewm10)
	- Kathleen Oshima (kmo16)
	- Glenn Rivkees (ghr4)
	- Michael Zhou (mz43)

Started:	3/27/12
Finished:	4/13/12

Project Length:
	- 400 hours [Actual]
	- 400 hours [Estimated]
	- Good estimate [Estimate evaluation]

Group Meetings:
	- March 27, 10 PM - 11 PM (assigned UTAs present)
	- April 1, 1 PM - 5 PM
	- April 4, 10 PM - 11 PM (assigned UTAs present)
	- April 11, 10 PM - 12 AM (assigned UTAs present)

Discussed with:
	- Jim Posen & Ethan Goh (assigned UTAs)

Resources:
	- GTGE library
		http://code.google.com/p/gtge/
	- GTGE add-ons library
	  	http://www.goldenstudios.or.id/products/utilities/addons/
	- Gson library
		http://code.google.com/p/google-gson/
	- JLayer1.0 library
		http://www.javazoom.net/javalayer/javalayer.html

Files Used to Start and Test:
	- LoadSaveTest.java (src/test package)

Data / Resource Files Required:
	- 'configurations' folder (JSON files for keyboard input)
	- 'lib' folder (supplementary JARs)
	- 'resources' folder (game resources and documentation for some JARs)
	- 'saves' folder (saved game levels)

Instructions:
	- Configure buildpath to include all JARs in the 'lib' folder
	
	- Run 'VoogaLevelEditorMain.java' in src/leveleditor package for the level
	  editor
	  
	- Run 'SimpleGameToTestLevelEditor.java' in src/levelio package to see our
	  framework load a level from a saved level file created in the level editor
	  
	- Run 'GameEngine2D.java' in src/demo package. On the menu screen, use the
	  up/down arrows to choose a demo and press 'Enter' to select it. DemoAI
	  is a simple testing environment for Player-NPC interaction. DemoPlayfield
	  is a short game coded by hand that showcases many of the advanced features
	  we've implemented so far or are currently testing. DemoHUD is a testing
	  environment for the new HUD feature. Press 'ESC' at anytime during one of
	  these games to pause the action and pull up options for continuing the
	  game, restarting the game, or returning to the main menu.