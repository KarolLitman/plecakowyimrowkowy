import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class mrowka {


//    int Nmax; //liczba maksymalnych krokow jakie mrowka bedzie mogla wykonac


    public ArrayList<wierzcholek> odwiedzone_wierzcholki;
    public ArrayList<wierzcholek> wszystkie_wierzcholki;
    wierzcholek obecny;
    problem_plecakowy plecak;


    mrowka(ArrayList<wierzcholek> wszystkie_wierzcholki){
        this.wszystkie_wierzcholki=wszystkie_wierzcholki;
        this.odwiedzone_wierzcholki=new ArrayList<>();
//        this.plecak=plecak;
        this.plecak=new problem_plecakowy();
    }








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


        if (plecak.czy_wystarczajaco_miejsca(w.przedmiot)) //sprawdzenie bo pierwszy wierzcholek jest losowany...
        {
//            System.out.println("przed odwiedzeniem "+plecak.pozostala_masa);
            plecak.pozostala_masa-=w.przedmiot.masa;
            plecak.dodaj_przedmiot(w.przedmiot);
//            System.out.println("po odwiedzeniu "+plecak.pozostala_masa);

        }
        else
        {
//            System.out.println("czemu");

//            throw new Exception("Brak miejca w plecaku");
        }
        obecny = w;
    }





    public wierzcholek wybierz_nastepny_wierzcholek()
    {
        Random random=new Random();

        List<wierzcholek> dostepne_wierzcholki = new ArrayList<>();

//        System.out.println("wejscie do funkcji wybierz");

        for (wierzcholek w : wszystkie_wierzcholki)
        {

            if (!odwiedzone_wierzcholki.contains(w))
            {
                if (plecak.czy_wystarczajaco_miejsca(w.przedmiot))
                {
//                    System.out.println("poz masa"+plecak.pozostala_masa);
//                    System.out.println("czy_wyst "+w.przedmiot.masa);

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

        double value = 0;


            for(przedmiot p : plecak.przedmioty_w_plecaku)
            {
                value += p.cena;
            }

        return value;
    }




    public void run() throws Exception {
        wierzcholek w;
        while ((w = wybierz_nastepny_wierzcholek()) != null)
        {
//            System.out.println("Nastepne "+w);
            this.odwiedz_wierzcholek(w);
        }

        System.out.println(plecak.przedmioty_w_plecaku);
//        System.out.println(plecak.wszystkie_przedmioty);
        System.out.println(rozwiazanie());

//        System.out.println(plecak);
    }

    public void reset()
    {
//       odwiedzone_wierzcholki.clear();
    }





}
