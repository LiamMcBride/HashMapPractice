import java.util.Random;
import java.time.*;

public class Project_Runner {

    public static void main(String[] args) {
        LList[] map = new LList[10];
        for(int i = 0; i < 10; i++) {
            map[i] = new LList();
        }
        
        Random rand = new Random();
        int find_key = 0;
        
        for(int i = 0; i < 1000; i++) {
            int randomNum = rand.nextInt((99 - 0) + 1) + 0;
            if(i == 673) {
                find_key = randomNum;
            }
            
            HashObject hObj = new HashObject(randomNum, i + "");
            
            map[h(hObj.getKey())].append(hObj);
        }
        
        for(LList list : map) {
            System.out.println(list.toString());
        }
        
        System.out.println("Looking for: " + find_key);
        
        Instant start = Instant.now();
        
        LList checkList = map[h(find_key)];
        
        while(!checkList.isAtEnd() && checkList.getValue().getKey() != find_key) {
            System.out.println(checkList.getValue().toString());
            checkList.next();
        }
        
        Instant end = Instant.now();
        Duration durr = Duration.between(start, end);
        System.out.println("Found " + checkList.getValue().toString());
        System.out.println(durr.toString());
        
    }   
    
    public static int h(int num) {
        return num % 10;
    }
    

}
