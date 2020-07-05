package sv.edu.ues.fia.eisi.pedidoscafeteria;

public class Chat{
    private int idchat;
    private String idUsuario;
    private String idUsuario2;

    public Chat(){}
    public Chat(int idchat, String idUsuario, String idUsuario2) {
        this.idchat = idchat;
        this.idUsuario = idUsuario;
        this.idUsuario2 = idUsuario2;
    }
    public Chat( String idUsuario, String idUsuario2) {

        this.idUsuario = idUsuario;
        this.idUsuario2 = idUsuario2;
    }

    public int getIdchat() {
        return idchat;
    }

    public void setIdchat(int idchat) {
        this.idchat = idchat;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(String idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }
}
