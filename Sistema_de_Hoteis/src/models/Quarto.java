package models;
public class Quarto {
    int numero;
    boolean ocupado;
    
    public Quarto(int numero){
        this.numero = numero;
        this.ocupado = true;
    }

    public Quarto(int numero, boolean ocupado) {
        this.numero = numero;
        this.ocupado = ocupado;
    }

    public int getNumero() { return numero; }
    public boolean isOcupado(){ return this.ocupado; }
    public void setOcupado(boolean status){ this.ocupado = status; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quarto(");
        sb.append(numero);
        sb.append(",");
        sb.append(ocupado ? "ocupado": "disponivel");
        sb.append(")");
        return sb.toString();
    }

    
}