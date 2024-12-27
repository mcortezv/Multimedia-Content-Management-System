package spotifyPresentacion;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */
public class SistemaSpotify {

    public static void main(String[] args) {
        if(!"UTF-8".equals(System.out.charset().displayName())){
            System.setOut(new PrintStream( new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        }
        IFachadaPersistencia persistencia = new FachadaPersistencia();
        MenuPrincipal menu = new MenuPrincipal(persistencia);
        menu.mostrarMenu();
    }
}
