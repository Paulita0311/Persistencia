package mundo;

import java.io.*;
import java.util.ArrayList;

public class ManejadorDePersistenciaSerializada {
    private Discotienda discotienda;

    public ManejadorDePersistenciaSerializada(Discotienda discotienda) {
        this.discotienda = discotienda;
    }

    public void guardarDiscotienda() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/discos.dat"));
        oos.writeObject(discotienda.getDiscos());
        oos.close();
    }

    public void leerDiscotienda() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/discos.dat"));
        ArrayList<Disco> discos = (ArrayList<Disco>) ois.readObject();
        ois.close();
        discotienda.setDiscos(discos);
    }
}
