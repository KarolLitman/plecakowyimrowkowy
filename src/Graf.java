import java.util.*;

//
//PodejĹ›cie wÄ™zĹ‚owe znacznie obniĹĽa koszt algorytmu. System nie musi pamiÄ™taÄ‡
//        wartoĹ›ci Ĺ›ladu fermonowego na wszystkich krawÄ™dziach, ktĂłrych liczba w grafie peĹ‚nym
//        wynosi nÂ·(nâ�’1)
//        2
//        , a jedynie na n wÄ™zĹ‚ach.

class przedmiot{
    String nazwa;
    double masa;
    double cena;
    double stosunek;
    przedmiot(String nazwa,double masa, double cena) {

    this.nazwa=nazwa;
    this.masa=masa;
    this.cena=cena;
    stosunek=oblicz_stosunek();
    }

    double oblicz_stosunek(){
        return cena/masa;
    }

    }


    class problem_plecakowy{
        public List<przedmiot> lista_przedmiotow = new ArrayList<>();
        double dopuszczalna_masa;

    }

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
    double koszt;
    double ilosc_feromonu;

    krawedz(wierzcholek vertex_start, double koszt, wierzcholek vertex_end) {
        this.vertex_start = vertex_start;
        this.vertex_end = vertex_end;
        this.koszt = koszt;
    }


    double wylicz_koszt(wierzcholek vertex_start,wierzcholek vertex_end){
        return 0.0;
    }

}


class mrowka {


    int Nmax; //liczba maksymalnych krokow jakie mrowka bedzie mogla wykonac

//    double alfa;
//    double beta;
//    int N;

    public List<wierzcholek> lista_tabu = new ArrayList<>();

 

    void generuj_feromon(int sposob){
//        https://www.ii.pwr.edu.pl/~kwasnicka/lindaabrichwww/description.html
        switch (sposob) {
            case 1:
                System.out.println("gestosciowy");
                break;
            case 2:
                System.out.println("ilosciowy");
                break;
            case 3:
                System.out.println("cykliczny");
                break;
        }
    }
 
    








}


public class Graf {


    public List<wierzcholek> lista_wierzcholkow = new ArrayList<>();
    public List<krawedz> lista_krawedzi = new ArrayList<>();



    List<krawedz> utworz_krawedzie(List<wierzcholek> lista_wierzcholkow){
        List<krawedz> lista_krawedzi = new ArrayList<>();

        for (wierzcholek w_pocz: lista_wierzcholkow) {
            for (wierzcholek w_konc: lista_wierzcholkow) {
                if(w_pocz!=w_konc){
                    lista_krawedzi.add(new krawedz(w_pocz,1.0,w_konc));
                }
            }
        }


        return lista_krawedzi;
    }

}



