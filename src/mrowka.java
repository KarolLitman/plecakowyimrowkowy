import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class mrowka {


//    int Nmax; //liczba maksymalnych krokow jakie mrowka bedzie mogla wykonac


    private boolean frozen = false;
    private double evaluatedValue;

    mrowka(ArrayList<wierzcholek> wszystkie_wierzcholki,problem_plecakowy plecak){
        this.wszystkie_wierzcholki=wszystkie_wierzcholki;
        this.odwiedzone_wierzcholki=new ArrayList<>(wszystkie_wierzcholki);
        this.plecak=plecak;
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

    public void odwiedz_wierzcholek(wierzcholek w) throws Exception {
        odwiedzone_wierzcholki.add(w);



        if (plecak.czy_wysarczajaco_miejsca(w.przedmiot))
        {
            plecak.dodaj_przedmiot(w.przedmiot);
        }
        else
        {
            throw new Exception("Brak miejca w plecaku");
        }
        obecny = w;
    }





    public wierzcholek wybierz_nastepny_wierzcholek()
    {
        Random random=new Random();

        List<wierzcholek> dostepne_wierzcholki = new ArrayList<>();



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


        if (random.nextDouble() > algorytm_mrowkowy.Q0)
        {

            int count = dostepne_wierzcholki.size();
            double totalAtr = 0;
            double[] atrMap = new double[count];


            wierzcholek tmpNode = null;
            for (int i = 0; i < count; i++)
            {
                tmpNode = dostepne_wierzcholki.get(i);
                totalAtr += tmpNode.oblicz_atrakcyjnosc(tmpNode);
                atrMap[i] = totalAtr;
            }

            double rand = random.nextDouble() * totalAtr;
            for (int i = 0; i < count; i++)
            {
                if (rand < atrMap[i])
                {
                    return dostepne_wierzcholki.get(i);
                }
            }
            return null;
        }
        else
        {
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
            return tmpNode;
        }
    }




    public double rozwiazanie()
    {
        if (frozen)
        {
            return evaluatedValue;
        }
        double value = 0;


            for(przedmiot p : plecak.lista_przedmiotow)
            {
                value += p.cena;
            }

        return value;
    }




    public void run() throws Exception {
        wierzcholek w;
        while ((w = wybierz_nastepny_wierzcholek()) != null)
        {
            this.odwiedz_wierzcholek(w);
        }

        System.out.println(rozwiazanie());
        System.out.println(plecak);
    }

    public void reset()
    {
//        odwiedzone_wierzcholki.clear();
    }





}
