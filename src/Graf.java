import java.util.*;

//
//PodejĹ›cie wÄ™zĹ‚owe znacznie obniĹĽa koszt algorytmu. System nie musi pamiÄ™taÄ‡
//        wartoĹ›ci Ĺ›ladu fermonowego na wszystkich krawÄ™dziach, ktĂłrych liczba w grafie peĹ‚nym
//        wynosi nÂ·(nâ�’1)
//        2
//        , a jedynie na n wÄ™zĹ‚ach.


//class krawedz {
//    wierzcholek vertex_start, vertex_end;
//    double koszt;
//    double ilosc_feromonu;
//
//    krawedz(wierzcholek vertex_start, double koszt, wierzcholek vertex_end) {
//        this.vertex_start = vertex_start;
//        this.vertex_end = vertex_end;
//        this.koszt = koszt;
//    }
//
//
//    double wylicz_koszt(wierzcholek vertex_start,wierzcholek vertex_end){
//        return 0.0;
//    }
//
//}


public class Graf {


    double alfa;
    double beta;
    int N;

    public List<wierzcholek> lista_wierzcholkow = new ArrayList<>();



//    public List<krawedz> lista_krawedzi = new ArrayList<>();



//    List<krawedz> utworz_krawedzie(List<wierzcholek> lista_wierzcholkow){
//        List<krawedz> lista_krawedzi = new ArrayList<>();

//        for (wierzcholek w_pocz: lista_wierzcholkow) {
//            for (wierzcholek w_konc: lista_wierzcholkow) {
//                if(w_pocz!=w_konc){
//                    lista_krawedzi.add(new krawedz(w_pocz,1.0,w_konc));
//                }
//            }
//        }


//        return lista_krawedzi;
//    }

}



