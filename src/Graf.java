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



    void dodaj_przedmiot(String nazwa, double masa, double cena){
        lista_przedmiotow.add(new przedmiot(nazwa, masa, cena));
    }

    void dodaj_przedmiot(przedmiot p){
        lista_przedmiotow.add(p);
    }


        public List<przedmiot> lista_przedmiotow = new ArrayList<>();







        public Double policz_wartosc_plecaka()
        {
            Double tmp=0.0;
            for(przedmiot p : lista_przedmiotow)
            {
                tmp = tmp + p.cena;
            }
            return tmp;
        }

        public boolean czy_wysarczajaco_miejsca(przedmiot p)
        {
            return pozostala_masa >= p.masa;
        }


        double dopuszczalna_masa;

        double pozostala_masa;




    }

class wierzcholek {
    Boolean czy_odwiedzony;
    Double feromon;
    przedmiot przedmiot;

    wierzcholek(przedmiot p) {
        this.przedmiot=p;
        this.feromon=algorytm_mrowkowy.poczatkowy_feromon;
        this.czy_odwiedzony=false;

    }



    public double oblicz_atrakcyjnosc(wierzcholek w)
    {
        return w.feromon * Math.pow((w.przedmiot.cena/w.przedmiot.masa), algorytm_mrowkowy.Beta);
    }




}

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


class mrowka {


    int Nmax; //liczba maksymalnych krokow jakie mrowka bedzie mogla wykonac

//    double alfa;
//    double beta;
//    int N;


    mrowka(ArrayList<wierzcholek> wszystkie_wierzcholki){
        this.wszystkie_wierzcholki=wszystkie_wierzcholki;
        this.odwiedzone_wierzcholki=new ArrayList<>(wszystkie_wierzcholki);
    }



    public ArrayList<wierzcholek> odwiedzone_wierzcholki;
    public ArrayList<wierzcholek> wszystkie_wierzcholki;
wierzcholek obecny;
    problem_plecakowy plecak;





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

    public void setLocation(wierzcholek w)
    {
        odwiedzone_wierzcholki.add(w);




        if (plecak.czy_wysarczajaco_miejsca(w.przedmiot))
        {
            plecak.dodaj_przedmiot(w.przedmiot);
//            kiNode.Knapsack.Add(kiNode.Item);
        }
        else
        {
            throw new AcoException("KONTROLA");
        }
        obecny = w;
    }






    public wierzcholek selectNextNode()
    {
        NodeList availableNodes = new NodeList() ;

        Profiler.StartEvent("Select available nodes");
        //wybieramy dostepne wezly:
        foreach (KnapsackItemNode node in allNodes)
        {
            if (!visitedNodes.Contains(node))
            {
                if (node.Knapsack.IsEnoughPlaceFor(node.Item))
                {
                    availableNodes.Add(node);
                }
            }
        }
        Profiler.FinishEvent("Select available nodes");
        int count = availableNodes.Count;
        double totalAtr = 0;
        double[] atrMap = new double[count];

        if (random.NextDouble() > algorithm.Parameters.Q0)
        {
            Profiler.StartEvent("Exploration");
            KnapsackItemNode tmpNode = null;
            for (int i = 0; i < count; i++)
            {
                tmpNode = (KnapsackItemNode)availableNodes[i];
                totalAtr += algorithm.CalculateAttractiveness(tmpNode);
                atrMap[i] = totalAtr;
            }

            ///losujemy wartoæ z mapy atrakcyjnosci
            double rand = random.NextDouble() * totalAtr;
            for (int i = 0; i < count; i++)
            {
                if (rand < atrMap[i])
                {
                    Profiler.FinishEvent("Exploration");
                    return availableNodes[i];
                }
            }
            Profiler.FinishEvent("Exploration");
            return null;
        }
        else
        {
            Profiler.StartEvent("Exploitation");
            if (availableNodes.Count == 0)
                return null;
            KnapsackItemNode tmpNode = (KnapsackItemNode)availableNodes[0];
            double tmp, tmpMax = double.MinValue;
            foreach(KnapsackItemNode node in availableNodes)
            {
                tmp = algorithm.CalculateAttractiveness(node);
                if(tmp > tmpMax)
                {
                    tmpMax = tmp;
                    tmpNode = node;
                }
            }
            Profiler.FinishEvent("Exploitation");
            return tmpNode;
        }
    }




    public void run()
    {
        ///todo: wybieramy najbardziej obiecuj¹cy wezel, przechodzimy do niego, czynnosc powtarzamy dopóki beda dopuszczalne wezly
        wierzcholek w;
        while ((w = selectNextNode()) != null)
        {
            this.setLocation(w);
        }

        //tu trzeba obliczyc wartosc wszystkich przedmiotow
        solution.freeze(algorithm.mkp.KnapsackList);
    }


}


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



