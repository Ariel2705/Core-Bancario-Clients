package ec.edu.espe.corebancario.clients.enums;

public enum TypeClientEnum {
    NATURAL("NAT", "Natural"),
    JURIDICA("JUD", "Juridico");

    private final String type;
    private final String description;

    private TypeClientEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String gettype() {
        return type;
    }

    public String getDescription() {
        return description;
    }

}
