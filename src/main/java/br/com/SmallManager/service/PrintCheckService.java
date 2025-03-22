package br.com.SmallManager.service;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrintQuality;
import java.io.File;
import java.io.FileNotFoundException;

public class PrintCheckService {

    public static void main(String[] args) throws FileNotFoundException, PrintException {

        File file = new File("C:/Users/Almeida/Downloads/BoletoICMBio.pdf");

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        if (printServices.length == 0) {
            System.out.println("Nenhuma impressora encontrada.");
            return;
        }

        for (PrintService print : printServices){
            System.out.println(print.getName());
        }

        PrintService printService = printServices[5]; // Alterar para o nome da impressora se necessário

        // Configuração dos atributos da impressão
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(MediaSizeName.ISO_A4); // Tamanho do papel
        attributes.add(PrintQuality.HIGH);   // Qualidade alta

        // Job de impressão
        DocPrintJob job = printService.createPrintJob();

        // Tipo de arquivo que será impresso
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        // Cria o documento para ser enviado à impressora
        Doc doc = new SimpleDoc(new java.io.FileInputStream(file), flavor, null);

        // Envia o documento para a impressora
        job.print(doc, attributes);
    }

}
