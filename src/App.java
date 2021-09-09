import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("teste.txt");
        ArrayList<String> linhasFinalizadas = new ArrayList<>();
        List<String> linhas = Files.readAllLines(path);
        
        int linhaAtual =0;
       
         for (String linha : linhas) {

            if(LinhaBalanceada(linha)){
                linhasFinalizadas.add(linhaAtual, linha+"  - OK");
            }
            else{
                linhasFinalizadas.add(linhaAtual, linha+"  - Inválido");
            }
            linhaAtual++;
        } 
        
        Files.write(path, linhasFinalizadas);
        
    }

    public static boolean LinhaBalanceada(String linha){
        Stack<Character> pilhaAbertos = new Stack<>();

        for(int i=0;i<linha.length();i++){
            if(linha.charAt(i) =='(' || linha.charAt(i) =='['|| 
            linha.charAt(i)=='<'|| linha.charAt(i)=='{'){
                pilhaAbertos.push(linha.charAt(i));
            }
            else{
                if(pilhaAbertos.isEmpty())
                    return false;
                if(pilhaAbertos.peek() == '(' && linha.charAt(i)!=')'||
                   pilhaAbertos.peek() == '[' && linha.charAt(i)!=']'||
                   pilhaAbertos.peek() == '<' && linha.charAt(i)!='>'||
                   pilhaAbertos.peek() == '{' && linha.charAt(i)!='}'){

                    return false;
                }
                pilhaAbertos.pop();  //par igual, segue o fluxo para o prox.
            }
        }
        return pilhaAbertos.isEmpty();
    }
}
