package br.eti.ns.formsns;

import br.eti.ns.nssuite.compartilhados.Genericos;
import br.eti.ns.nssuite.NSSuite;
import br.eti.ns.nssuite.JSON.NF3e.NF3eJSON;
import br.eti.ns.nssuite.JSON.NF3e.NF3eJSON.*;
import br.eti.ns.nssuite.JSON.NF3e.NF3eJSON.Dest;
import br.eti.ns.nssuite.JSON.NF3e.NF3eJSON.Emit;
import br.eti.ns.nssuite.JSON.NF3e.NF3eJSON.EnderDest;
import br.eti.ns.nssuite.JSON.NF3e.NF3eJSON.EnderEmit;
import br.eti.ns.nssuite.JSON.NF3e.NF3eJSON.Ide;
import br.eti.ns.nssuite.requisicoes.nf3e.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.util.ArrayList;

public class frmemissaoTeste {

    private JPanel panelMain;
    private JButton btnEnviarNF3e;
    private JButton btnCancelarNF3e;
    private JButton btnDownEventoNF3e;
    private JButton btnConSituacaoNF3e;
    private JButton btnListarNSNrec;
    private JButton btnDownloadNF3e;
    private JButton btnPegaCerti;

    public frmemissaoTeste() {
        btnPegaCerti.addActionListener(e -> {
            Genericos.carregarCertificado(false, "ns1109TI", "C:\\Users\\User\\Desktop\\teste\\NEWS SYSTEMS & CIA LTDA.pfx");

        });

        btnEnviarNF3e.addActionListener(e -> {

            NF3eJSON nF3eJSON = new NF3eJSON();
            NF3e nF3e = new NF3e();
            InfNF3e infNF3e = new InfNF3e();
            Ide ide = new Ide();
            Emit emit = new Emit();
            EnderEmit enderEmit = new EnderEmit();
            Dest dest = new Dest();
            EnderDest enderDest = new EnderDest();
            Acessante acessante = new Acessante();
            NFDet NFdet = new NFDet();
            ArrayList<Det> listdet = new ArrayList<Det>();
            Det det = new Det();
            DetItem detItem = new DetItem();
            GTarif gTarif = new GTarif();
            GAdBand gAdBand = new GAdBand();
            prod prod = new prod();
            Imposto imposto = new Imposto();
            ICMS90 iCMS90 = new ICMS90();
            pIS PIS = new pIS();
            cOFINS COFINS = new cOFINS();
            Total total = new Total();
            ICMSTot ICMSTot = new ICMSTot();
            VRetTribTot vRetTribTot = new VRetTribTot();
            GFat gFat = new GFat();
            EnderCorresp enderCorresp = new EnderCorresp();
            GANEEL gANEEL = new GANEEL();
            GHistFat gHistFat = new GHistFat();
            GGrandFat gGrandFat = new GGrandFat();
            ArrayList<GGrandFat> listgGrandFat = new ArrayList<GGrandFat>();
            int i = 1; // Variavel para ser utilizada como contador no loop
            AutXML autXML = new AutXML();
            ArrayList<AutXML> listautXML = new ArrayList<AutXML>();
            InfAdic infAdic = new InfAdic();
            GRespTec gRespTec = new GRespTec();

            nF3eJSON.nF3e = nF3e;
            nF3e.infNF3e = infNF3e;
            infNF3e.versao = "1.00";

            infNF3e.ide = ide;
            ide.cUF = "43";
            ide.tpAmb = "2";
            ide.mod = "66";
            ide.serie = "0";
            ide.nNF = "51020";
            ide.cNF = "00000001";
            ide.cDV = "";
            ide.dhEmi = "2021-06-04T15:00:33-03:00";
            ide.tpEmis = "1";
            ide.cMunFG = "4303509";
            ide.finNF3e = "1";
            ide.verProc = "1.0.0";

            infNF3e.emit = emit;
            emit.cNPJ = "07364617000135";
            emit.iE = "0170108708";
            emit.xNome = "News Systems Ltda";
            emit.xFant = "TESTE DE EMISSAO";

            emit.enderEmit = enderEmit;
            enderEmit.xLgr = "Av. Antonio Duro";
            enderEmit.nro = "870";
            enderEmit.xBairro = "Centro";
            enderEmit.cMun = "4303509";
            enderEmit.xMun = "Camaqua";
            enderEmit.cEP = "96785200";
            enderEmit.uF = "RS";
            enderEmit.fone = "5136712053";
            enderEmit.email = "email@email.com.br";

            infNF3e.dest = dest;
            dest.xNome = "Teste de Emissão da Silva";
            dest.cPF = "00269925074";
            dest.indIEDest = "9";

            dest.enderDest = enderDest;
            enderDest.xLgr = "Av. Anthero Salustiano Silveira";
            enderDest.nro = "510";
            enderDest.nro = "510";
            enderDest.xBairro = "Olaria";
            enderDest.cMun = "4303509";
            enderDest.xMun = "Camaqua";
            enderDest.cEP = "96785200";
            enderDest.uF = "RS";
            enderDest.fone = "51999999999";
            enderDest.email = "fernando.konflanz@nstecnologia.com.br";

            infNF3e.acessante = acessante;
            acessante.idAcesso = "43037003";
            acessante.tpAcesso = "0";
            acessante.tpFase = "3";
            acessante.tpGrpTensao = "01";
            acessante.tpModTar = "03";
            acessante.latGPS = "38.736946";
            acessante.longGPS = "9.142685";

            infNF3e.NFdet = NFdet;
            NFdet.det = det;

            det.add(det);
            det.nItem = "1";

            det.detItem = detItem;

            detItem.gTarif = gTarif;

            gTarif.dIniTarif = "2020-07-27";
            gTarif.dFimTarif = "2020-07-28";
            gTarif.tpAto = "1";
            gTarif.nAto = "1234";
            gTarif.anoAto = "1234";
            gTarif.tpTarif = "1";
            gTarif.cPosTarif = "0";
            gTarif.uMed = "1";
            gTarif.vTarifHom = "3.00000000";
            gTarif.vTarifAplic = "3.00000000";
            gTarif.motDifTarif = "03";

            detItem.gAdBand = gAdBand;
            gAdBand.dIniAdBand = "2019-11-27";
            gAdBand.dFimAdBand = "2019-11-27";
            gAdBand.tpBand = "1";
            gAdBand.vAdBand = "3.50";
            gAdBand.vAdBandAplic = "3000.80";
            gAdBand.motDifBand = "03";

            detItem.prod = prod;
            prod.indOrigemQtd = "1";
            prod.cProd = "5555555";
            prod.xProd = "Consumo de Teste";
            prod.cClass = "0601000";
            prod.cFOP = "5258";
            prod.uMed = "2";
            prod.qFaturada = "10";
            prod.vItem = "3.00000000";
            prod.vProd = "30.00000000";

            detItem.imposto = imposto;

            imposto.iCMS90 = iCMS90;
            iCMS90.cST = "90";
            iCMS90.vBC = "30.00";
            iCMS90.pICMS = "18.00";
            iCMS90.vICMS = "5.40";

            imposto.PIS = PIS;
            PIS.cST = "01";
            PIS.vBC = "30.00";
            PIS.pPIS = "1.650";
            PIS.vPIS = "0.49";

            imposto.COFINS = COFINS;
            COFINS.cST = "01";
            COFINS.vBC = "30.00";
            COFINS.pCOFINS = "7.6000";
            COFINS.vCOFINS = "2.28";

            infNF3e.total = total;
            total.vProd = "30.00";
            total.vCOFINS = "2.28";
            total.vPIS = "0.49";
            total.vNF = "30.00";
            total.vCOFINSEfet = "0.00";
            total.vPISEfet = "0.00";

            total.iCMSTot = ICMSTot;
            ICMSTot.vBC = "30.00";
            ICMSTot.vICMS = "5.40";
            ICMSTot.vICMSDeson = "0.00";
            ICMSTot.vFCP = "0.00";
            ICMSTot.vBCST = "0.00";
            ICMSTot.vST = "0.00";
            ICMSTot.vFCPST = "0.00";

            total.vRetTribTot =  vRetTribTot;
            vRetTribTot.vRetPIS = "0.00";
            vRetTribTot.vRetCofins = "0.00";
            vRetTribTot.vRetCSLL = "0.00";
            vRetTribTot.vIRRF = "0.00";

            infNF3e.gFat = gFat;
            gFat.CompetFat = "201911";
            gFat.dVencFat = "2019-11-27";
            gFat.dApresFat = "2019-11-27";
            gFat.dProxLeitura = "2019-12-27";
            gFat.nFat = "123456";
            gFat.codBarras = "12345678";
            gFat.codDebAuto = "12345678";
            gFat.enderCorresp = enderCorresp;
            enderCorresp.xLgr = "Av. Anthero Salustiano Silveira";
            enderCorresp.nro = "510";
            enderCorresp.xBairro = "Olaria";
            enderCorresp.cMun = "4303509";
            enderCorresp.xMun = "Camaqua";
            enderCorresp.cEP = "96785200";
            enderCorresp.uF = "RS";
            enderCorresp.fone = "51999999999";
            enderCorresp.email = "fernando.konflanz@nstecnologia.com.br";

            infNF3e.gANEEL = gANEEL;
            gANEEL.gHistFat = gHistFat;
            gHistFat.xGrandFat = "Consumo Meses Anteriores";
            gHistFat.gGrandFat = listgGrandFat;

            while (i <= 13) {
                listgGrandFat.add(gGrandFat);
                gGrandFat.CompetFat = "201911";
                gGrandFat.vFat = "30.00";
                gGrandFat.uMed = "2";
                gGrandFat.qtdDias = "10";
                i++;
            }

            infNF3e.autXML = autXML;
            autXML.add(autXML);
            autXML.cNPJ = "07364617000135";

            infNF3e.infAdic = infAdic;
            infAdic.infAdFisco = "Dados de interesse do fiscal";
            infAdic.infCpl = "Observacoes, emissao de teste da NS";

            infNF3e.gRespTec = gRespTec;
            gRespTec.cNPJ = "07364617000135";
            gRespTec.xContato = "Fernando Konflanz";
            gRespTec.email = "fernando.konflanz@nstecnologia.com.br";
            gRespTec.fone = "51996359538";

	    ObjectMapper mapper = new ObjectMapper();
            String json = "";

	    try {
                json = mapper.writeValueAsString(nF3eJSON);
            } 
	    catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
            System.out.println(json);
            String retorno = null;

            try {
                retorno = NSSuite.emitirNF3eSincrono(json, "json", "07364617000135", "X", "2", "C:/Notas/NF3e", false);

                JsonNode respostaJSON = mapper.readTree(retorno);
                String statusEnvio = respostaJSON.get("statusEnvio").asText();
                String statusConsulta = respostaJSON.get("statusConsulta").asText();
                String statusDownload = respostaJSON.get("statusDownload").asText();
                String cStat = respostaJSON.get("cStat").asText();
                String chNF3e = respostaJSON.get("chNF3e").asText();
                String nProt = respostaJSON.get("nProt").asText();
                String motivo = respostaJSON.get("motivo").asText();
                String nsNRec = respostaJSON.get("nsNRec").asText();
                ArrayList<String> erros = mapper.readValue(respostaJSON.get("erros").toString(), new TypeReference<ArrayList<String>>(){});

                if (statusEnvio.equals("200") || statusEnvio.equals("-6")){
                    if (statusConsulta.equals("200")) {
                        if (cStat.equals("100")) {
                            JOptionPane.showMessageDialog(null, motivo);
                            if (!statusDownload.equals("200")) {
                                JOptionPane.showMessageDialog(null, "Erro Download");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, motivo);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, motivo + "\n" + erros.toString());
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
	    System.out.println(retorno);
        });

        // Cancelamento
        btnCancelarNF3e.addActionListener(e -> {
            CancelarReqNF3e cancelarReqNF3e = new CancelarReqNF3e();
            cancelarReqNF3e.dhEvento = "2021-06-04T16:21:00-03:00";
            cancelarReqNF3e.nProt = "143210007831904";
            cancelarReqNF3e.tpAmb = "2";
            cancelarReqNF3e.xJust = "Cancelamento de NF3e para fins de teste em homologação";
            cancelarReqNF3e.chNF3e = "43210607364617000135660000000510201000000011";
            try {
                String retorno = NSSuite.cancelarDocumento("66", cancelarReqNF3e);
                System.out.println(retorno);
            }
            catch (Exception exception) {
                exception.printStackTrace();

            }
        });
	
	//Download de Evento
        btnDownEventoNF3e.addActionListener(e -> {
	    DownloadEventoReqNF3e downloadEventoReqNF3e = new DownloadEventoReqNF3e();
            downloadEventoReqNF3e.nSeqEvento = "1";
            downloadEventoReqNF3e.chNF3e = "43210607364617000135660000000510201000000011";
            downloadEventoReqNF3e.tpDown = "X";
            downloadEventoReqNF3e.tpAmb = "2";
            downloadEventoReqNF3e.tpEvento = "CANC";
	    try {
                String retorno = NSSuite.downloadEventoESalvar("66",downloadEventoReqNF3e,"C:/Notas/NF3e",downloadEventoReqNF3e.chNF3e,downloadEventoReqNF3e.nSeqEvento,false);
                System.out.println(retorno);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
	
	//Consultar Situacao
        btnConSituacaoNF3e.addActionListener(e -> {
            ConsSitReqNF3e consSitReqNF3e = new ConsSitReqNF3e();
            consSitReqNF3e.chNF3e = "43210607364617000135660000000510201000000011";
            consSitReqNF3e.licencaCNPJ = "07364617000135";
            consSitReqNF3e.tpAmb = "2";
	    try {
		String retorno = NSSuite.consultarSituacaoDocumento("66", consSitReqNF3e);
                System.out.println(retorno);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        
	//Listagem de nsNRec
	btnListarNSNrec.addActionListener(e -> {
	    ListarNSNRecReqNF3e listarNSNRecReqNF3e = new ListarNSNRecReqNF3e();
            listarNSNRecReqNF3e.chNF3e = "43210607364617000135660000000510201000000011";
            listarNSNRecReqNF3e.tpAmb = "2";
	    try {
                String retorno = NSSuite.listarNSNRecs("66", listarNSNRecReqNF3e);
                System.out.println(retorno);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        
	btnDownloadNF3e.addActionListener(e -> {
            DownloadReqNF3e downloadReqNF3e = new DownloadReqNF3e();
            downloadReqNF3e.chNF3e = "43210607364617000135660000000510201000000011";
            downloadReqNF3e.tpDown = "X";
            downloadReqNF3e.tpAmb = "2";
            
	    try {
                String retorno = NSSuite.downloadDocumentoESalvar("66", downloadReqNF3e,"C:/Notas/NF3e",downloadReqNF3e.chNF3e+"-NF3e",false);
                System.out.println(retorno);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    };


    
    public static void main (String[] args) {
        JFrame frame = new JFrame("frmEmissaoTeste");
        frame.setContentPane(new frmemissaoTeste().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
