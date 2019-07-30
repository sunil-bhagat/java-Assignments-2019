package findinggene;

import findinggene.gene.Gene;

public class Main {
    public static void main(String[] args){
        Gene gene = new Gene();
        System.out.println(gene.findSimpleGene("gatgctataatatg"));
        System.out.println(gene.twoOccourences("sunil","dfsdfsunilfsfsuni"));
        System.out.println(gene.lastPart("zoo","forzoosdds"));
    }
}
