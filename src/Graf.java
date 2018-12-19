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


    private boolean frozen = false;
    private double evaluatedValue;

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

    public void setLocation(wierzcholek w) throws Exception {
        odwiedzone_wierzcholki.add(w);




        if (plecak.czy_wysarczajaco_miejsca(w.przedmiot))
        {
            plecak.dodaj_przedmiot(w.przedmiot);
//            kiNode.Knapsack.Add(kiNode.Item);
        }
        else
        {
            throw new Exception("KONTROLA");
        }
        obecny = w;
    }






    public wierzcholek selectNextNode()
    {
        Random random=new Random();

        List<wierzcholek> dostepne_wierzcholki = new ArrayList<>();


//        NodeList availableNodes = new NodeList();

//        Profiler.StartEvent("Select available nodes");
        //wybieramy dostepne wezly:
        for (wierzcholek w : wszystkie_wierzcholki)
        {
            if (!odwiedzone_wierzcholki.contains(w))
            {
                if (plecak.czy_wysarczajaco_miejsca(w.przedmiot))
                {
                    dostepne_wierzcholki.add(w);
                }
            }
        }
//        Profiler.FinishEvent("Select available nodes");
        int count = dostepne_wierzcholki.size();
        double totalAtr = 0;
        double[] atrMap = new double[count];

        if (random.nextDouble() > algorytm_mrowkowy.Q0)
        {
//            Profiler.StartEvent("Exploration");
            wierzcholek tmpNode = null;
            for (int i = 0; i < count; i++)
            {
                tmpNode = dostepne_wierzcholki.get(i);
                totalAtr += tmpNode.oblicz_atrakcyjnosc(tmpNode);
                atrMap[i] = totalAtr;
            }

            ///losujemy wartoæ z mapy atrakcyjnosci
            double rand = random.nextDouble() * totalAtr;
            for (int i = 0; i < count; i++)
            {
                if (rand < atrMap[i])
                {
//                    Profiler.FinishEvent("Exploration");
                    return dostepne_wierzcholki.get(i);
                }
            }
//            Profiler.FinishEvent("Exploration");
            return null;
        }
        else
        {
//            Profiler.StartEvent("Exploitation");
            if (dostepne_wierzcholki.size() == 0)
                return null;
            wierzcholek tmpNode = dostepne_wierzcholki.get(0);
            double tmp, tmpMax = Double.MIN_VALUE;
            for(wierzcholek w : dostepne_wierzcholki)
            {
                tmp = w.oblicz_atrakcyjnosc(w);
                if(tmp > tmpMax)
                {
                    tmpMax = tmp;
                    tmpNode = w;
                }
            }
//            Profiler.FinishEvent("Exploitation");
            return tmpNode;
        }
    }


    public double evaluateGoalFunction()
    {
        return evaluate();
    }


    public double evaluate()
    {
        if (frozen)
        {
            return evaluatedValue;
        }
        double value = 0;
//        for (Knapsack knapsack : knapsacks)
//        {
            for(przedmiot item : plecak.lista_przedmiotow)
            {
                value += item.cena;
            }
//        }
        return value;
    }




    public void run() throws Exception {
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



