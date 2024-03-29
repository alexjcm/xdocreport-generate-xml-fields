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
        // SOLICITUDES
        fieldsMetadata.addField("requestCode", false, "", "", false);
        fieldsMetadata.addField("s_numeroDeProrroga", false, "", "", false);
        fieldsMetadata.addField("s_tituloTrabajoTitulacion", false, "", "", false);
        fieldsMetadata.addField("s_directorTrabajoTitulacion", false, "", "", false);
        fieldsMetadata.addField("s_mesYearInicioPeriodoAcademico", false, "", "", false);
        fieldsMetadata.addField("s_mesYearFinPeriodoAcademico", false, "", "", false);
        fieldsMetadata.addField("s_nombreDelCertificado", false, "", "", false);
        fieldsMetadata.addField("s_motivoSolicitudCertificado", false, "", "", false);

        // CERTIFICADOS
        fieldsMetadata.addField("nombreDeLaCarrera", false, "", "", false);
        fieldsMetadata.addField("nombreDeLaCarreraAux", false, "", "", false);
        fieldsMetadata.addField("nombreDelEstudiante", false, "", "", false);
        fieldsMetadata.addField("cedulaDelEstudiante", false, "", "", false);
        fieldsMetadata.addField("fechaDeEmision", false, "", "", false);
        fieldsMetadata.addField("nombreDeLaSecretaria", false, "", "", false);
        fieldsMetadata.addField("nombreDelGestor", false, "", "", false);
        fieldsMetadata.addField("nombreDelDecano", false, "", "", false);
        fieldsMetadata.addField("certificateCode", false, "", "", false);

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
        fieldsMetadata.addField("c2_numeroDeProrroga", false, "", "", false);
        fieldsMetadata.addField("c2_fechaInicioProrroga", false, "", "", false);
        fieldsMetadata.addField("c2_fechaFinProrroga", false, "", "", false);

        fieldsMetadata.addField("c3_fechaInicioDeEstudios", false, "", "", false);
        fieldsMetadata.addField("c3_fechaFinDeEstudios", false, "", "", false);
        fieldsMetadata.addField("c3_fechaInicioPrimeraProrroga", false, "", "", false);
        fieldsMetadata.addField("c3_fechaFinPrimeraProrroga", false, "", "", false);
        fieldsMetadata.addField("c3_fechaInicioSegundaProrroga", false, "", "", false);
        fieldsMetadata.addField("c3_fechaFinSegundaProrroga", false, "", "", false);

        fieldsMetadata.addField("c4_nombreDelEvento", false, "", "", false);
        fieldsMetadata.addField("c4_lugarDelEvento", false, "", "", false);
        fieldsMetadata.addField("c4_tematicaDelEvento", false, "", "", false);
        fieldsMetadata.addField("c4_fechaInicioDelEvento", false, "", "", false);
        fieldsMetadata.addField("c4_fechaFinDelEvento", false, "", "", false);
        fieldsMetadata.addField("c4_horasDelEvento", false, "", "", false);
        fieldsMetadata.addField("c4_tipoCertEvento", false, "", "", false);

        // 3) Generate XML fields in the file "*.fields.xml".
        // Extension *.fields.xml is very important to use it with MS Macro XDocReport.dotm
        String pathFile = "/home/ajcm/Downloads/solicitudes_y_certificados.fields.xml";
        if (!pathFile.endsWith(".fields.xml")) {
            throw new Exception("The file must end with the extension .fields.xml");
        }

        File xmlFieldsFile = new File(pathFile);
        // FieldsMetadata#saveXML is called with true to indent the XML.         
        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
    }
}
