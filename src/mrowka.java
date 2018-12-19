import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
