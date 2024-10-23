package mundo;

import java.io.*;
import java.util.ArrayList;

public class ManejadorDePersistenciaTx {
    private Discotienda discotienda;

    public ManejadorDePersistenciaTx(Discotienda discotienda) {
        this.discotienda = discotienda;
    }

    public void guardarDiscotienda() throws IOException {
        PrintWriter escritor = new PrintWriter(new File("./data/discos.txt"));
        for (Disco disco : discotienda.getDiscos()) {
            escritor.println(disco.getNombre() + ";" + disco.getArtista() + ";" + disco.getAnio());
            for (Cancion cancion : disco.getCanciones()) {
                escritor.println(cancion.getNombre() + ";" + cancion.getDuracionMinutos() + ";" + cancion.getDuracionSegundos() + ";" + cancion.getGenero());
            }
        }
        escritor.close();
    }

    public void leerDiscotienda() throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader("./data/discos.txt"));
        String linea;
        ArrayList<Disco> discos = new ArrayList<>();
        Disco discoActual = null;
        while ((linea = lector.readLine()) != null) {
            String[] datos = linea.split(";");
            if (datos.length == 3) {
                discoActual = new Disco(datos[0], datos[1], Integer.parseInt(datos[2]));
                discos.add(discoActual);
            } else if (datos.length == 4 && discoActual != null) {
                Cancion cancion = new Cancion(datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), datos[3]);
                discoActual.getCanciones().add(cancion);
            }
        }
        lector.close();
        discotienda.setDiscos(discos);
    }
}
