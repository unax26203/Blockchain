import java.util.Date;
public class Block {
    public String hash;
    public String previousHash;
    private String datos; //en este caso sera un mensaje
    private long timeStamp; //en milisegundos

    private int nonce;

    public Block(String datos, String previousHash){
        this.datos=datos;
        this.previousHash=previousHash;
        this.timeStamp = new Date().getTime();
        this.hash=calcularHash();
    }
    public String calcularHash(){
        String hashcalculado= StringUtil.applySha256(
                previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + datos
        );
        return  hashcalculado;
    }
    public void minarBloque(int dificultad){
        String target = new String(new char[dificultad]).replace('\0', '0');
        while (!hash.substring(0,dificultad).equals(target)){
            nonce ++;
            hash = calcularHash();
        }
        System.out.println("Bloque Minado : " + hash);
    }
}



