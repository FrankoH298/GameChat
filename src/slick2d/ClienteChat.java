package slick2d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteChat {

    // Referencia al socket de conexion con el servidor
    private Socket conexion = null;
    // Direccion IP del servidor de chat.
    private String ipServidor = "192.168.60.177";
    // Puerto TCP del servidor de chat (chat room)
    private int puertoServidor = 7666;

    // Flujo de Entrada de caracteres desde el servidor.
    private BufferedReader flujoEntrada = null;
    // Flujo de salida de caracteres hacia el servidor.
    private PrintStream flujoSalida = null;

    @SuppressWarnings("unused")
    private final Console consola;
    private final Slick2D slick;

    public ClienteChat(String direccionIP, String puerto, Console consola, Slick2D slick) {
        this.slick = slick;
        this.consola = consola;

        if (direccionIP != null) {
            ipServidor = direccionIP;
        }

        if (puerto != null) {
            puertoServidor = Integer.parseInt(puerto);
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

        } catch (IOException e) {
            System.out.println("No se pudo abrir el socket " + ipServidor + ":" + puertoServidor);
            //e.printStackTrace();
            System.exit(-1);
        }
    }

    public void EnviarMensaje(String mensaje) {
        Commands(mensaje);
    }

    private void Commands(String mensaje) {
        if (mensaje.length() > 1) {
            String action = mensaje.substring(1, 2);
            String mensajeNuevo = mensaje.substring(2, mensaje.length());
            switch (action) {
                case "/":
                    if (mensajeNuevo.toLowerCase().equals("salir")) {
                        consola.recibirMensaje("Cerrando cliente.");
                        System.exit(0);
                    }
                    break;
                default:
                    flujoSalida.println(mensaje);
                    break;
            }
        } else {
            flujoSalida.println(mensaje);
        }
    }

    private class LectorRemoto implements Runnable {

        @Override
        public void run() {
            // se hace un ciclo infinito leyendo todas las líneas
            // que se vayan recibiendo del servidor.
            while (true) {
                try {
                    String mensaje = flujoEntrada.readLine();
                    //System.out.println(mensaje);
                    Protocol(mensaje);
                } catch (SocketException e) {
                    consola.recibirMensaje("Servidor cerrado.");
                    consola.recibirMensaje("Cerrando cliente en 2 segundos.");
                    try {
                        //e.printStackTrace();
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ClienteChat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.exit(0);
                    break;
                } catch (IOException e) {
                    System.out.println("Error leyendo del servidor");
                    //e.printStackTrace();
                    break;
                }
            }
        }

        private void Protocol(String mensaje) {
            int index = 0;
            int temp = 0;
            int userIndex = 0;
            float x = 0;
            float y = 0;
            float msg = 0f;
            String action = "";
            if (mensaje != null) {
                if (mensaje.length() > 0) {
                    action = mensaje.substring(0, 1);
                    mensaje = mensaje.substring(1, mensaje.length());
                }
                switch (action) {
                    case "M":
                        consola.recibirMensaje(mensaje);
                        break;
                    case "D":
                        for (int i = 0; i < mensaje.length(); i++) {
                            if (mensaje.charAt(i) == '@') {
                                msg = Float.parseFloat(mensaje.substring(index, i));
                                index = i + 1;
                                temp++;
                            }
                            switch (temp) {
                                case 1:
                                    userIndex = Math.round(msg);
                                    break;
                                case 2:
                                    x = msg;
                                    break;
                                case 3:
                                    y = msg;
                                    break;
                            }
                        }
                        slick.createBot(userIndex, x, y);
                        break;
                    case "N":
                        for (int i = 0; i < mensaje.length(); i++) {
                            if (mensaje.charAt(i) == '@') {
                                msg = Float.parseFloat(mensaje.substring(index, i));
                                index = i + 1;
                                temp++;
                            }
                            switch (temp) {
                                case 1:
                                    userIndex = Math.round(msg);
                                    break;
                                case 2:
                                    x = msg;
                                    break;
                                case 3:
                                    y = msg;
                                    break;
                            }
                        }
                        slick.updateBot(userIndex, x, y);
                        break;
                    case "G":
                        slick.removeBot(Integer.parseInt(mensaje));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
