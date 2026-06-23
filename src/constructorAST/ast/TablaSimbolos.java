package constructorAST.ast;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TablaSimbolos {
    private HashMap<String, Declaracion> tabla;

    public TablaSimbolos() {
        this.tabla = new HashMap<>();
    }

    public void add_id(String id, Declaracion ref) {
        tabla.put(id, ref);
    }

    public Declaracion lookup(String id) {
        return tabla.get(id);
    }

    public boolean contains_id(String id) {
        return tabla.containsKey(id);
    }
    

    public Set<Map.Entry<String, Declaracion>> entrySet(){
        return tabla.entrySet();
    }
}