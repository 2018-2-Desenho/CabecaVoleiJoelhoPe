package game;

import java.util.HashMap;
import java.util.Scanner;
import util.AbstractGameBuilder;
import util.GameBuilderDirector;
import util.GameManager;

public class Main {
    static AbstractGameBuilder builder;
    static GameBuilderDirector director;
    static GameManager myGame;
    static boolean end;
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        builder = new GameBuilder();
        director = new GameBuilderDirector(builder);
        end = false;
        HashMap<String, String> imageFileNames = new HashMap<>();
        
        System.out.println("Bem vindo ao CabeçaVoleioJoelhoPé!");
        
        while(!end){
            menu();
            imageFileNames.clear();
            
            switch(in.next()){
                case "1":
                    imageFileNames.put("Personagem_Esquerda", menuPersonagem());
                    imageFileNames.put("Personagem_Direita", menuPersonagem());
                    imageFileNames.put("Arena", menuArena());
                    
                    myGame = director.construct(imageFileNames);
                    myGame.run();
                    break;
                case "2": 
                    end = true;
                    break;
                default:
                    System.out.println("A opção que vc tentou acessar não existe!");
            }
        }
        
        System.out.println("Até a Proxima!");
    }
    
    public static void menu (){
        System.out.println("\nMenu opções:");
        System.out.println("\n\t1 - Jogar");
        System.out.println("\t2 - Sair");
        System.out.println("\nDigite o número da opção que quer acessar: ");
    }
    
    public static String menuPersonagem (){
        boolean isEnd = false;
        String character = "personagens/";
        
        while(!isEnd){
            isEnd = true;
            
            System.out.println("\nPersonagens diponiveis:");
            System.out.println("\n\t1 - Gabi");
            System.out.println("\t2 - Lucas L");
            System.out.println("\t3 - Lucas S");
            System.out.println("\t4 - Paulo");
            System.out.println("\t5 - Thiago");
            System.out.println("\nDigite o número do personagem que deseja escolher: ");
            
            switch(in.next()){
                    case "1":
                        character += "personagem_gabi/";
                        break;
                    case "2":
                        character += "personagem_lucas_l/";
                        break;
                    case "3": 
                        character += "personagem_lucas_s/";
                        break;
                    case "4":
                        character += "personagem_paulo/";
                        break;
                    case "5":
                        character += "personagem_thiago/";
                        break;
                    default:
                        isEnd = false;
                        System.out.println("A opção que vc tentou acessar não existe!");
            }
        }
        return character;
    }
    
    public static String menuArena (){ 
        boolean isEnd = false;
        String arena = "arenas/";
        
        while(!isEnd){
            isEnd = true;
            
            System.out.println("\nArenas diponiveis:");
            System.out.println("\n\t1 - Arena Gabi");
            System.out.println("\t2 - Arena Lucas L");
            System.out.println("\t3 - Arena Lucas S");
            System.out.println("\t4 - Arena Paulo");
            System.out.println("\t5 - Arena Thiago");
            System.out.println("\nDigite o número da Arena que deseja escolher: ");
            
            switch(in.next()){
                    case "1":
                        arena += "ARENA_GABI.png";
                        break;
                    case "2":
                        arena += "ARENA_LUCAS_L.png";
                        break;
                    case "3": 
                        arena += "ARENA_LUCAS_S.png";
                        break;
                    case "4":
                        arena += "ARENA_PAULO.png";
                        break;
                    case "5":
                        arena += "ARENA_THIAGO.png";
                        break;
                    default:
                        isEnd = false;
                        System.out.println("A opção que vc tentou acessar não existe!");
            }
        }
        return arena;
    }
}
