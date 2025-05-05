package Models;

public class Perguntas {
    private int id;
    private String texto;
    private String tipo; // aberta, fechada, condicional
    private Integer perguntaCondicionalId; // se houver
    private String valorCondicional;       // "sim", "não", etc.

    // Construtores, getters e setters

public Perguntas() {}

public Perguntas(int id, String texto, String tipo, Integer perguntaCondicionalId, String valorCondicional) {
    this.id = id;
    this.texto = texto;
    this.tipo = tipo;
    this.perguntaCondicionalId = perguntaCondicionalId;
    this.valorCondicional = valorCondicional;
}

// Getters e Setters

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getTexto() {
    return texto;
}

public void setTexto(String texto) {
    this.texto = texto;
}

public String getTipo() {
    return tipo;
}

public void setTipo(String tipo) {
    this.tipo = tipo;
}

public Integer getPerguntaCondicionalId() {
    return perguntaCondicionalId;
}

public void setPerguntaCondicionalId(Integer perguntaCondicionalId) {
    this.perguntaCondicionalId = perguntaCondicionalId;
}

public String getValorCondicional() {
    return valorCondicional;
}

public void setValorCondicional(String valorCondicional) {
    this.valorCondicional = valorCondicional;
}
}