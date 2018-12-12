import java.util.*;


class wierzcholek {
    String vertex_name;
    String value;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        wierzcholek that = (wierzcholek) o;
        return Objects.equals(vertex_name, that.vertex_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex_name);
    }

    wierzcholek(String vertex_name) {
        this.vertex_name = vertex_name;
    }



    @Override
    public String toString() {
        return "" + vertex_name + ": " + " .";
    }
}

class krawedz {
    wierzcholek vertex_start, vertex_end;
    String predicate;

    Map<String, String> any_key_value_Map;


    krawedz(wierzcholek vertex_start, String predicate, wierzcholek vertex_end) {
        this.vertex_start = vertex_start;
        this.predicate = predicate;
        this.any_key_value_Map = new HashMap<>();
        this.vertex_end = vertex_end;
    }

    krawedz() {
    }

    @Override
    public String toString() {
        return vertex_start.vertex_name + " " + predicate + " " + vertex_end.vertex_name;
    }

}

public class Graf {

    public List<wierzcholek> lista_wierzcholkow = new ArrayList<>();
    public List<krawedz> lista_krawedzi = new ArrayList<>();





}



