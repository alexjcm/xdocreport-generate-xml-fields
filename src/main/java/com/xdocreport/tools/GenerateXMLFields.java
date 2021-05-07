package com.xdocreport.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.document.SyntaxKind;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

/**
 * Allows to generate XML fields with HTML metadata.
 * 
 * @author alexjcm
 */
public class GenerateXMLFields {

    public static void main(String[] args) throws XDocReportException, IOException, Exception {

        // 1) Create FieldsMetadata by setting Velocity as template engine
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        // 2) Add fields with HTML metadata            
        fieldsMetadata.addFieldAsTextStyling("cn_cabeceraDelCertificado", SyntaxKind.Html);
        fieldsMetadata.addFieldAsTextStyling("cn_cuerpoDelCertificado", SyntaxKind.Html);

        // Add fields metadata           
        fieldsMetadata.addField("nombreDelEstudiante", false, "", "", false);
        fieldsMetadata.addField("cedulaDelEstudiante", false, "", "", false);
        fieldsMetadata.addField("fechaDeEmision", false, "", "", false);
        fieldsMetadata.addField("nombreDeLaSecretaria", false, "", "", false);
        fieldsMetadata.addField("nombreDelGestor", false, "", "", false);
        fieldsMetadata.addField("nombreDelDecano", false, "", "", false);
     
        fieldsMetadata.addField("c1_nombreDeEmpresaMantenimiento", false, "", "", false);
        fieldsMetadata.addField("c1_fechaDeInicioMantenimiento", false, "", "", false);
        fieldsMetadata.addField("c1_fechaDeFinMantenimiento", false, "", "", false);
        fieldsMetadata.addField("c1_nombreDeEmpresaRedes", false, "", "", false);
        fieldsMetadata.addField("c1_fechaDeInicioRedes", false, "", "", false);
        fieldsMetadata.addField("c1_fechaDeFinRedes", false, "", "", false);
        fieldsMetadata.addField("c1_nombreDeEmpresaSW", false, "", "", false);
        fieldsMetadata.addField("c1_fechaDeInicioSW", false, "", "", false);
        fieldsMetadata.addField("c1_fechaDeFinSW", false, "", "", false);

        fieldsMetadata.addField("c2_fechaInicioDeEstudios", false, "", "", false);
        fieldsMetadata.addField("c2_fechaFinDeEstudios", false, "", "", false);
        fieldsMetadata.addField("c2_fechaInicioPrimeraProrroga", false, "", "", false);
        fieldsMetadata.addField("c2_fechaFinPrimeraProrroga", false, "", "", false);
        fieldsMetadata.addField("c2_fechaInicioSegundaProrroga", false, "", "", false);
        fieldsMetadata.addField("c2_fechaFinSegundaProrroga", false, "", "", false);

        fieldsMetadata.addField("c3_nombreDelEvento", false, "", "", false);
        fieldsMetadata.addField("c3_lugarDelEvento", false, "", "", false);
        fieldsMetadata.addField("c3_tematicaDelEvento", false, "", "", false);
        fieldsMetadata.addField("c3_fechaInicioDelEvento", false, "", "", false);
        fieldsMetadata.addField("c3_fechaFinDelEvento", false, "", "", false);
        fieldsMetadata.addField("c3_horasDelEvento", false, "", "", false);

        // 3) Generate XML fields in the file "*.fields.xml".
        // Extension *.fields.xml is very important to use it with MS Macro XDocReport.dotm
        String pathFile = "/home/alexjcm/Descargas/certificados.fields.xml";
        if (!pathFile.endsWith(".fields.xml")) {
            throw new Exception("The file must end with the extension .fields.xml");
        }

        File xmlFieldsFile = new File(pathFile);
        // FieldsMetadata#saveXML is called with true to indent the XML.         
        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
    }
}
