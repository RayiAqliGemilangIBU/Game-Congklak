import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private List<Hole> holes;
    private int currentPlayer;
    private int counter;

    public Board() {
        holes = new ArrayList<>();

        // Inisialisasi lubang normal dengan 7 biji
        for (int i = 0; i < 7; i++) {
            holes.add(new Hole(TypeHole.NORMAL, 7)); // Lubang pemain pertama
        }
        holes.add(new Hole(TypeHole.BANK, 0)); // Bank pemain pertama

        for (int i = 8; i < 15; i++) {
            holes.add(new Hole(TypeHole.NORMAL, 7)); // Lubang pemain kedua
        }
        holes.add(new Hole(TypeHole.BANK, 0)); // Bank pemain kedua

        currentPlayer = 1; // Pemain pertama memulai
        counter = 0; // Inisialisasi counter
    }

    public void displayBoard() {
        // Tampilkan lubang pemain kedua

        System.out.print("_________");
        for (int i = 8; i <= 14; i++) {
            System.out.print("[" + (i+1) + "]");
            System.out.print("_____");
        }
        System.out.println("_____");

        System.out.print("______");
        for (int i = 8; i <= 14; i++) {
            System.out.print("(   " + holes.get(i).getSeedCount() + "   )");
        }
        System.out.println("_____");

        // Tampilkan bank
        System.out.println("{  " + holes.get(7).getSeedCount() + "  }-------------------------------------------------------------{  " + holes.get(15).getSeedCount() + "  }");

        // Tampilkan lubang pemain pertama
        System.out.print("______");
        for (int i = 6; i >= 0; i--) {
            System.out.print("(   " + holes.get(i).getSeedCount() + "   )");
        }
        System.out.println("_____");
        System.out.print("_________");
        for (int i = 7; i > 0; i--) {
            System.out.print("[" + (i) + "]");
            System.out.print("______");
        }
        System.out.println("_____");
    }

    public void playTurn(int chosenHole) {
        if (counter == 0 && !isValidMove(chosenHole)) {
            System.out.println("Invalid move. Please choose a valid hole.");
            return;
        }

        int seedsInHand = holes.get(chosenHole).takeSeeds();
        int index = chosenHole;

        while (seedsInHand > 0) {
            index = (index + 1) % holes.size();
            if ((currentPlayer == 1 && index != 15) || (currentPlayer == 2 && index != 7)) {
                holes.get(index).addSeed();
                seedsInHand--;
            }
        }

        displayBoard();

        if (holes.get(index).getSeedCount() > 1 && holes.get(index).getType() == TypeHole.NORMAL) {
            counter++; // Tambah counter jika player dapat giliran lagi
            System.out.println("Player " + currentPlayer + " continues the turn from hole " + (index + 1));
            playTurn(index); // Lanjutkan giliran pemain
        } else {
            // Ganti giliran ke pemain lain dan reset counter
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            counter = 0;
        }
    }

    private boolean isValidMove(int index) {
        if (currentPlayer == 1) {
            return index >= 0 && index <= 6 && holes.get(index).getSeedCount() > 0;
        } else {
            return index >= 8 && index <= 14 && holes.get(index).getSeedCount() > 0;
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        displayBoard(); // Tampilkan papan sebelum memulai giliran pemain
        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn. Choose a hole to play:");
            int chosenHole = scanner.nextInt() - 1; // Mengubah input pengguna menjadi indeks
            playTurn(chosenHole);
        }
    }

    public static void main(String[] args) {
        Board gameBoard = new Board();
        gameBoard.startGame();
    }
}
