import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Concessionaria implements Serializable {
    private List<Veiculo> veiculos;

    public Concessionaria() {
        veiculos = new ArrayList<>();
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void adicionarVeiculo(Veiculo v) {
        veiculos.add(v);
    }

    public Veiculo buscarVeiculoPorId(int id) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getId() == id) {
                return veiculos.get(i);
            }
        }
        return null;
    }

    public boolean removerVeiculo(int id) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getId() == id) {
                veiculos.remove(i);
                return true;
            }
        }
        return false;
    }

  public void listarVeiculos() {
    if (veiculos.isEmpty()) {
        System.out.println("Nenhum veículo cadastrado.");
        return;
    }
    
    for (int i = 0; i < veiculos.size(); i++) {
        Veiculo v = veiculos.get(i);
        System.out.println("ID: " + v.getId() + ", Modelo: " + v.getModelo() + ", Marca: " + v.getMarca() + ", Ano: " + v.getAno());
    }
}

    public void salvarDados() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("veiculos.dat"))) {
            oos.writeObject(veiculos);
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarDados() throws IOException, ClassNotFoundException {
        File file = new File("veiculos.dat");
        if (!file.exists()) {
            veiculos = new ArrayList<>();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            veiculos = (List<Veiculo>) ois.readObject();
        }
    }
}
