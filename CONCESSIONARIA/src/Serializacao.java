import java.io.IOException;

public interface Serializacao {
    void salvarDados() throws IOException;
    void carregarDados() throws IOException, ClassNotFoundException;
}
