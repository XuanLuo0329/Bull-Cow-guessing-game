# Bull-Cow-guessing-game
The bulls and cows game is a code-breaking game designed for two or more players. Each player chooses a secret code of 4 digits from 0 – 9. The digits must be all different. The goal of the game is for each player to guess the other player's secret code.

The players in turn present their guesses to the opponents. The opponents respond by telling the players:

The number of bulls, i.e. the number of matching digits in their right positions, and
The number of cows, i.e. the number of matching digits but in different positions.

The main goal of this game is to develop the bulls and cows game that allows a single player to play interactively against the computer. The game stores two secret codes, one from the player and one from the computer. The player and the computer will try to guess each other’s secret code. Both the player and the computer only have seven attempts for guessing the secret code. If the player enters an invalid input, the game should ask the player to try again. The game also lets the player choose the difficulty level to play against the computer. There are three levels: easy, medium and hard. In addition, the game can read from a text file that contains multiple guesses from the player, and save the results to another text file.

## Easy level 
It means that the player can now also enter a secret code when the game begins, which the computer must guess. Remember to verify that the player has chosen a valid secret code. The player and computer each take turns guessing the other's code. The game ends when either side successfully guesses the other's code (resulting in a win for that side), or when each side has made seven incorrect guesses (resulting in a draw). When the computer makes a guess, it will simply generate a random (valid) guess.

## Medium level
It means that at the beginning of the game (before the player enters their own secret code), they will be asked to select either an easy or medium mode opponent to play against. If the player chooses to play against an easy computer, the game should proceed in exactly the same manner as in before. However, if a medium computer is selected, the computer should keep track of guesses it has already made. The AI will not make the same guess twice.

## Hard level 
It means that the player can additionally choose to play against a hard computer mode opponent. If the player chooses to play against an easy or medium computer, the game should proceed in exactly the same manner as in before. However, if a hard computer is selected, the computer should be much more intelligent when guessing, rather than just choosing at random. 

The possible strategy involves keeping a list of all possible guesses, and then intelligently pruning that list based on the result of each guess made by the AI. In this strategy, the first guess by the computer will be chosen randomly. After this guess, all subsequent guesses will be carefully planned. The computer keeps a track of precisely which codes remain consistent with all the information it has received so far. The computer will only choose a guess that has a chance of being the correct one.

For example, let’s assume the computer’s first guess scored 1 bull and 1 cow. Then the only codes that still have a chance to be the correct one, are those which match up 1 bull and 1 cow with the first guess. All other codes should be eliminated. The computer will go through its list of all possible codes and test each against its first guess. If a code matches 1 bull and 1 cow with the first guess, then it will remember that the code is still a possible candidate for the secret code. If a code does not match 1 bull and 1 cow with the first guess, the code will be eliminated. After this is completed, the computer randomly chooses any of the possible candidates for the second guess.

If the computer’s second guess scored 2 bulls and 1 cow, the computer checks all the remaining candidates to see which codes match up 2 bulls and 1 cow with the second guess. Those codes that do not match are eliminated. In this manner, each guess is consistent with all the information obtained up until that point in the game.
