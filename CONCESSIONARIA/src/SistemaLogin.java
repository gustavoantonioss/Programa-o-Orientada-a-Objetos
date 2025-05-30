import java.io.*;
import java.util.*;

public class SistemaLogin {
    private List<Usuario> usuarios = new ArrayList<>();

    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public boolean autenticar(String login, String senha) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
    public boolean usuarioExiste(String login) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    public void salvarUsuarios() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(usuarios);
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarUsuarios() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.dat"))) {
            usuarios = (List<Usuario>) ois.readObject();
        }
    }
}
