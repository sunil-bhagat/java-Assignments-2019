package findinggene.gene;
public class Gene {
    /**
     * find the first occurrence of the gene in the dna.
     * @param dna the dna strand
     * @return the found gene.
     */
    public String findSimpleGene(String dna){
        String test = dna.toUpperCase();
        int  startCodon = test.indexOf("ATG");
        if(startCodon == -1) return "";
        int stopCodon = test.indexOf("TAA",startCodon+3);
        if(stopCodon == -1) return "";
        String result = dna.substring(startCodon,stopCodon+3);
        if(result.length()%3==0) return result;
        return "";

    }

    /**
     * find the stop codon of gene.
     * @param dna the dna strand.
     * @param startIndex starting index of gene.
     * @param stopCodon the stopcodon pattern.
     * @return the index of stopcodon.
     */
    public int findStopCodon(String dna, int startIndex,String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        if((stopIndex-startIndex+3)%3 ==0) return stopIndex;
        return dna.length();
    }

    /**
     * finds the minimum of three numbers.
     */
    private int findMin(int a,int b, int c){
        if(a == b && a==c){
            return -1;
        }
        if(c<a && c<b){
            return c;
        }else if(b<a && b<c){
           return b;
        }else{
            return a;
        }

    }

    /**
     * find the first  shortest gene.
     * @param dna the dna strand.
     * @return the gene.
     */
    public String findGene(String dna){
        String test = dna.toUpperCase();
        int startIndex = test.indexOf("ATG");
        if(startIndex == -1) return "";
        int stopTaa = findStopCodon(test,startIndex,"TAA");
        int stopTag = findStopCodon(test, startIndex, "TAG");
        int stopAtg = findStopCodon(test , startIndex, "ATG");
        int stopIndex = findMin(stopAtg,stopTaa,stopTag);
        if (stopIndex == -1) return "";
        String result = dna.substring(startIndex,stopIndex+3);
        if(result.length()%3==0) return result;
        return "";

    }
}
