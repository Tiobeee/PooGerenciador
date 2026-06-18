public class OrdemServico {
    private int id;
    private String cliente;
    private String descricao;
    private StatusOS status;

    public OrdemServico(int id, String cliente, String descricao, StatusOS status) {
        this.id = id;
        this.cliente = cliente;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public StatusOS getStatus() { return status; }
    public void setStatus(StatusOS status) { this.status = status; }

       public String formatarParaArquivo() {
        return id + ";" + cliente + ";" + descricao + ";" + status;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] | Cliente: %-15s | Status: %-12s | Descrição: %s", 
                id, cliente, status, descricao);
    }
}
