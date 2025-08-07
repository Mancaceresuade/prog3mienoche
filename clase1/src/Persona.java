public class Persona {
    private int id;
    private String nombre;
    private static final String SALUDO = "Hola, soy una persona";
    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public static String saludar() {
        return SALUDO;
    }
}
