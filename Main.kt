import kotlin.random.Random

/**
 * A class that implements the game "Guess the number". The human and computer change roles after the game is over.
 */
class GuessTheNumber {
    private var number = 0 // picked number
    private var currentPlayer = "HUMAN"
    private var gameFinished = false // end of game flag

    private var min = 0 // min and max - range for guessing the number by computer
    private var max = 100

    /**
     * Function to start the game
     */
    fun startGame() {
        if (currentPlayer == "HUMAN") {
            println("I picked a number between 0 and 100. Can you guess it?")
            number = Random.nextInt(101) // generate a number from 0 to 100
        } else { // if currentPlayer == "COMPUTER"
            println("Pick a number between 0 and 100:")

            var a = readLine()?.toIntOrNull()
            while (a == null) {
                println("Invalid input. Please enter a valid number:")
                a = readLine()?.toIntOrNull()
            }
            number = a
        }

        while (!gameFinished) takeTurn()

        currentPlayer = getNextPlayer() // change player
        gameFinished = false // reset end of game flag
    }

    /**
     * Function for making a player's turn
     */
    private fun takeTurn() {
        if (currentPlayer == "HUMAN") {
            println("Enter your guess: ")

            var playerGuess = readLine()?.toIntOrNull()
            while (playerGuess == null) {
                println("Invalid input. Please enter a valid number:")
                playerGuess = readLine()?.toIntOrNull()
            }

            // check if the number is guessed
            if (playerGuess == number) {
                println("Congratulations! You guessed the number.\n")
                gameFinished = true
            } else if (playerGuess < number) {
                println("The number is higher.")
            } else {
                println("The number is lower.")
            }

        } else { // if currentPlayer == "COMPUTER"
            val computerGuess = (min + max) / 2
            println("Computer's guess: $computerGuess")

            // check if the number is guessed
            if (computerGuess == number) {
                println("Congratulations! Computer guessed the number.\n")
                gameFinished = true
                min = 0
                max = 100
            } else if (computerGuess < number) {
                println("The number is higher.")
                min = computerGuess
            } else {
                println("The number is lower.")
                max = computerGuess
            }
        }
    }

    /**
     * Function what changes current player
     */
    private fun getNextPlayer(): String {
        return if (currentPlayer == "HUMAN") "COMPUTER" else "HUMAN"
    }
}

fun main() {
    val game = GuessTheNumber()
    var playAgain = true

    while (playAgain) {
        game.startGame()
        println("Do you want to play again? (yes/no): ")
        val answer = readLine()
        playAgain = (answer == "yes")
    }
}