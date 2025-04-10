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
            System.out.println("7. Evaluar rendimiento con distintas claves");
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
                    String numericKey = " ";
                    BigVigenere bV = new BigVigenere(numericKey);
                    int position;

                    do {
                        System.out.println("Ingrese la posición del caracter que quere buscar (0 -- 4095)");
                        position = sc2.nextInt();
                    } while (position < 0 || position > 4095);


                    char letra = bV.search(position);
                        System.out.print("El caracter en la posicion " + position + " es: " + letra);
                        break;
                }
                case 5: {
                    Scanner sc2 = new Scanner(System.in);
                    String numericKey = " ";
                    BigVigenere bV = new BigVigenere(numericKey);
                    int position;

                    do {
                        System.out.println("Ingrese la posición del caracter que quere buscar (0 -- 4095)");
                        position = sc2.nextInt();
                    } while (position < 0 || position > 4095);

                    char letra = bV.optimalSearch(position);
                    System.out.println("El caracter en la posicion " + position + " es: " + letra );
                    break;
                }
                case 6: {
                    menu = false;
                    break;
                }
                case 7: {
                    evaluarTiempos();
                    break;
                }
            }
        }
    }
    public static void evaluarTiempos() {
        int[] longitudes = {10, 50, 100, 500, 1000, 5000};
        String caracteres = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789 ";
        StringBuilder mensajeBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            mensajeBuilder.append(caracteres.charAt((int)(Math.random() * caracteres.length())));
        }
        String mensaje = mensajeBuilder.toString();

        System.out.println("TamañoClave | TiempoEncriptado (ms) | TiempoDesencriptado (ms)");

        for (int l : longitudes) {
            StringBuilder claveBuilder = new StringBuilder();
            for (int i = 0; i < l; i++) {
                claveBuilder.append(caracteres.charAt((int)(Math.random() * 63)));
            }
            String clave = claveBuilder.toString();
            BigVigenere bv = new BigVigenere(clave);

            long inicio = System.nanoTime();
            String encriptado = bv.encrypt(mensaje);
            long fin = System.nanoTime();
            long tiempoEncrypt = (fin - inicio) / 1_000_000;

            inicio = System.nanoTime();
            bv.decrypt(encriptado);
            fin = System.nanoTime();
            long tiempoDecrypt = (fin - inicio) / 1_000_000;

            System.out.printf("%11d | %21d | %23d\n", l, tiempoEncrypt, tiempoDecrypt);
        }
    }

}

