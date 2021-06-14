package sample;

import java.util.ArrayList;

public class Suite {
    ArrayList<Patch> patches;
    Patch[] nuancierPatches;
    Patch precedent;
    String patchesString;
    int nbColors;
    String temperature;//warm, cold,balanced
    String averageValue;//dark,medium,light
    String averageChroma;//faded,medium,bright
    String contrastValue;//weak,medium,strong
    String contrastChroma;//weak,medium,strong

    Suite(int minSaturation,
          int maxSaturation,
          int minValeur,
          int maxValeur,
          int offsetAngleV,
          int offsetAngleS,
          int gamme6,
          Patch[] nuancierPatches) {
        this.precedent = new Patch(0, 0, 0);
        this.patches = new ArrayList<Patch>();
        this.patchesString = "";
        // if (36 % (maxSaturation + 1 - minSaturation) == 0 && 36 % (maxValeur + 1 - minValeur) == 0) {
        int hS, hV;
        int s;
        int v;
        for (int h = gamme6; h < 36; h += 6) {
            hS = (h + offsetAngleS) % 36;
            if (hS < 18) s = (int) (Math.round(minSaturation + (maxSaturation - minSaturation) * (hS / 18.0)));
            else s = (int) (Math.round(minSaturation + (maxSaturation - minSaturation) * ((36 - hS) / 18.0)));

            hV = (h + offsetAngleV) % 36;
            if (hV < 18) v = (int) Math.round(minValeur + (maxValeur - minValeur) * (hV / 18.0));
            else v = (int) Math.round(minValeur + (maxValeur - minValeur) * ((36 - hV) / 18.0));

            Patch inputPatch = new Patch(h * 10, v * 10, s * 10);
            if (inputPatch.gamme6 == gamme6) {
                if (this.inNuancier(inputPatch, nuancierPatches) && !(inputPatch.ral.equals(precedent.ral))) {
                    this.patches.add(inputPatch);
                    precedent = inputPatch;
                    patchesString += "§" + inputPatch.ral;
                }
            }
        }
        // }
        if(patches.size()>0) {
            this.nbColors = this.patches.size();
            int sommeValeur = 0;
            int sommeChroma = 0;
            int sommeTeinte = 0;
            int minValue = 1000;
            int maxValue = -1;
            int minChroma = 1000;
            int maxChroma = -1;
            for (int i = 0; i < this.patches.size(); i++) {
                sommeValeur += patches.get(i).L;
                sommeChroma += patches.get(i).C;
                sommeTeinte += patches.get(i).H;
                if (patches.get(i).L < minValue) minValue = patches.get(i).L;
                if (patches.get(i).L > maxValue) maxValue = patches.get(i).L;
                if (patches.get(i).C < minChroma) minChroma = patches.get(i).C;
                if (patches.get(i).C > maxChroma) maxChroma = patches.get(i).C;
            }
            if ((sommeChroma / nbColors) < 30) averageChroma = "faded";
            if ((sommeChroma / nbColors) >= 30 && (sommeChroma / nbColors) < 50) averageChroma = "medium";
            if ((sommeChroma / nbColors) >= 50) averageChroma = "bright";
            if ((sommeValeur / nbColors) < 30) averageValue = "dark";
            if ((sommeValeur / nbColors) >= 30 && (sommeValeur / nbColors) < 60) averageValue = "medium";
            if ((sommeValeur / nbColors) >= 60) averageValue = "light";
            if ((maxChroma - minChroma) < 45) contrastChroma = "weak";
            if ((maxChroma - minChroma) >= 25 && (maxChroma - minChroma) < 45) contrastChroma = "medium";
            if ((maxChroma - minChroma) >= 45) contrastChroma = "strong";
            if ((maxValue - minValue) < 25) contrastValue = "weak";
            if ((maxValue - minValue) >= 25 && (maxValue - minValue) < 45) contrastValue = "medium";
            if ((maxValue - minValue) >= 45) contrastValue = "strong";
            if ((sommeTeinte / nbColors) > 60 && (sommeTeinte / nbColors) <= 120) temperature = "balanced";
            if ((sommeTeinte / nbColors) > 120 && (sommeTeinte / nbColors) <= 240) temperature = "cold";
            if ((sommeTeinte / nbColors) > 240 && (sommeTeinte / nbColors) <= 300) temperature = "balanced";
            if ((sommeTeinte / nbColors) > 300 && (sommeTeinte / nbColors) <= 360) temperature = "warm";
            if ((sommeTeinte / nbColors) >= 0 && (sommeTeinte / nbColors) <= 60) temperature = "warm";
        }
    }

    boolean inNuancier(Patch patch, Patch[] patches) {
        boolean isIn = false;
        for (int i = 0; i < patches.length; i++) {
            if (patch.H == patches[i].H && patch.L == patches[i].L && patch.C == patches[i].C) {
                isIn = true;
                break;
            }
        }
        return isIn;
    }
}
