package Util;

public class Validador {

    public static boolean validarEmailInstitucional(String email) {
        return email != null && email.toLowerCase().endsWith("@fiemg.com.br");
    }

    public static boolean isAdminLogin(String email, String senha) {
        return "adm".equalsIgnoreCase(email) && "123321".equals(senha);
    }
}