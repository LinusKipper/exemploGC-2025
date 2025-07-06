import java.util.*;

public class TabSimb {
    private Stack<Map<String, TS_entry>> escopos;

    public TabSimb() {
        escopos = new Stack<>();
        beginScope(); // escopo global inicial
    }

    public void beginScope() {
        escopos.push(new HashMap<>());
    }

    public void endScope() {
        if (!escopos.isEmpty()) {
            escopos.pop();
        } else {
            System.err.println("Erro: tentativa de sair de escopo inexistente.");
        }
    }

    public void insert(TS_entry nodo) {
        escopos.peek().put(nodo.getId(), nodo);
    }

    public TS_entry pesquisa(String id) {
        for (int i = escopos.size() - 1; i >= 0; i--) {
            TS_entry ent = escopos.get(i).get(id);
            if (ent != null) return ent;
        }
        return null;
    }

    public void listar() {
        System.out.println("\n\n# Listagem da tabela de símbolos:");
        for (int i = 0; i < escopos.size(); i++) {
            System.out.println("Escopo " + i + ":");
            for (TS_entry e : escopos.get(i).values()) {
                System.out.println("  " + e);
            }
        }
    }

    public void geraGlobais() {
        if (escopos.isEmpty()) return;

        for (TS_entry nodo : escopos.firstElement().values()) {
            // assume que todas as globais são inteiras
            System.out.println("_" + nodo.getId() + ":" + "\t.zero 4");
        }
    }
}
