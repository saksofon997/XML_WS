package agent.exceptions;

public class InvalidEmailOrPasswordError extends Exception{
    public InvalidEmailOrPasswordError(String s){
        super(s);
    }
}