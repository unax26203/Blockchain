import java.util.ArrayList;
import com.google.gson.GsonBuilder;import com.google.gson.GsonBuilder;
public class Main {
    public static int dificultad=6; //El aumento de complejidad computacional sube drasticamente para numeros > 6
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static void main(String[] args) {

        blockchain.add(new Block("Hola soy el primer bloque", "0"));
        System.out.println("Intentando minar el bloque 1.. ");
        blockchain.get(0).minarBloque(dificultad);
        blockchain.add(new Block("Hola soy el primer bloque", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Intentando minar el bloque 2.. ");
        blockchain.get(1).minarBloque(dificultad);
        blockchain.add(new Block("Hola soy el primer bloque", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Intentando minar el bloque 3.. ");
        blockchain.get(2).minarBloque(dificultad);

        System.out.println("\nLa blockchain es valida: " + esValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }
    public static Boolean esValid(){
        Block bloqueActual;
        Block bloquePrevio;
        String hashTarget = new String(new char[dificultad]).replace('\0', '0');

        for(int i=1;i<blockchain.size();i++){
            bloqueActual = blockchain.get(i);
            bloquePrevio = blockchain.get(i-1);

            if(!bloqueActual.hash.equals(bloqueActual.calcularHash())){
                System.out.println("El hash actual no coincide");
                return false;
            }
            if(!bloquePrevio.hash.equals(bloqueActual.previousHash)){
                System.out.println("El hash previo no coincide");
                return false;
            }
            //check if hash is solved
            if(!bloqueActual.hash.substring( 0, dificultad).equals(hashTarget)) {
                System.out.println("Este bloque ha sido minado");
                return false;
            }
        }
        return true;
    }
}