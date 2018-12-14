##### Name: Game Centre
##### game Description:
This project aims to make user play a game with an account.

First, sign up with a username and password to get an account. You can
not set either  your userName or password to empty, beside that it's fine.
The userName will be unique, so if the userName you entered has already been registered,
it will not be able to create the account.
Then, you will be redirected back to login screen, login with your new signed up account.
You can choose to play the sliding tile game by clicking on "sliding tile".

We have 3 complexities to choose from: (3\*3, 4\*4, 5\*5)
At the beginning of each new game, we shuffle the board such that it is always solvable
to make sure the user can win, but with a different level of difficulty.

Press the Save Game button to save the current game you were playing and press the
Load button to load the saved game.
Load from auto-save loads the game from the game the user just played.
Undo will go back to the board before you made the previous move.

There will be timers and number of moves you have done on the top, be careful,
your score after you finish the game will be determined by these two factors, the
faster the better, less moves are better as well.
If you win the game, you will see a pop up message, follow it's instruction to next
page, you will see your score. **CAUTION:** if you decide to leave before jumping to
redirection screen, your score will not be recorded.

From GameCenter you can go into Scoreboard. it displays
the highest score of a user has for each game, or the
ranking of all users that played the game of same complexity. **Only top10**
**will be recorded at all time.**
You can select which game you want to view by using the
drop-down menu in the scoreboard screen.

Installation:

+ Step 1: Install Android Studio

+ Step 2: git Clone the markus URL to the computer.

+ Step 3: from Android Studio open from Existing Project to open the game.

+ Step 4: Try to run the code from right up green triangle button,
  if there is Pixel2 Device please use it,
  to display our beautiful UI perfectly, otherwise Please Create New Virtual Device Pixel2
  choose Oreo27 then press finish and you can run.

**Additional note:** if above doesn't work, maybe try the installation guide from A2.