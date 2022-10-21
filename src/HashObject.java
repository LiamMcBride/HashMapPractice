
public class HashObject {
    private int key;
    private String value;
    
    public HashObject(int k, String v) {
        key = k;
        value = v;        
    }
    
    public int getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }
    
    public String toString() {
        return "{" + key + " : " + value + "}"; 
    }
}
