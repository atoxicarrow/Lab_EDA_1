import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        String message;
        String encryptedMessage;
        boolean menu = true;

        while (menu) {


            System.out.println("\nMENU");
            System.out.println("1. Encriptar texto");
            System.out.println("2. Desencriptar texto");
            System.out.println("3. ReEncriptar texto");
            System.out.println("4. Search caracter");
            System.out.println("5. Search caracter optimo");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opcion:");
            Scanner sc = new Scanner(System.in);
            int op = sc.nextInt();


            switch (op) {

                case 1: {
                    BigVigenere bV = new BigVigenere();
                    System.out.print("Ingrese el texto: ");
                    Scanner sc2 = new Scanner(System.in);
                    message = sc2.nextLine();


                    System.out.print("El texto encriptado es: " + bV.encrypt(message));

                    break;
                }
                case 2: {
                    System.out.println("Ingrese el texto encriptado: ");
                    Scanner sc2 = new Scanner(System.in);
                    encryptedMessage = sc2.nextLine();
                    BigVigenere bV = new BigVigenere();
                    System.out.print("El texto desencriptado es: " + bV.decrypt(encryptedMessage));
                    break;
                }
                case 3: {
                    BigVigenere bV = new BigVigenere();
                    bV.reEncrypt();
                    break;
                }
                case 4: {
                    Scanner sc2 = new Scanner(System.in);
                    BigVigenere bV = new BigVigenere();
                    int position;
                    do {
                        System.out.println("Ingrese la posición del caracter que quere buscar (0 -- 4095)");
                        position = sc2.nextInt();
                    } while (position < 0 || position > 4095);


                    char letra = bV.search(position);
                        System.out.print("El caracter en la posicion " + position + " es: " + letra);
                }
                case 5: {
                    Scanner sc2 = new Scanner(System.in);
                    BigVigenere bV = new BigVigenere();
                    int position;
                    do {
                        System.out.println("Ingrese la posición del caracter que quere buscar (0 -- 4095)");
                        position = sc2.nextInt();
                    } while (position < 0 || position > 4095);

                    char letra = bV.search(position);
                    System.out.print("El caracter en la posicion " + position + " es: " + letra);
                }
                case 6: {
                    menu = false;
                    break;
                }
            }
        }
    }
}

