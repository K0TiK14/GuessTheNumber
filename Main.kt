import kotlin.random.Random
import kotlin.system.exitProcess

/**
 * A class that implements the game "Guess the number". The human and computer change roles after the game is over.
 */
class GuessTheNumber {
    private var number = 0 // picked number
    private var firstPlayer = Player.HUMAN
    private var secondPlayer = Player.HUMAN
    private var gameFinished = false // end of game flag

    private var min = 0 // min and max - range for guessing the number by computer
    private var max = 100

    enum class Player {
        HUMAN, COMPUTER
    }

    /**
     * Function to start the game
     */
    fun startGame() {
        menu()
        if (firstPlayer == Player.COMPUTER) {
            println("I picked a number between 0 and 100. Can you guess it?")
            number = Random.nextInt(101) // generate a number from 0 to 100
        } else { // if firstPlayer == HUMAN
            println("Pick a number between 0 and 100:")

            var a = readLine()?.toIntOrNull()
            while (a == null) {
                println("Invalid input. Please enter a valid number:")
                a = readLine()?.toIntOrNull()
            }
            number = a
        }

        while (!gameFinished) takeTurn()

        gameFinished = false // reset end of game flag
    }

    /**
     * Function for making a player's turn
     */
    private fun takeTurn() {
        if (secondPlayer == Player.HUMAN) {
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

        } else { // if secondPlayer == COMPUTER
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
                min = computerGuess + 1
            } else {
                println("The number is lower.")
                max = computerGuess - 1
            }
        }
    }

    /**
     * Function what changes current player
     */
    private fun menu() {
        println("\t\t\tMenu")
        println("\t1. Human vs Computer")
        println("\t2. Computer vs Human")
        println("\t3. Human vs Human")
        println("\t4. Computer vs Computer")
        println("\t0. Exit")
        var choice = readLine()?.toIntOrNull()
        while (choice == null) {
            println("Invalid input. Please enter a valid choice:")
            choice = readLine()?.toIntOrNull()
        }
        when (choice) {
            0 -> exitProcess(0)
            1 -> {
                firstPlayer = Player.HUMAN
                secondPlayer = Player.COMPUTER
            }
            2 -> {
                firstPlayer = Player.COMPUTER
                secondPlayer = Player.HUMAN
            }
            3 -> {
                firstPlayer = Player.HUMAN
                secondPlayer = Player.HUMAN
            }
            4 -> {
                firstPlayer = Player.COMPUTER
                secondPlayer = Player.COMPUTER
            }
        }
    }
}

fun main() {
    val game = GuessTheNumber()
    while (true) game.startGame()
}