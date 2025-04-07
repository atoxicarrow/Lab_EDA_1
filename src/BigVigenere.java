import java.util.Scanner;

public class BigVigenere {
    //atributitos
    int[] key;
    String caracteres = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789";
    String numericKey;
    char[][]alphabet = new char[64][64];
    Scanner sc = new Scanner(System.in);


    //CONSTRUCTORES______________________________________________
    public BigVigenere(){
        System.out.print("Ingrese la Key: ");
        numericKey = sc.nextLine();
        execute(numericKey);
    }

    public BigVigenere(String numericKey) {
        execute(numericKey);
    }

    private void execute(String numericKey) {
        key = new int[numericKey.length()];

        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                alphabet[i][j] = caracteres.charAt((i + j) % caracteres.length());
            }
        }

        for (int i = 0; i < numericKey.length(); i++) {
            char aux = numericKey.charAt(i);
            for (int fila = 0; fila < 1; fila++) {
                for (int columna = 0; columna < caracteres.length(); columna++) {
                    if (alphabet[fila][columna] == aux) {
                        key[i] = columna;
                    }
                }
            }
        }
    }

    //METODOS______________________________________________

    public String encrypt(String message){
        int[] arregloAux = new int [message.length()];

        if (key.length != message.length()) {
            int[] keyAux = new int[message.length()];

            for (int i = 0; i < message.length(); i++) {
                keyAux[i] = key[i % key.length];
            }
            key = keyAux;
        }



        for(int i = 0; i < message.length(); i++){
            for(int j = 0; j < caracteres.length(); j++){
                if(caracteres.charAt(j) == message.charAt(i)){
                    arregloAux[i] = j;
                }
            }
        }

        String auxmessage=null;

        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) == ' '){
                if(auxmessage == null){
                    auxmessage = " ";
                } else {
                    auxmessage += " ";
                }
                continue;
            }
            if(auxmessage == null) {
                auxmessage = String.valueOf(alphabet[arregloAux[i]][key[i]]);
            } else {
                auxmessage = auxmessage + alphabet[arregloAux[i]][key[i]];
            }
        }


        return auxmessage;
    }

    public String decrypt(String encryptedMessage){
        int[] arregloAux = new int [encryptedMessage.length()];

        if (key.length != encryptedMessage.length()) {
            int[] keyAux = new int[encryptedMessage.length()];

            for (int i = 0; i < encryptedMessage.length(); i++) {
                keyAux[i] = key[i % key.length];
            }
            key = keyAux;
        }

        for(int i = 0; i < encryptedMessage.length(); i++){
            for(int j = 0; j < caracteres.length(); j++){
                if(encryptedMessage.charAt(i) == alphabet[j][key[i]]){
                    arregloAux[i] = j;
                    break;
                }
            }
        }
        String auxmessage=null;

        for(int i = 0; i < encryptedMessage.length(); i++) {
            if(encryptedMessage.charAt(i) == ' '){
                if(auxmessage == null){
                    auxmessage = " ";
                } else {
                    auxmessage += " ";
                }
                continue;
            }

            if(auxmessage == null) {
                auxmessage = String.valueOf(alphabet[arregloAux[i]][0]);
            }else{
                auxmessage = auxmessage + alphabet[arregloAux[i]][0];
            }
        }
        return auxmessage;
    }


    public void reEncrypt(){
        String encryptedMessage;
        System.out.print("Ingrese el Texto encriptado: ");
        encryptedMessage = sc.nextLine();
        String message = decrypt(encryptedMessage);


        System.out.print("Ingrese la nueva Key: ");
        numericKey = sc.nextLine();
        execute(numericKey);
        System.out.print("Mensaje reencriptado: " + encrypt(message));


    }


    public char search(int position){
        char letra = '_';
        int iter = 0;
        for(int i=0; i<64; i++){
            for(int j=0; j<64; j++){
                if(position == iter){
                    letra = alphabet[i][j];
                    iter++;
                    break;
                }else{
                    iter++;
                }
            }
        }
        return letra;
    }


    public char optimalSearch(int position){
        int columna= position % 64;
        int fila = position /64;

        char letra= alphabet[fila][columna];

        return letra;
    }


}

