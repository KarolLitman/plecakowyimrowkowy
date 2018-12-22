import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class algorytm_mrowkowy {


    static public double poczatkowy_feromon = 0.01;
    static public double Rho = 0.10;
    static public double Beta = 1;
    static public double Alpha = 1;
    static public double Q0 = 0.5;
    static public int ilosc_mrowek = 500;
    
    
    
    ArrayList<wierzcholek> lista_wierzcholkow;
     mrowka[] mrowki;
     mrowka najlepsza_mrowka;
     problem_plecakowy pp;
     double najlepsza_sciezka = 0;


    algorytm_mrowkowy(problem_plecakowy pp) throws Exception {

        this.pp=pp;

        List<przedmiot> lista_przedmitow = pp.wszystkie_przedmioty;
        lista_wierzcholkow = new ArrayList<>();


        for (int j = 0; j < lista_przedmitow.size(); j++)
        {
            lista_wierzcholkow.add(new wierzcholek(lista_przedmitow.get(j)));
        }

        mrowki = new mrowka[algorytm_mrowkowy.ilosc_mrowek];
        for (int i = 0; i < algorytm_mrowkowy.ilosc_mrowek; i++)
        {
            mrowki[i] = new mrowka(lista_wierzcholkow);
        }


        wykonaj();

    }


    void wykonaj() throws Exception {
        //todo: zrobic fermon staly,sredni i cykliczny

        double maksymalna_wartosc = Double.MIN_VALUE;
        double minimalna_wartosc = Double.MAX_VALUE;
        double srednia_wartosc = 0;
        double tmp;

        Random r=new Random();

        for (mrowka m : mrowki)
        {


            m.reset();
            m.odwiedz_wierzcholek(lista_wierzcholkow.get(r.nextInt(lista_wierzcholkow.size())));

//            System.out.println("Pierwszy wylosowany wierzcholek "+m.odwiedzone_wierzcholki);

            m.run();

            tmp = m.rozwiazanie();

            srednia_wartosc += tmp;
            if (tmp < minimalna_wartosc)
            {
                minimalna_wartosc = tmp;
            }
            if (tmp > maksymalna_wartosc)
            {
                maksymalna_wartosc = tmp;
                najlepsza_mrowka = m;
            }
            lokalny_feromon(m);
        }
        srednia_wartosc = srednia_wartosc / (double)algorytm_mrowkowy.ilosc_mrowek;

        if(this.najlepsza_sciezka < maksymalna_wartosc)
        {
            this.najlepsza_sciezka = maksymalna_wartosc;
        }

        globalny_feromon(najlepsza_mrowka);



    }
 

    public void globalny_feromon(mrowka m)
    {
        double wartosc; 
        if (this.najlepsza_sciezka > 0)
        {
            wartosc = m.rozwiazanie() / this.najlepsza_sciezka;
        }
        else
        {
            wartosc = 0;
        }

        for (wierzcholek w : m.odwiedzone_wierzcholki)
        {
            w.feromon +=
                    algorytm_mrowkowy.Alpha * wartosc * algorytm_mrowkowy.poczatkowy_feromon;
        }
    }

    public void lokalny_feromon(mrowka m)
    {
        for (wierzcholek w : m.odwiedzone_wierzcholki)
        {
            w.feromon = (1 - algorytm_mrowkowy.Rho) * (w.feromon) + algorytm_mrowkowy.Rho * algorytm_mrowkowy.poczatkowy_feromon;
        }
    }


    public void Reset()
    {

            for (wierzcholek w : lista_wierzcholkow) {
                
                w.feromon = algorytm_mrowkowy.poczatkowy_feromon;
            }
    }







}



