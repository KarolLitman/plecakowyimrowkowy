public class algorytm_wierzcholki {


    void inicjalizuj()
    {


        /// Inicjalizacja Knapsack items
        List<Przedmiot>  KnapsackItemList = mkp.KnapsackItemList;
        if (KnapsackItemList.Count == 0)
        {
            throw new AcoException("Nie zdefiniowano ¿adnych przedmiotów");
        }


        /// Inicjalizacja wêz³ów problemu
        nodeList = new NodeList();
        for (int i = 0; i < KnapsackList.Count; i++)
        {
            for (int j = 0; j < KnapsackItemList.Count; j++)
            {
                nodeList.Add(new KnapsackItemNode(KnapsackList[i], KnapsackItemList[j], parameters.InitialPheromoneValue));
            }
        }

        if (parameters.AntQuantity <= 0)
        {
            throw new AcoException("Nieprawid³owa liczba mrówek: "+parameters.AntQuantity.ToString());
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
        double maxValue = double.MinValue;
        double minValue = double.MaxValue;
        double avgValue = 0;
        int cycyleCount = 0;
        double tmp;

        /// wykonujemy poni¿sze kroki dla ka¿dej mrówki:
        foreach (MkpNodeAnt a in Ants)
        {
            ///oprozniamy plecaki:
            Profiler.StartEvent("CycleReset");
            foreach (Knapsack k in mkp.KnapsackList)
            {
                k.Clear();
            }
            ///resetujemy i ustawiamy mrówki:
            a.reset();
            a.setLocation(nodeList[random.Next(0, nodeList.Count - 1)]);
            Profiler.FinishEvent("CycleReset");

            Profiler.StartEvent("Ant run");
            ///wykonujemy ruch mrówki:
            a.run();
            Profiler.FinishEvent("Ant run");

            Profiler.StartEvent("Pheromone update");
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


    public void GlobalPheromoneUpdate(MkpNodeAnt ant)
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

        foreach (KnapsackItemNode node in ant.VisitedNodes)
        {
            node.Pheromone +=
                    this.parameters.Alpha * updateAmount * node.InitialPheromone;
        }
    }

    public void LocalPheromoneUpdate(MkpNodeAnt ant)
    {
        foreach (KnapsackItemNode node in ant.VisitedNodes)
        {
            node.Pheromone = (1 - parameters.Rho) * (node.Pheromone) + parameters.Rho * node.InitialPheromone;
        }
    }

    public double CalculateAttractiveness(KnapsackItemNode node)
    {
        return node.Pheromone * Math.Pow((node.Item.Value.value / node.Item.Size.getAverage()), parameters.Beta);
    }

    public override void Reset()
    {
        if (nodeList != null)
        {
            foreach (KnapsackItemNode node in NodeList)
            {
                node.Pheromone = node.InitialPheromone;
            }
        }
    }

    public override string ToString()
    {
        return "Mrówka wêz³owa";
    }







}



