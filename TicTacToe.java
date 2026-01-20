import java.util.*;


/**
 * Command-line Tic Tac Toe Game
 * Allows two players to play on a 3x3 grid
 */

public class TicTacToe{

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    static String player1, player2;

    public static void main(String args[]){
        System.out.println("====== TIC TAC TOE GAME ======");

        // Taking player names
        System.out.print("Enter name of Player 1 (X): ");
        player1 = sc.nextLine();

        System.out.print("Enter name of Player 2 (O): ");
        player2 = sc.nextLine();

        // Instructions
        System.out.println("Instructions:");
        System.out.println("1. " + player1 + " plays with X");
        System.out.println("2. " + player2 + " plays with O");
        System.out.println("3. Enter row and column numbers (0 to 2)");
        System.out.println("4. First player to align 3 symbols wins");
        System.out.println();

        boolean playAgain;

        do{
            initializeBoard();
            playGame();
            System.out.println("Do you want to play again? (yes/no):");
            playAgain = sc.next().equalsIgnoreCase("yes");
            sc.nextLine();// Clear buffer
        }while(playAgain);

        System.out.println("Thank you for playing Tic Tac Toe!");
        sc.close();
    }

    // Initialize the board with empty cells
    static void initializeBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j]=' ';
            }
        }
    }

    // Main game loop
    static void playGame(){
        char currentSymbol = 'X';
        boolean gameOver = false;
    
        while(!gameOver){
            displayBoard();
            
             String currentPlayerName =(currentSymbol == 'X') ? player1 : player2;

            System.out.println(currentPlayerName + "'s turn (" + currentSymbol + ")");

            int row,col;

            // Input validation
            while(true){
                System.out.println("Enter row and column: ");
                row = sc.nextInt();
                col = sc.nextInt();

                if(row>=0 && row<3 && col>=0 && col<3 && board[row][col]==' '){
                    break;
                }
                else{
                    System.out.println("Invalid move. Try again.");
                }
            }

            board[row][col]=currentSymbol;

            // Win check
            if(checkWin(currentSymbol)){
                displayBoard();
                System.out.println(currentPlayerName+" wins!");
                gameOver = true;
            }
            // Draw check
            else if(isBoardFull()){
                displayBoard();
                System.out.println("Game is a draw!");
                gameOver = true;
            }
            // Switch turn
            else{
                currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
            }
        }
    }

    // Display board
    static void displayBoard(){
        System.out.println("\n   0   1   2");
        System.out.println(" +---+---+---+");

        for(int i=0;i<3;i++){
            System.out.print(i+"|");
            for(int j=0;j<3;j++){
                System.out.print(" "+board[i][j]+" |");
            }
            System.out.println();
            System.out.println(" +---+---+---+");
        }
        System.out.println();
    }

    // Check win condition
    static boolean checkWin(char symbol){

        //Rows & Columns
        for(int i=0;i<3;i++){
            if((board[i][0]==symbol && board[i][1]==symbol && board[i][2]==symbol) ||
                (board[0][i]==symbol && board[1][i]==symbol && board[2][i]==symbol)){
                    return true;
            }
        }

        // Diagonals
        if((board[0][0]==symbol && board[1][1]==symbol && board[2][2]==symbol)||
            (board[0][2]==symbol && board[1][1]==symbol && board[2][0]==symbol)){
                return true;
        }
        return false;
    }

    // Check draw
    static boolean isBoardFull(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }
}