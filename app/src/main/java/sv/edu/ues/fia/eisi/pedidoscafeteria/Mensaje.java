package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Mensaje{
    private int idMensaje;
    private String idUsuario;
    private int idChat;
    private String texto;
    private String fecha;
    public Mensaje(){}

    public Mensaje(int idMensaje, String idUsuario, int idChat, String texto, String fecha) {
        this.idMensaje = idMensaje;
        this.idUsuario = idUsuario;
        this.idChat = idChat;
        this.texto = texto;
        this.fecha = fecha;
    }
    public Mensaje( String idUsuario, int idChat, String texto, String fecha) {

        this.idUsuario = idUsuario;
        this.idChat = idChat;
        this.texto = texto;
        this.fecha = fecha;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
