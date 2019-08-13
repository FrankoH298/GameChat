package slick2d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author nicolas.fanin
 */
public class ClienteChat {

    // Referencia al socket de conexion con el servidor
    private Socket conexion = null;
    // Direccion IP del servidor de chat.
    private String ipServidor = "127.0.0.1";
    // Puerto TCP del servidor de chat (chat room)
    private int puertoServidor = 2000;

    // Flujo de Entradade caracteres desde el servidor.
    private BufferedReader flujoEntrada = null;
    // Flujo de salida de caracteres hacia el servidor.
    private PrintStream flujoSalida = null;
    
    @SuppressWarnings("unused")
    private Console consola;
    
    public ClienteChat(String direccionIP, String puerto, Console consola) {
        
        this.consola = consola;
        
        if (direccionIP != null) {
            ipServidor = direccionIP;
        }
        
        if (puerto != null) {
            try {
                puertoServidor = Integer.parseInt(puerto);
            } catch (NumberFormatException nfe) {
            }
        }
    }
    
    public void conectar() {
        try {
            // se abre un socket a la dirección IP y puerto indicado .
            conexion = new Socket(ipServidor, puertoServidor);

            // se crea un lector de caracteres para todo lo que se reciba
            // desde el servidor por el socket.
            flujoEntrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            // se crea un flujo para enviar texto al servidor.
            flujoSalida = new PrintStream(conexion.getOutputStream());

            // se inicia un ciclo de lectura infinito.
            Thread t = new Thread(new LectorRemoto());
            t.start();
            
        } catch (Exception e) {
            System.out.println("No se pudo abrir el socket " + ipServidor + ":" + puertoServidor);
            //e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public void EnviarMensaje(String mensaje) {
        flujoSalida.println(mensaje);
    }
    
    private class LectorRemoto implements Runnable {
        
        public void run() {
            // se hace un ciclo infinito leyendo todas las líneas
            // que se vayan recibiendo del servidor.
            while (true) {
                try {
                    String mensaje = flujoEntrada.readLine();
                    Protocol(mensaje);
                    
                }
                catch (SocketException e) {
                    System.out.println("Servidor cerrado.");
                    //e.printStackTrace();
                    System.exit(0);
                    break;
                }
                catch (Exception e) {
                    System.out.println("Error leyendo del servidor");
                    //e.printStackTrace();
                    break;
                }
            }
        }
        
        private void Protocol(String mensaje) {
            
            String action = mensaje.substring(0, 1);
            mensaje = mensaje.substring(1, mensaje.length());
            switch (action) {
                case "C":
                    switch (Integer.parseInt(mensaje)) {
                        case 1:
                            System.out.println("Movio");
                            break;
                    }
                    break;
                default:
                    break;
                case "M":
                    consola.recibirMensaje(mensaje);
            }
        }
    }
    
}
