package cpe526_assignmant3;

import java.util.*;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Fatma
 */
public class CPE526_Assignmant3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        HashMap<String, Integer> Analysis = new HashMap<String, Integer>();
        String [] replacementRule = null;
        Scanner in = new Scanner(System.in);
        String cipherText, plainText = "" , replacement = "";
        int count, choice = 0;
        
        System.out.println("Cipher Text:");
        cipherText = in.nextLine();
        plainText = cipherText;
        
        for(Character c : cipherText.toCharArray()){
            if (Character.isLetter(c) && !Analysis.containsKey(c)){
              count =  StringUtils.countMatches(cipherText,c);
              Analysis.put(String.valueOf(Character.toUpperCase(c)), count);
            }
        }

        
        ArrayList<Entry<String,Integer>> AnalysisOrder = new ArrayList<Entry<String,Integer>>(Analysis.entrySet());
        Collections.sort(AnalysisOrder,new Comparator<Entry<String,Integer>>() {
            public int compare(Entry<String,Integer> First, Entry<String,Integer> Second){
              return First.getValue().compareTo(Second.getValue());
            }
        });

        System.out.println("Analysis:");
        for(int i = AnalysisOrder.size()-1; i>=0; i--){
            System.out.print(AnalysisOrder.get(i).getKey()+"->"+AnalysisOrder.get(i).getValue()+", ");
        }
        
        do{
            System.out.println("");
            System.out.println("Options:");
            System.out.println("1) Take replace rule");
            System.out.println("2) Exit");
            System.out.print("Option> ");
            choice = in.nextInt();
            if (choice == 1){
               System.out.print("Enter replacement rule-> "); 
               do{
                   replacement = "";
                   replacement += in.next();
                }while(!in.hasNextLine());

                replacementRule = replacement.split(",");

                for(int i =0; i< replacementRule.length; i++){
                    for(Character c : plainText.toCharArray()){
                        if (Character.isLetter(c)){
                            if(Character.toUpperCase(c) == Character.toUpperCase(replacementRule[i].toCharArray() [0])){
                                plainText = plainText.replace(c, replacementRule[i].toCharArray()[2]);
                            }
                        }
                    }
                }
           
            System.out.println("Cipher Text:");
            System.out.println(cipherText);
            System.out.println("Analysis:");
            for(int i = AnalysisOrder.size()-1; i>=0; i--){
                System.out.print(AnalysisOrder.get(i).getKey()+"->"+AnalysisOrder.get(i).getValue()+", ");
            }
            System.out.println("");
            System.out.println("Plain Text");
            System.out.println(plainText);

            } 
        }while(choice !=2);
    }
}
