package br.eti.ns.nssuite.requisicoes._genericos;

//import br.eti.ns.nssuite.requisicoes.cte.InfGTVReqCTe;

import java.util.ArrayList;

public class IncluirDFeReq {
    public String chMDFe;
    public String tpAmb;
    public String dhEvento;
    public String nSeqEvento;
    public String nProt;
    public String xMun;
    public String cMun;
    public ArrayList<IncluirDFeReq.InfDocs> infDocs;

    public static class InfDocs {
        public String cMun;
        public String xMun;
        public String chNFe;
    }
}
