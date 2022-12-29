package br.eti.ns.nssuite.JSON.NF3e;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class NF3eJSON {
  @JsonProperty("NF3e")
  public NF3e nF3e;

  public static class NF3e {
    public InfNF3e infNF3e;
  }

  public static class InfNF3e {
    public String versao;
    public Ide ide;
    public Emit emit;
    public Dest dest;
    public AutXML autXML;
    public NFDet NFdet;
    public Acessante acessante;
    public Total total;
    public GFat gFat;
    public GANEEL gANEEL;
    public InfAdic infAdic;
    public InfNF3eSupl infNF3eSupl;
    public GRespTec gRespTec;
  }

  public static class Ide {
    public String cUF;
    public String tpAmb;
    public String mod;
    public String serie;
    public String nNF;
    public String cNF;
    public String cDV;
    public String dhEmi;
    public String tpEmis;
    public String cMunFG;
    public String finNF3e;
    public String verProc;
    public String natOp;
    public String dhSaiEnt;
    public String tpNF;
    public String idDest;
    public String tpImp;
    public String indFinal;
    public String indPres;
    public String procEmi;
    public String dhCont;
    public String xJust;
  }

  public static class Emit {
    @JsonProperty("CNPJ")
    public String cNPJ;
    @JsonProperty("IE")
    public String iE;
    public String xNome;
    public String xFant;
    public EnderEmit enderEmit;
  }

  public static class EnderEmit {
    public String xLgr;
    public String nro;
    public String xCpl;
    public String xBairro;
    public String cMun;
    public String xMun;
    @JsonProperty("CEP")
    public String cEP;
    @JsonProperty("UF")
    public String uF;
    public String fone;
    public String email;
  }

  public static class Dest {
    public String xNome;
    @JsonProperty("CNPJ")
    public String cNPJ;
    @JsonProperty("CPF")
    public String cPF;
    public String idOutros;
    public String indIEDest;
    @JsonProperty("IE")
    public String iE;
    @JsonProperty("IM")
    public String iM;
    @JsonProperty("cNIS")
    public String cNIS;
    public EnderDest enderDest;
    @JsonProperty("NB")
    public String nb;
    public String xNomeAdicional;
  }

  public static class EnderDest {
    public String xLgr;
    public String nro;
    public String xCpl;
    public String xBairro;
    public String cMun;
    public String xMun;
    @JsonProperty("CEP")
    public String cEP;
    @JsonProperty("UF")
    public String uF;
    public String fone;
    public String email;
  }

  public static class Acessante {
    public String idAcesso;
    public String idCodCliente;
    public String tpAcesso;
    public String xNomeUC;
    public String tpClasse;
    public String tpSubClasse;
    public String tpFase;
    public String tpGrpTensao;
    public String tpModTar;
    public String latGPS;
    public String longGPS;
    public String codRoteiroLeitura;
  }

  public static class gSub {
    public String chNF3e;
    public GNF gNF;
  }

  public static class GNF {
    @JsonProperty("CNPJ")
    public String cNPJ;
    public String serie;
    public String nNF;
    public String CompetEmis;
    public String CompetAput;
    public String hash115;
    public String motSub;
  }

  public static class gJudic {
    public String chNF3e;
  }

  public static class gGrContrat {
    public String nContrat;
    public String tpGrContra;
    public String tpPosTar;
    public String qUnidContrat;
  }

  public static class gMed {
    public String nMed;
    public String idMedidor;
    public String dMedAnt;
    public String dMedAtu;
  }

  public static class gSCEE {
    public String tpPartComp;
    public GConsumidor gConsumidor;
    public GSaldoCred gSaldoCred;
  }

  public static class GConsumidor {
    public String idAcessGer;
    public String vPotInst;
    public String tpFonteEnergia;
    public String enerAloc;
    public String tpPosTar;
  }

  public static class GSaldoCred {
    public String tpPosTar;
    public String vSaldAnt;
    public String vCredExpirado;
    public String vSaldAtual;
    public String vCredExpirar;
    public String CompetExpirar;
  }

  public static class NFDet { //1
    public String chNF3eAnt;
    public String mod6HashAnt;
    public Det det;
  }

  public static class Det { //2
    public String nItem;
    public GAjusteNF3eAnt gAjusteNF3eAnt;
    public DetItemAnt detItemAnt;
    public DetItem detItem;
    public void add(Det det) {}
  }

  public static class GAjusteNF3eAnt {
    public String tpAjuste;
    public String motAjuse;
  }

  public static class DetItemAnt {
    public String nItemAnt;
    public String vItem;
    public String qFaturada;
    public String vProd;
    public String cClass;
    public String vBC;
    public String pICMS;
    public String vICMS;
    public String vPIS;
    public String vCOFINS;
    public RetTrib retTrib;
  }

  public static class DetItem {
    public String nItemAnt;
    public GTarif gTarif;
    public GAdBand gAdBand;
    public prod prod;
    public Imposto imposto;
    public GProcRef gProcRef;
    public GContab gContab;
  }

  public static class GTarif {
    public String dIniTarif;
    public String dFimTarif;
    public String tpAto;
    public String nAto;
    public String anoAto;
    public String tpTarif;
    public String cPosTarif;
    public String uMed;
    public String vTarifHom;
    public String vTarifAplic;
    public String motDifTarif;
  }

  public static class GAdBand {
    public String dIniAdBand;
    public String dFimAdBand;
    public String tpBand;
    public String vAdBand;
    public String vAdBandAplic;
    public String motDifBand;
  }

  public static class prod {
    public String indOrigemQtd;
    public GMedicao gMedicao;
    public String cProd;
    public String xProd;
    public String cClass;
    @JsonProperty("CFOP")
    public String cFOP;
    public String uMed;
    public String qFaturada;
    public String vItem;
    public String vProd;
    public String indDevolucao;
    public String indPrecoACL;
  }

  public static class GMedicao {
    public String nMed;
    public String nContrat;
    public GMedida gMedida;
    public String tpMotNaoLeitura;
  }

  public static class GMedida {
    public String tpGrMed;
    public String cPosTarif;
    public String uMed;
    public String vMedAnt;
    public String vMedAtu;
    public String vConst;
    public String vMed;
    public String pPerdaTran;
    public String vMedPerdaTran;
    public String vMedPerdaTec;
  }

  public static class Imposto {
    @JsonProperty("ICMS00")
    public ICMS00 iCMS00;
    @JsonProperty("ICMS10")
    public ICMS10 iCMS10;
    @JsonProperty("ICMS20")
    public ICMS20 iCMS20;
    @JsonProperty("ICMS40")
    public ICMS40 iCMS40;
    @JsonProperty("ICMS51")
    public ICMS51 iCMS51;
    @JsonProperty("ICMS90")
    public ICMS90 iCMS90;
    public pIS PIS;
    public cOFINS COFINS;
    public pISEfet PISEfet;
    public cOFINSEfet COFINSEfet;
    public RetTrib retTrib;
  }

  public static class ICMS00 {
    @JsonProperty("CST")
    public String cST;
    public String vBC;
    public String pICMS;
    public String vICMS;
    public String pFCP;
    public String vFCP;
  }

  public static class ICMS10 {
    @JsonProperty("CST")
    public String cST;
    public String vBCST;
    public String pICMSST;
    public String vICMSST;
    public String pFCPST;
    public String vFCPST;
  }

  public static class ICMS20 {
    @JsonProperty("CST")
    public String cST;
    public String pRedBC;
    public String vBC;
    public String pICMS;
    public String vICMS;
    public String vICMSDeson;
    public String cBenef;
    public String vBCFCP;
    public String pFCP;
    public String vFCP;
  }

  public static class ICMS40 {
    @JsonProperty("CST")
    public String cST;
    public String vICMSDeson;
    public String cBenef;
  }

  public static class ICMS51 {
    @JsonProperty("CST")
    public String cST;
    public String vICMSDeson;
    public String cBenef;
  }

  public static class ICMS90 {
    @JsonProperty("CST")
    public String cST;
    public String vBC;
    public String pICMS;
    public String vICMS;
  }

  public static class pIS {
    @JsonProperty("CST")
    public String cST;
    public String vBC;
    public String pPIS;
    public String vPIS;
  }

  public static class pISEfet {
    public String vBCPISEfet;
    public String pPISEfet;
    public String vPISEfet;
  }

  public static class cOFINS {
    @JsonProperty("CST")
    public String cST;
    public String vBC;
    public String pCOFINS;
    public String vCOFINS;
  }

  public static class cOFINSEfet {
    public String vBCCOFINSEfet;
    public String pCOFINSEfet;
    public String vCOFINSEfet;
  }

  public static class RetTrib {
    public String vRetPIS;
    public String vRetCofins;
    public String vRetCSLL;
    public String vBCIRRF;
    public String vIRRF;
  }

  public static class GProcRef {
    public String vItem;
    public String qFaturada;
    public String vProd;
    public String indDevolucao;
    public String vBC;
    public String pICMS;
    public String vICMS;
    public String vPIS;
    public String vCOFINS;
    public GProc gProc;
  }

  public static class GProc {
    public String tpProc;
    public String nProcesso;
  }

  public static class GContab {
    public String cContab;
    public String xContab;
    public String vContab;
    public String infAdProd;
  }

  public static class Total {
    public String vProd;
    public String vCOFINS;
    public String vCOFINSEfet;
    public String vPIS;
    public String vPISEfet;
    public String vNF;
    @JsonProperty("ICMSTot")
    public ICMSTot iCMSTot;
    public VRetTribTot vRetTribTot;
  }

  public static class VRetTribTot {
    public String vRetPIS;
    public String vRetCofins;
    public String vRetCSLL;
    public String vIRRF;
  }

  public static class ICMSTot {
    public String vBC;
    public String vICMS;
    public String vICMSDeson;
    public String vFCP;
    public String vBCST;
    public String vST;
    public String vFCPST;
  }

  public static class GFat {
    public String CompetFat;
    public String dVencFat;
    public String dApresFat;
    public String dProxLeitura;
    public String nFat;
    public String codBarras;
    public String codDebAuto;
    public String codBanco;
    public String codAgencia;
    public EnderCorresp enderCorresp;
    public GPIX gPix;
  }

  public static class GPIX {
    public String urlQRCodePIX;
  }
  public static class EnderCorresp {
    public String xLgr;
    @JsonProperty("nro")
    public String nro;
    public String xCpl;
    public String xBairro;
    public String cMun;
    public String xMun;
    @JsonProperty("CEP")
    public String cEP;
    @JsonProperty("UF")
    public String uF;
    @JsonProperty("fone")
    public String fone;
    @JsonProperty("email")
    public String email;
  }

  public static class GANEEL {
    public GHistFat gHistFat;
  }

  public static class GHistFat {
    public String xGrandFat;
    public List<GGrandFat> gGrandFat;
  }


  public static class GGrandFat {
    public String CompetFat;
    public String vFat;
    public String uMed;
    public String qtdDias;
    public void add(ArrayList<GGrandFat> listgGrandFat) {
    }
  }

  public static class AutXML {
    @JsonProperty("CNPJ")
    public String cNPJ;
    @JsonProperty("CPF")
    public String cPF;
    public void add(AutXML autXML) {
    }
  }

  public static class InfAdic {
    public String infAdFisco;
    public String infCpl;
    public List<ObsCont> obsCont;
    public List<ObsFisco> obsFisco;
    public List<ProcRef> procRef;
  }

  public static class ObsCont {
    public String xCampo;
    public String xTexto;
  }

  public static class ObsFisco {
    public String xCampo;
    public String xTexto;
  }

  public static class ProcRef {
    public String nProc;
    public String indProc;
  }

  public static class GRespTec {
    @JsonProperty("CNPJ")
    public String cNPJ;
    public String xContato;
    @JsonProperty("email")
    public String email;
    @JsonProperty("fone")
    public String fone;
    public String idCSRT;
    public String hashCSRT;
  }

  public static class InfNF3eSupl {
    public String qrCodNF3e;
  }
}