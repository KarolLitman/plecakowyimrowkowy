import java.util.ArrayList;

public class algorytm_wierzcholki {


    ArrayList<wierzcholek> nodeList;

    void inicjalizuj() throws Exception {


        /// Inicjalizacja Knapsack items
        ArrayList<przedmiot> KnapsackItemList = mkp.KnapsackItemList;
        if (KnapsackItemList.size() == 0)
        {
            throw new Exception("Nie zdefiniowano ¿adnych przedmiotów");
        }


        /// Inicjalizacja wêz³ów problemu
        nodeList = new ArrayList<>();
//        for (int i = 0; i < KnapsackList.Count; i++)
//        {
            for (int j = 0; j < KnapsackItemList.size(); j++)
            {
                nodeList.add(new wierzcholek(KnapsackList[i], KnapsackItemList[j], parameters.InitialPheromoneValue));
            }
//        }

        if (algorytm_mrowkowy.ilosc_mrowek <= 0)
        {
            throw new Exception("Nieprawid³owa liczba mrówek: "+algorytm_mrowkowy.ilosc_mrowek);
        }

        // Inicjalizacja mrówek
        Ants = new MkpNodeAnt[parameters.AntQuantity];
        for (int i = 0; i < parameters.AntQuantity; i++)
        {
            Ants[i] = new MkpNodeAnt(nodeList, this);
        }
    }



    public CycleResult ExecuteCycle()
    {
        ///ustawiamy mrówki w losowo wybranych wêz³ach
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        double avgValue = 0;
        int cycyleCount = 0;
        double tmp;

        /// wykonujemy poni¿sze kroki dla ka¿dej mrówki:
        for (mrowka a : Ants)
        {
            ///oprozniamy plecaki:
//            Profiler.StartEvent("CycleReset");
            for(Knapsack k in mkp.KnapsackList)
            {
                k.Clear();
            }
            ///resetujemy i ustawiamy mrówki:
            a.reset();
            a.setLocation(nodeList[random.Next(0, nodeList.Count - 1)]);
//            Profiler.FinishEvent("CycleReset");

//            Profiler.StartEvent("Ant run");
            ///wykonujemy ruch mrówki:
            a.run();
//            Profiler.FinishEvent("Ant run");

//            Profiler.StartEvent("Pheromone update");
            tmp = a.evaluateGoalFunction();

            avgValue += tmp;
            cycyleCount++;
            if (tmp < minValue)
            {
                minValue = tmp;
            }
            if (tmp > maxValue)
            {
                maxValue = tmp;
                CycleBestAnt = a;
            }
            LocalPheromoneUpdate(a);
            Profiler.FinishEvent("Pheromone update");
        }

        avgValue = avgValue / (double)cycyleCount;

        if(this.globalUpdateFactor < maxValue)
        {
            this.globalUpdateFactor = maxValue;
        }

        GlobalPheromoneUpdate(CycleBestAnt);

        // przekazujemy rezultat:

        CycleResult result = new CycleResult();
        result.BestSolution = CycleBestAnt.getSolution();
        result.Number = ++CycleCount;
        result.max = maxValue;
        result.min = minValue;
        result.avg = avgValue;

        return result;
    }


    public void GlobalPheromoneUpdate(mrowka ant)
    {
        double updateAmount;
        if (this.globalUpdateFactor > 0)
        {
            updateAmount = ant.evaluateGoalFunction() / this.globalUpdateFactor;
        }
        else
        {
            updateAmount = 0;
        }

        for (wierzcholek node : ant.odwiedzone_wierzcholki)
        {
            node.feromon +=
                    algorytm_mrowkowy.Alpha * updateAmount * algorytm_mrowkowy.poczatkowy_feromon;
        }
    }

    public void LocalPheromoneUpdate(mrowka ant)
    {
        for (wierzcholek node : ant.odwiedzone_wierzcholki)
        {

//            tutaj nie wiem czy initial node nie powinien byc dla wierzcholka ponizej na samym koncu mnozenie
            node.feromon = (1 - algorytm_mrowkowy.Rho) * (node.feromon) + algorytm_mrowkowy.Rho * algorytm_mrowkowy.poczatkowy_feromon;
        }
    }

    public double CalculateAttractiveness(wierzcholek node)
    {
        return node.feromon * Math.pow((node.przedmiot.cena / node.przedmiot.masa), algorytm_mrowkowy.Beta);
    }

    public void Reset()
    {
        if (nodeList != null)
        {
            for (wierzcholek node : NodeList)
            {
                node.feromon = algorytm_mrowkowy.poczatkowy_feromon;
            }
        }
    }

    public String ToString()
    {
        return "Mrówka wêz³owa";
    }







}



