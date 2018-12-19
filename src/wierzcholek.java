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
