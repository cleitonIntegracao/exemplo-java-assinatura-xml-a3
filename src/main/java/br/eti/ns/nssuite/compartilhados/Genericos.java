package br.eti.ns.nssuite.compartilhados;

import sun.security.pkcs11.SunPKCS11;

import java.io.*;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Genericos {
    public static void gravarLinhaLog(String modelo, String conteudo) throws IOException, IOException {

        //Lendo Path do computador utlilizado e criando a pasta log
        String path = System.getProperty("user.dir");
        File localSalvar = new File(path + "\\log");
        if (!localSalvar.exists()) {
            localSalvar.mkdirs();
        }
        //Data atual ddmmyy
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
        String dataAtual = formatador.format(data);

        //Cria .txt com a data atual
        FileWriter txt = new FileWriter( localSalvar + "\\" + dataAtual + ".txt", true);
        BufferedWriter gravarArq = new BufferedWriter(txt);

        //Data e hora atuais
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dataAtual = formatador.format(data);

        //grava as informações dentro do .txt
        gravarArq.write( dataAtual + "_" + modelo + " - "+ conteudo);
        gravarArq.newLine();
        gravarArq.close();
        txt.close();
    }

    public static void salvarJSON(String json, String caminho, String nome, String tpEvento, String nSeqEvento) throws IOException{
        String localParaSalvar = caminho + tpEvento + nome + nSeqEvento + ".json";
        File arq = new File(localParaSalvar);
        if(arq.exists()){
            arq.delete();
        }
        FileWriter fileEdit = new FileWriter(arq);
        try (BufferedWriter bfw = new BufferedWriter(fileEdit)) {
            bfw.write(json);
            bfw.flush();
        }
    }

    public static void salvarXML(String xml, String caminho, String nome) throws IOException{
        String localParaSalvar = caminho + nome + ".xml";
        String conteudoReplace = xml.replace("\\","");
        File arq = new File(localParaSalvar);
        if(arq.exists()){
            arq.delete();
        }
        FileOutputStream fop = new FileOutputStream(arq);
        FileWriter fileEdit = new FileWriter(arq);
        try (BufferedWriter bfw = new BufferedWriter(fileEdit)) {
            bfw.write(conteudoReplace);
            bfw.flush();
        }
    }

    public static void salvarPDF(String pdf, String caminho, String nome) throws FileNotFoundException, IOException{
        String localParaSalvar = caminho + nome + ".pdf";
        File arq = new File(localParaSalvar);
        if(arq.exists()){
            arq.delete();
        }
        try (FileOutputStream fop = new FileOutputStream(arq)) {
            fop.write(Base64.getDecoder().decode(pdf));
            fop.flush();
        }
    }


    public static void carregarCertificado(boolean repositorio, String senha, String localCertificado) {
        KeyStore ks;
        try {
            if(repositorio){
                ks = KeyStore.getInstance("Windows-MY", "SunSCAPY");
                ks.load(null,null);
            } else{
                Provider p = new SunPKCS11(localCertificado);
                Security.addProvider(p);
                ks = KeyStore.getInstance("PCS11");
                ks.load(null, senha.toCharArray());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
