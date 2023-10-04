package hw6bisharasaid;

/*
course: CSC190
project: Homework Assignment 6
date: 10/20/22
author: Bishara Said
purpose:  Java program to simulate a blackjack game of cards. The computer will
    play the role of the dealer. The program will randomly generate the cards 
    dealt to the player and dealer during the game. Cards in this game will be 
    represented by numbers 1 to 13 with Ace being represented by a 1. Face cards 
    (i.e. Jack, Queen, and King) are worth 10 points to a hand while an Ace can
    be worth 1 or 11 points depending on the user’s choice. The numbered cards 
    are worth their number value to the hand.
*/

import java.util.Scanner;
public class HW6Bisharasaid {
    
    public static void main(String[] args) {
        
        //variable declarations
        int choice, hiddenVal, hit = 0;
        int playerTotal = 0, cpuTotal = 0;
        Scanner in = new Scanner(System.in);
        
        //game menu
        System.out.println("Welcome to BlackJack!");
        do{
            System.out.println("\nWould you like to test your luck?\n"
                    + "1. Play BlackJack\n"
                    + "2. Quit game\n");
            
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine();
            
            switch(choice){
                case 1:
                    //player draws two cards
                    playerTotal = drawCard4Player(playerTotal);
                    playerTotal = drawCard4Player(playerTotal);
                    
                    //dealer draws two cards
                    cpuTotal = hiddenCard(cpuTotal);
                    hiddenVal = cpuTotal; //store the hidden card value
                    cpuTotal = drawCard4Cpu(cpuTotal);
                    
                    System.out.println("\nYour total is "+playerTotal);
                    while((cpuTotal<21) && (playerTotal<21) && (hit != 2)){

                        //ask if they want to keep drawing
                        System.out.print("\nWould you like to hit?"
                                + "(1. Hit! or 2. Stop): ");
                        hit = in.nextInt();
                        in.nextLine();
                        
                        //input validation
                        while((hit != 1) && (hit != 2)){
                            System.out.println("\nChoose an appropriate option:");
                            System.out.print("Would you like to hit?"
                                    + "(1. Hit! or 2. Stop): ");
                            hit = in.nextInt();
                            in.nextLine();
                            
                        }//end nested while
                        
                        if(hit == 1){
                            playerTotal = drawCard4Player(playerTotal);
                            System.out.println("\nYour total is " + playerTotal);
                        }

                    }//end while
                    
                    //System.out.println("\nYour total is " + playerTotal);
                    System.out.println("The hidden card was worth "+hiddenVal);
                    System.out.println("Dealer's total is "+cpuTotal);
                    
                    getWinner(playerTotal, cpuTotal);
                    
                    
                    break;
                    
                case 2:
                    //end program, no more selections
                    System.out.println("\nGoodbye for now!");
                    continue;
                    
                default:
                    //prompt the user to give a valid choice
                    System.out.println("\nThat's not a valid choice! Try again.");
                    break;
            }
            
            //reset values for future games
            playerTotal = 0;
            cpuTotal = 0;
            hit = 0;
            
        }while(choice != 2); //end do-while
        
    }//end main

    
    //method to draw a card for the player
    public static int drawCard4Player(int total){
        //variable declaration
        int card, worth;
        Scanner in = new Scanner(System.in);
        
        //randomly draw a card
        card = (int)(Math.random()*(13 - 1))+1;
        
        switch(card){
            case 1:
                System.out.println("You drew an Ace!");
                System.out.print("1. Is it worth 1 or 11?: ");
                worth = in.nextInt();
                in.nextLine();
            
                while(worth != 1 && worth != 11){
                    System.out.println("Choose a valid option");
                    
                    System.out.println("You drew an Ace!");
                    System.out.print("1. Is it worth 1 or 11?: ");
                    worth = in.nextInt();
                    in.nextLine();
                }
                total += worth;
                break;
            case 11:
                System.out.println("You drew a Jack!");
                total += 10;
                break;
            case 12:
                System.out.println("You drew a Queen!");
                total += 10;
                break;
            case 13:
                System.out.println("You drew a King!");
                total += 10;
                break;
            default:
                System.out.println("You drew a " + card + "!");
                total += card;
                break;
        }
        
        return total;
    }//end drawCard4Player
    
    
    //draws card for the dealer
    public static int drawCard4Cpu(int total){
        
        //variable declaration
        int card, worth;
        
        //randomly draw a card
        card = (int)(Math.random()*(13 - 1))+1;
        
        switch(card){
            case 1:
                System.out.print("Dealer drew an Ace!");
                worth = (int)(Math.random()*(2-1))+1;
                if(worth == 1){
                    System.out.println(" They decided that it's worth 1");
                    total += 1;
                }else{
                    System.out.println(" They decided that it's worth 11");
                    total += 11;
                }
                break;
                
            case 11:
                System.out.println("Dealer drew a Jack!");
                total += 10;
                break;
                
            case 12:
                System.out.println("Dealer drew a Queen!");
                total += 10;
                break;
                
            case 13:
                System.out.println("Dealer drew a King!");
                total += 10;
                break;
                
            default:
                System.out.println("Dealer drew a " + card + "!");
                total += card;
                break;
                
        }
        
        return total;
    }//end drawCard4Cpu
    
    
    //method to draw a card for the Dealer, but the prompts is hidden
    public static int hiddenCard(int total){
        
        //variable declaration
        int card, worth;
        
        //randomly draw a card
        card = (int)(Math.random()*(13 - 1))+1;
        
        switch(card){
            case 1:
                //randomly choose between scores 1 and 11
                worth = (int)(Math.random()*(2-1))+1;
                if(worth == 1)
                    total += 1;
                else
                    total += 11;
                break;
                
            case 11:
                total += 10;
                break;
                
            case 12:
                total += 10;
                break;
                
            case 13:
                total += 10;
                break;
                
            default:
                total += card;
                break;
        }
        
        System.out.println("The Dealer drew a hidden card!");
        return total;
    }//end hiddenCard
    
    
    //method to get winner
    public static void getWinner(int ptotal, int ctotal){
        //what determines the winner?
        if(ptotal > 21){
            //cpu wins
            System.out.println("Oh no, you Lost!");
            
        }else if(ctotal > 21){
            //player wins
            System.out.println("You Win!");
            
        }else if(ctotal > ptotal){
            //cpu wins
            System.out.println("Oh no, you Lost!");
            
        }else if(ptotal > ctotal){
            //player wins
            System.out.println("You Win!");
            
        }else{
            System.out.println("It's a Tie!");
        }
    }//end getWinner
    
      
}// end class
