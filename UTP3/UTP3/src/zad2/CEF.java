package zad2;
import java.io.IOException;
import java.util.function.*;
interface CEF <T,R> extends Function <T,R>{
    R checkedApply(T arg) throws IOException;

    default R apply(T arg){
        try{
            return checkedApply(arg);
        }catch (IOException exc){
            exc.printStackTrace();
        }
        return null;
    }
}