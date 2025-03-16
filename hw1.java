// Name Surname: ALPEREN BUGAY TASKIRAN
// Student ID : 18050161004
// Video Link : https://drive.google.com/file/d/1QSpB-5X1U123SYR1dv-TIHgiMdG5tcn-/view?usp=sharing

import java.util.Scanner;

public class hw1 {
    private Song head, tail, current;
    private Scanner scanner;

    public hw1() {
        this.scanner = new Scanner(System.in);
    }

    private class Song {
        String title, artist;
        int duration;
        Song next, prev;

        public Song(String title, String artist, int duration) {
            this.title = title;
            this.artist = artist;
            this.duration = duration;
        }
    }

    // Adds a song to the end of the playlist
    public void addSong() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        System.out.print("Enter duration (sec): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid duration. Please enter a number.");
            scanner.next();
            return;
        }

        int duration = scanner.nextInt();
        scanner.nextLine();
        Song newSong = new Song(title, artist, duration);

        if (head == null) {
            head = tail = current = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
        System.out.println("Song added: " + title);
    }

    // Removes a song from the playlist
    public void removeSong() {
        System.out.print("Enter song title to remove: ");
        String title = scanner.nextLine();

        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song temp = head;
        while (temp != null && !temp.title.equals(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Error: Song not found.");
            return;
        }

        if (temp == head) head = temp.next;
        if (temp == tail) tail = temp.prev;
        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;

        System.out.println("Song removed: " + title);
    }

    // Displays the entire playlist
    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song temp = head;
        int position = 1;
        while (temp != null) {
            System.out.println(position + ". " + temp.title + " - " + temp.artist + " (" + temp.duration + "s)");
            temp = temp.next;
            position++;
        }
    }

    // Moves a song to a new position in the playlist
    public void moveSong() {
        System.out.print("Enter song title to move: ");
        String title = scanner.nextLine();
        System.out.print("Enter new position: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid position. Please enter a number.");
            scanner.next();
            return;
        }

        int newPosition = scanner.nextInt();
        scanner.nextLine();

        if (head == null || newPosition < 1) {
            System.out.println("Error: Invalid operation.");
            return;
        }

        Song temp = head;
        while (temp != null && !temp.title.equals(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Error: Song not found.");
            return;
        }

        if (newPosition == 1) {
            if (temp == head) {
                System.out.println("Song is already at position 1.");
                return;
            }
            temp.prev.next = temp.next;
            if (temp.next != null) temp.next.prev = temp.prev;
            temp.next = head;
            head.prev = temp;
            head = temp;
            head.prev = null;
        } else {
            Song current = head;
            int count = 1;
            while (current.next != null && count < newPosition - 1) {
                current = current.next;
                count++;
            }
            if (temp.prev != null) temp.prev.next = temp.next;
            if (temp.next != null) temp.next.prev = temp.prev;
            if (temp == tail) tail = temp.prev;
            temp.next = current.next;
            if (current.next != null) current.next.prev = temp;
            current.next = temp;
            temp.prev = current;
        }
        System.out.println("Song moved: " + title + " to position " + newPosition);
    }

    public void playNext() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Playing: " + current.title);
        } else {
            System.out.println("No next song available.");
        }
    }

    public void playPrevious() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Playing: " + current.title);
        } else {
            System.out.println("No previous song available.");
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. Display Playlist");
            System.out.println("4. Move Song");
            System.out.println("5. Play Next Song");
            System.out.println("6. Play Previous Song");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1-7.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addSong();
                case 2 -> removeSong();
                case 3 -> displayPlaylist();
                case 4 -> moveSong();
                case 5 -> playNext();
                case 6 -> playPrevious();
                case 7 -> {
                    System.out.println("Exiting.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        new hw1().start();
    }
}
