package Models;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private boolean primeiraVez;
    private boolean isAdmin;

    // Construtores, getters e setters

public Usuario() {}

public Usuario(int id, String email, String senha, boolean primeiraVez, boolean isAdmin) {
    this.id = id;
    this.email = email;
    this.senha = senha;
    this.primeiraVez = primeiraVez;
    this.isAdmin = isAdmin;
}

// Getters e Setters

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getSenha() {
    return senha;
}

public void setSenha(String senha) {
    this.senha = senha;
}

public boolean isPrimeiraVez() {
    return primeiraVez;
}

public void setPrimeiraVez(boolean primeiraVez) {
    this.primeiraVez = primeiraVez;
}

public boolean isAdmin() {
    return isAdmin;
}

public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
}
}
