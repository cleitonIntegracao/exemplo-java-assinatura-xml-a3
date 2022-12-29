package br.eti.ns.nssuite;

import br.eti.ns.nssuite.compartilhados.Endpoints;
import br.eti.ns.nssuite.compartilhados.Genericos;
import br.eti.ns.nssuite.compartilhados.Parametros;
import br.eti.ns.nssuite.requisicoes._genericos.*;
import br.eti.ns.nssuite.requisicoes.nf3e.ConsStatusProcessamentoReqNF3e;
import br.eti.ns.nssuite.requisicoes.nf3e.DownloadReqNF3e;
import br.eti.ns.nssuite.retornos.nf3e.EmitirSincronoRetNF3e;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NSSuite {
    private static String token = "ADQWREQW561D32AWS1D6";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Endpoints endpoints = new Endpoints();
    private static Parametros parametros = new Parametros();

    // Esta função envia um conteúdo para uma URL, em requisições do tipo POST
    private static String enviaConteudoParaAPI(Object conteudo, String url, String tpConteudo) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Response respostaServidor;
        try {
            if (tpConteudo.equals("txt")) {
                respostaServidor = target.request(MediaType.TEXT_PLAIN)
                        .header("X-AUTH-TOKEN", token)
                        .post(Entity.text(conteudo));
            }
            else if (tpConteudo.equals("xml")) {
                respostaServidor = target.request(MediaType.APPLICATION_XML)
                        // pode ser enviado também no json junto com os dados da nfce
                        .header("X-AUTH-TOKEN", token)
                        .post(Entity.xml(conteudo));
            } 
            else {
                respostaServidor = target.request(MediaType.APPLICATION_JSON)
                        // pode ser enviado também no json junto com os dados da nfce
                        .header("X-AUTH-TOKEN", token)
                        .post(Entity.json(conteudo));
            }
        }
        catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build().toString();
        }
        return respostaServidor.readEntity(String.class);
    }

    // Métodos específicos de NF3e
    public static String emitirNF3eSincrono(String conteudo, String tpConteudo, String CNPJ, String tpDown, String tpAmb, String caminho, boolean exibeNaTela) throws Exception {
        ArrayList<String> erros = new ArrayList<>();

        String modelo = "66";
        String statusEnvio = "";
        String statusConsulta = "";
        String statusDownload = "";
        String motivo = "";
        String nsNRec = "";
        String chNF3e = "";
        String cStat = "";
        String nProt = "";

        Genericos.gravarLinhaLog(modelo, "[EMISSAO_SINCRONA_INICIO]");

        String resposta = emitirDocumento(modelo, conteudo, tpConteudo);
        JsonNode respostaJSON = objectMapper.readTree(resposta);
        statusEnvio = respostaJSON.get("status").asText();

        // Testa se o envio foi feito com sucesso (200) ou se é para reconsultar (-6)
        if (statusEnvio.equals("200") || statusEnvio.equals("-6")) {

            nsNRec = respostaJSON.get("nsNRec").asText();

            // É necessário aguardar alguns milisegundos antes de consultar o status de
            // processamento
            Thread.sleep(parametros.TEMPO_ESPERA);

            ConsStatusProcessamentoReqNF3e consStatusProcessamentoReqNF3e = new ConsStatusProcessamentoReqNF3e();
            consStatusProcessamentoReqNF3e.CNPJ = CNPJ;
            consStatusProcessamentoReqNF3e.nsNRec = nsNRec;
            consStatusProcessamentoReqNF3e.tpAmb = tpAmb;

            resposta = consultarStatusProcessamento(modelo, consStatusProcessamentoReqNF3e);
            respostaJSON = objectMapper.readTree(resposta);
            statusConsulta = respostaJSON.get("status").asText();

            if (statusConsulta.equals("-2")) {
                cStat = respostaJSON.get("cStat").asText();
                if (cStat.equals("996")) {
                    motivo = respostaJSON.get("erro").get("xMotivo").asText();
                    for (int i = 1; i <= 3; i++) {
                        try {
                            Thread.sleep(6000 - (i * 1000));
                        } catch (InterruptedException ignored) {
                        }
                        resposta = consultarStatusProcessamento(modelo, consStatusProcessamentoReqNF3e);
                        respostaJSON = objectMapper.readTree(resposta);
                        statusConsulta = respostaJSON.get("status").asText();
                        if (!statusConsulta.equals("-2")) {
                            break;
                        }
                    }
                } else {
                    motivo = respostaJSON.get("xMotivo").asText();
                }
            }
            // Testa se a consulta foi feita com sucesso (200)
            if (statusConsulta.equals("200")) {

                cStat = respostaJSON.get("cStat").asText();

                if (cStat.equals("100") || cStat.equals("150")) {

                    chNF3e = respostaJSON.get("chNF3e").asText(); 
                    nProt = respostaJSON.get("nProt").asText();
                    motivo = respostaJSON.get("xMotivo").asText();

                    DownloadReqNF3e downloadReqNF3e = new DownloadReqNF3e();
                    downloadReqNF3e.chNF3e = chNF3e;
                    downloadReqNF3e.tpAmb = tpAmb; 
                    downloadReqNF3e.tpDown = tpDown;

                    resposta = downloadDocumentoESalvar(modelo, downloadReqNF3e, caminho, chNF3e + "-NF3e", exibeNaTela);
                    respostaJSON = objectMapper.readTree(resposta);
                    statusDownload = respostaJSON.get("status").asText();

                    if (!statusDownload.equals("200"))
                        motivo = respostaJSON.get("motivo").asText();
                }
                else {
                    motivo = respostaJSON.get("xMotivo").asText();
                }
            }
            else if (statusConsulta.equals("-2")) {

                cStat = respostaJSON.get("cStat").asText();
                motivo = respostaJSON.get("erro").get("xMotivo").asText();

            } else {
                motivo = respostaJSON.get("motivo").asText();
            }
        } else if (statusEnvio.equals("-7")) {

            motivo = respostaJSON.get("motivo").asText();
            nsNRec = respostaJSON.get("nsNRec").asText();

        } else if (statusEnvio.equals("-4") || statusEnvio.equals("-2")) {

            motivo = respostaJSON.get("motivo").asText();
            erros = objectMapper.readValue(respostaJSON.get("erros").toString(),
                    new TypeReference<ArrayList<String>>() {
                    });

        } else if (statusEnvio.equals("-999") || statusEnvio.equals("-5")) {

            motivo = respostaJSON.get("erro").get("xMotivo").asText();

        } else {
            try {
                motivo = respostaJSON.get("motivo").asText();
            } catch (Exception ex) {
                motivo = respostaJSON.toString();
            }
        }
        EmitirSincronoRetNF3e emitirSincronoRetNF3e = new EmitirSincronoRetNF3e();
        emitirSincronoRetNF3e.statusEnvio = statusEnvio;
        emitirSincronoRetNF3e.statusConsulta = statusConsulta;
        emitirSincronoRetNF3e.statusDownload = statusDownload;
        emitirSincronoRetNF3e.cStat = cStat;
        emitirSincronoRetNF3e.chNF3e = chNF3e;
        emitirSincronoRetNF3e.nProt = nProt;
        emitirSincronoRetNF3e.motivo = motivo;
        emitirSincronoRetNF3e.nsNRec = nsNRec;
        emitirSincronoRetNF3e.erros = erros;

        String retorno = objectMapper.writeValueAsString(emitirSincronoRetNF3e);

        Genericos.gravarLinhaLog(modelo, "[JSON_RETORNO]");
        Genericos.gravarLinhaLog(modelo, retorno);
        Genericos.gravarLinhaLog(modelo, "[EMISSAO_SINCRONA_FIM]");

        return retorno;
    }

    // Métodos genéricos, compartilhados entre diversas funções
    public static String emitirDocumento(String modelo, String conteudo, String tpConteudo) throws Exception {
        String urlEnvio;

        switch (modelo) {
            case "66": {
                urlEnvio = endpoints.NF3eEnvio;
                break;
            }
            default: {
                throw new Exception("Não definido endpoint de envio para o modelo " + modelo);
            }

        }

        Genericos.gravarLinhaLog(modelo, "[ENVIO_DADOS]");
        Genericos.gravarLinhaLog(modelo, conteudo);

        String resposta = enviaConteudoParaAPI(conteudo, urlEnvio, tpConteudo);

        Genericos.gravarLinhaLog(modelo, "[ENVIO_RESPOSTA]");
        Genericos.gravarLinhaLog(modelo, resposta);

        return resposta;
    }

    public static String consultarStatusProcessamento(String modelo, ConsStatusProcessamentoReq consStatusProcessamentoReq) throws Exception {
        String urlConsulta;

        switch (modelo) {
            case "66": {
                urlConsulta = endpoints.NF3eConsStatusProcessamento;
                break;
            }
            default: {
                throw new Exception("Não definido endpoint de consulta para o modelo " + modelo);
            }
        }

        String json = objectMapper.writeValueAsString(consStatusProcessamentoReq);

        Genericos.gravarLinhaLog(modelo, "[CONSULTA_DADOS]");
        Genericos.gravarLinhaLog(modelo, json);

        String resposta = enviaConteudoParaAPI(json, urlConsulta, "json");

        Genericos.gravarLinhaLog(modelo, "[CONSULTA_RESPOSTA]");
        Genericos.gravarLinhaLog(modelo, resposta);
        return resposta;
    }

    public static String downloadDocumento(String modelo, DownloadReq downloadReq) throws Exception {
        String urlDownload, status;
        ObjectMapper objectMapper = new ObjectMapper();
        switch (modelo) {

            case "66": {
                urlDownload = endpoints.NF3eDownload;
                break;
            }

            default: {
                throw new Exception("Não definido endpoint de Download para o modelo " + modelo);
            }
        }

        String json = objectMapper.writeValueAsString(downloadReq);

        Genericos.gravarLinhaLog(modelo, "[DOWNLOAD_DADOS]");
        Genericos.gravarLinhaLog(modelo, json);

        String resposta = enviaConteudoParaAPI(json, urlDownload, "json");

        JsonNode respostaJSON = objectMapper.readTree(resposta);
        status = respostaJSON.get("status").asText();

        // O retorno da API será gravado somente em caso de erro,
        // para não gerar um log extenso com o PDF e XML
        if ((!status.equals("200")) & (!status.equals("100")))
        {
            Genericos.gravarLinhaLog(modelo, "[DOWNLOAD_RESPOSTA]");
            Genericos.gravarLinhaLog(modelo, resposta);
        }
        else
        {
            Genericos.gravarLinhaLog(modelo, "[DOWNLOAD_STATUS]");
            Genericos.gravarLinhaLog(modelo, status);
        }

        return resposta;
    }

    public static String downloadDocumentoESalvar(String modelo, DownloadReq downloadReq, String caminho, String nome, boolean exibeNaTela) throws Exception {
        
        String resposta = downloadDocumento(modelo, downloadReq);
        String status;

        JsonNode respostaJSON = objectMapper.readTree(resposta);
        status = respostaJSON.get("status").asText();

        if ((status.equals("200")) || (status.equals("100"))){
            try {
                File diretorio = new File(caminho);
                if(!diretorio.exists()) diretorio.mkdirs();
                if(!caminho.endsWith("\\")) caminho += "\\";
            }
            catch (Exception ex){
                Genericos.gravarLinhaLog(modelo, "[CRIAR_DIRETORIO]" + caminho);
                Genericos.gravarLinhaLog(modelo, ex.getMessage());
                throw new Exception("Erro: " + ex.getMessage());
            }

            if (!modelo.equals("65")){
                if (downloadReq.tpDown.toUpperCase().contains("X")){
                    String xml = respostaJSON.get("xml").asText();
                    Genericos.salvarXML(xml, caminho, nome);
                }
                if (downloadReq.tpDown.toUpperCase().contains("P"))
                {
                    String pdf = respostaJSON.get("pdf").asText();
                    Genericos.salvarPDF(pdf, caminho, nome);

                    if (exibeNaTela)
                    {
                        File arq = new File(caminho + nome + ".pdf");
                        Desktop.getDesktop().open(arq);
                    }
                }
            }
            else {
                String xml = respostaJSON.get("nfeProc").get("xml").asText();
                Genericos.salvarXML(xml, caminho, nome);

                String pdf = respostaJSON.get("pdf").asText();
                Genericos.salvarPDF(pdf, caminho, nome);

                if (exibeNaTela){
                    File arq = new File(caminho + nome + ".pdf");
                    Desktop.getDesktop().open(arq);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro, veja o retorno da API para mais informaçõe");
        }
        return resposta;
    }

    public static String downloadEvento(String modelo, DownloadEventoReq downloadEventoReq) throws Exception {
        String urlDownloadEvento;

        switch (modelo){
            case "66": {
                urlDownloadEvento = endpoints.NF3eDownloadEvento;
                break;
            }
            default:{
                throw new Exception("Não definido endpoint de download de evento para o modelo " + modelo);
            }
        }

        String json = objectMapper.writeValueAsString(downloadEventoReq);

        Genericos.gravarLinhaLog(modelo, "[DOWNLOAD_EVENTO_DADOS]");
        Genericos.gravarLinhaLog(modelo, json);

        String resposta = enviaConteudoParaAPI(json, urlDownloadEvento, "json");

        JsonNode respostaJSON = objectMapper.readTree(resposta);
        String status = respostaJSON.get("status").asText();

        // O retorno da API será gravado somente em caso de erro,
        // para não gerar um log extenso com o PDF e XML
        if ((!status.equals("200")) && (!status.equals("100"))){
            Genericos.gravarLinhaLog(modelo, "[DOWNLOAD_EVENTO_RESPOSTA]");
            Genericos.gravarLinhaLog(modelo, resposta);
        }
        else {
            Genericos.gravarLinhaLog(modelo, "[DOWNLOAD_EVENTO_STATUS]");
            Genericos.gravarLinhaLog(modelo, status);
        }
        return resposta;
    }

    public static String downloadEventoESalvar(String modelo, DownloadEventoReq downloadEventoReq, String caminho, String chave, String nSeqEvento, boolean exibeNaTela) throws Exception {
        String tpEventoSalvar = "";

        String resposta = downloadEvento(modelo, downloadEventoReq);
        JsonNode respostaJSON = objectMapper.readTree(resposta);
        String status = respostaJSON.get("status").asText();

        if (status.equals("200") || status.equals("100")){
            try {
                File diretorio = new File(caminho);
                if(!diretorio.exists()) diretorio.mkdirs();
                if(!caminho.endsWith("\\")) caminho += "\\";
            } catch (Exception ex){
                Genericos.gravarLinhaLog(modelo, "[CRIAR_DIRETORIO]" + caminho);
                Genericos.gravarLinhaLog(modelo, ex.getMessage());
                throw new Exception("Erro: " + ex.getMessage());
            }
            if (!modelo.equals("65")){

                if (downloadEventoReq.tpEvento.toUpperCase().equals("CANC")){
                    tpEventoSalvar = "110111";
                }
                else if (downloadEventoReq.tpEvento.toUpperCase().equals("ENC")){
                    tpEventoSalvar = "110110";
                }
                else{
                    tpEventoSalvar = "110115";
                }

                //Verifica quais arquivos deve salvar
                if (downloadEventoReq.tpDown.toUpperCase().contains("X")){
                    String xml = respostaJSON.get("xml").asText();
                    Genericos.salvarXML(xml, caminho, tpEventoSalvar + chave + nSeqEvento + "-procEven");
                }
                if (downloadEventoReq.tpDown.toUpperCase().contains("P")){
                    String pdf = respostaJSON.get("pdf").asText();
                    if (pdf != null && !pdf.equals("")){
                        Genericos.salvarPDF(pdf, caminho, tpEventoSalvar + chave + nSeqEvento + "-procEven");
                        if (exibeNaTela){
                            File arq = new File(caminho + tpEventoSalvar + chave + nSeqEvento + "procEven.pdf");
                            Desktop.getDesktop().open(arq);
                        }
                    }
                }
            }
            else {
                String xml = respostaJSON.get("nfeProc").get("xml").asText();
                Genericos.salvarXML(xml, caminho, tpEventoSalvar + chave + nSeqEvento + "-procEven");

                String pdf = respostaJSON.get("pdfCancelamento").asText();
                Genericos.salvarPDF(pdf, caminho, tpEventoSalvar + chave + nSeqEvento + "procEven");

                if (exibeNaTela){
                    File arq = new File(caminho + tpEventoSalvar + chave + nSeqEvento + "procEven.pdf");
                    Desktop.getDesktop().open(arq);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, veja o retorno da API para mais informações");
        }
        return resposta;
    }

    public static String cancelarDocumento(String modelo, CancelarReq cancelarReq) throws Exception {
        String urlCancelamento;
        switch (modelo){
            case "66": {
                urlCancelamento = endpoints.NF3eCancelamento;
                break;
            }

            default:
            {
                throw new Exception("Não definido endpoint de cancelamento para o modelo " + modelo);
            }
        }

        String json = objectMapper.writeValueAsString(cancelarReq);

        Genericos.gravarLinhaLog(modelo, "[CANCELAMENTO_DADOS]");
        Genericos.gravarLinhaLog(modelo, json);

        String resposta = enviaConteudoParaAPI(json, urlCancelamento, "json");

        Genericos.gravarLinhaLog(modelo, "[CANCELAMENTO_RESPOSTA]");
        Genericos.gravarLinhaLog(modelo, resposta);

        return resposta;
    }

    public static String cancelarDocumentoESalvar(String modelo, CancelarReq cancelarReq, DownloadEventoReq downloadEventoReq, String caminho, String chave, boolean exibeNaTela) throws Exception {
        
        String resposta = cancelarDocumento(modelo, cancelarReq);
        JsonNode respostaJSON = objectMapper.readTree(resposta);
        String status = respostaJSON.get("status").asText();

        if (status.equals("200") || status.equals("135")){
            String cStat = respostaJSON.get("cStat").asText();
            if (cStat.equals("135")){
                String respostaDownloadEvento = downloadEventoESalvar(modelo, downloadEventoReq, caminho, chave, "1", exibeNaTela);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cancelar, veja o retorno da API para mais informações");
        }
        return resposta;
    }

    public static String consultarSituacaoDocumento(String modelo, ConsSitReq consSitReq) throws Exception {
        String urlConsSit;

        switch (modelo){
            case "66":{
                urlConsSit = endpoints.NF3eConsSit;
                break;
            }

            default:{
                throw new Exception("Não definido endpoint de consulta de situação para o modelo " + modelo);
            }
        }

        String json = objectMapper.writeValueAsString(consSitReq);

        Genericos.gravarLinhaLog(modelo, "[CONS_SIT_DADOS]");
        Genericos.gravarLinhaLog(modelo, json);

        String resposta = enviaConteudoParaAPI(json, urlConsSit, "json");

        Genericos.gravarLinhaLog(modelo, "[CONS_SIT_RESPOSTA]");
        Genericos.gravarLinhaLog(modelo, resposta);

        return resposta;
    }

    public static String listarNSNRecs(String modelo, ListarNSNRecReq listarNSNRecReq) throws Exception {
        String urlListarNSNRecs;

        switch (modelo){
            case "66": {
                urlListarNSNRecs = endpoints.NF3eListarNSNRecs;
                break;
            }

            default:
            {
                throw new Exception("Não definido endpoint de listagem de nsNRec para o modelo " + modelo);
            }
        }

        String json = objectMapper.writeValueAsString(listarNSNRecReq);

        Genericos.gravarLinhaLog(modelo, "[LISTAR_NSNRECS_DADOS]");
        Genericos.gravarLinhaLog(modelo, json);

        String resposta = enviaConteudoParaAPI(json, urlListarNSNRecs, "json");

        Genericos.gravarLinhaLog(modelo, "[LISTAR_NSNRECS_RESPOSTA]");
        Genericos.gravarLinhaLog(modelo, resposta);

        return resposta;
    }
}