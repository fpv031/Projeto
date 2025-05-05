package Models;

public class Resposta {
    private int id;
    private int usuarioId;
    private int perguntaId;
    private String resposta;

    // Construtores, getters e setters

public Resposta() {}

public Resposta(int id, int usuarioId, int perguntaId, String resposta) {
    this.id = id;
    this.usuarioId = usuarioId;
    this.perguntaId = perguntaId;
    this.resposta = resposta;
}

// Getters e Setters

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public int getUsuarioId() {
    return usuarioId;
}

public void setUsuarioId(int usuarioId) {
    this.usuarioId = usuarioId;
}

public int getPerguntaId() {
    return perguntaId;
}

public void setPerguntaId(int perguntaId) {
    this.perguntaId = perguntaId;
}

public String getResposta() {
    return resposta;
}

public void setResposta(String resposta) {
    this.resposta = resposta;
}
}