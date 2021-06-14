package sample;

import java.util.ArrayList;

public class Recueil {
    NuancierRalDe nuancierRalDe = new NuancierRalDe();
    ArrayList<Suite> suites;
    ArrayList<Suite> filteredSuites;

    public Recueil(int gamme6) {
        suites = new ArrayList<Suite>();
        for (int minSat = 0; minSat < 10; minSat++) {
            for (int maxSat = minSat; maxSat < 10; maxSat++) {
                for (int minVal = 2; minVal <= 9; minVal++) {
                    for (int maxVal = minVal; maxVal <= 9; maxVal++) {
                        for (int offsetV = gamme6; offsetV <= 36; offsetV += 6) {
                            for (int offsetS = gamme6; offsetS <= 36; offsetS += 6) {
                                Suite suite = new Suite(minSat, maxSat, minVal, maxVal, offsetV, offsetS, gamme6, nuancierRalDe.patches);
                                if (suite.patches.size() > 1 && !(existe(suite))) {
                                    this.suites.add(suite);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean existe(Suite suite) {
        boolean existe = false;
        for (Suite s : this.suites) {
            if (suite.patchesString.equals(s.patchesString) || (s.patchesString.contains(suite.patchesString))) {
                existe = true;
            }
            if (existe) break;
        }
        return existe;
    }

    public ArrayList<Suite> containsPatch(String ral) {
        ArrayList<Suite> newList = new ArrayList<Suite>();
        for (Suite suite : this.suites) {
            boolean contains = false;
            for (Patch p : suite.patches) {
                if (p.ral.equals(ral)) contains = true;
            }
            if (contains) newList.add(suite);
        }
        return newList;
    }

    public ArrayList<Suite> searchNbColors(int nbColors,String type){//exact,auplus,aumoins
        ArrayList<Suite> newList = new ArrayList<Suite>();
        for (Suite suite : this.suites) {
            if (suite.nbColors == nbColors && type.equals("exact")) newList.add(suite);
            if (suite.nbColors >= nbColors && type.equals("aumoins")) newList.add(suite);
            if (suite.nbColors <= nbColors && type.equals("auplus")) newList.add(suite);
            }
        return newList;
    }
    public ArrayList<Suite> searchTemperature(String valeur){//warm,cold,balanced
        ArrayList<Suite> newList = new ArrayList<Suite>();
        for (Suite suite : this.suites) {
            if (suite.temperature.equals(valeur)) newList.add(suite);
        }
        return newList;
    }

    public ArrayList<Suite> searchAverageValue(String valeur){//dark,medium,light
        ArrayList<Suite> newList = new ArrayList<Suite>();
        for (Suite suite : this.suites) {
            if (suite.averageValue.equals(valeur)) newList.add(suite);
        }
        return newList;
    }

    public ArrayList<Suite> searchAverageChroma(String valeur){//faded,medium,bright
        ArrayList<Suite> newList = new ArrayList<Suite>();
        for (Suite suite : this.suites) {
            if (suite.averageChroma.equals(valeur)) newList.add(suite);
        }
        return newList;
    }

    public ArrayList<Suite> searchContrastChroma(String valeur){//weak,medium,strong
        ArrayList<Suite> newList = new ArrayList<Suite>();
        for (Suite suite : this.suites) {
            if (suite.contrastChroma.equals(valeur)) newList.add(suite);
        }
        return newList;
    }

    public ArrayList<Suite> searchContrastValue(String valeur){//weak,medium,strong
        ArrayList<Suite> newList = new ArrayList<Suite>();
        for (Suite suite : this.suites) {
            if (suite.contrastValue.equals(valeur)) newList.add(suite);
        }
        return newList;
    }
}
