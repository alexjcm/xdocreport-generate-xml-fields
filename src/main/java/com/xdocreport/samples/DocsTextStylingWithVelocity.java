package com.xdocreport.samples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.core.document.SyntaxKind;
//import fr.opensagres.xdocreport.document.docx.DocxConstants;
//import fr.opensagres.xdocreport.document.docx.preprocessor.dom.DOMFontsPreprocessor;

/**
 * Allows you to insert data with or without HTML tags into documents in .odt or .docx format. 
 * 
 * @author alexjcm
 */
public class DocsTextStylingWithVelocity {

    private static final String ODT_EXT = ".odt";
    private static final String DOCX_EXT = ".docx";

    public static void main(String[] args) {

        try {
            //String pathDocIn = "/home/alexjcm/Descargas/DOCXHtmlTextStyle.docx";
            String pathDocIn = "/home/alexjcm/Descargas/ODTHtmlTextStyle.odt";
            if (!(pathDocIn.endsWith(DOCX_EXT) || pathDocIn.endsWith(ODT_EXT))) {
                throw new Exception("La plantilla debe ser un documento .docx o .odt, no se admiten otros formatos.");
            }

            // 1) Load Docx file by filling Velocity template engine and cache it to the registry
            File file = new File(pathDocIn);
            InputStream in = new FileInputStream(file);
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            /*report.addPreprocessor( DocxConstants.WORD_DOCUMENT_XML_ENTRY, DOMFontsPreprocessor.INSTANCE );
            report.addPreprocessor( DocxConstants.WORD_HEADER_XML_ENTRY, DOMFontsPreprocessor.INSTANCE );
            report.addPreprocessor( DocxConstants.WORD_FOOTER_XML_ENTRY, DOMFontsPreprocessor.INSTANCE );*/
           
            // 2) Create fields metadata to manage text styling
            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.addFieldAsTextStyling("comments_html", SyntaxKind.Html);

            // 3) Create context Java model
            IContext context = report.createContext();            
            context.put("comments_html", "Here are severals styles :<ul>" + "<li><strong>Bold</strong> style.</li>"
                    + "<li><em>Italic</em> style.</li>" + "<li><strong><em>BoldAndItalic</em></strong> style.</li>"
                    + "</ul>" + "<p>Here are 3 styles :</p>" + "<ol>" + "<li><strong>Bold</strong> style.</li>"
                    + "<li><em>Italic</em> style.</li>" + "<li><strong><em>BoldAndItalic</em></strong> style.</li>"
                    + "</ol>"
                    + "<p><a href=\"http://code.google.com/p/xdocreport/\">XDocReport</a> can manage those styles. "
                    + "Now some <strong>headers</strong>:" + "</p>" + "<h1>Title 1</h1>" + "<p>Some text...</p>"
                    + "<h2>Title 2</h2>" + "<p>Some text...</p>" + "<h3>Title 3</h3>" + "<p>Some text...</p>");
            //context.put(DOMFontsPreprocessor.FONT_NAME_KEY, "Magneto");
            //context.put(DOMFontsPreprocessor.FONT_SIZE_KEY, 40);
                   
            // 4) Generate report by merging Java model with the DOC
            String pathDocOut = pathDocIn.replace(".", "_out.");
            OutputStream out = new FileOutputStream(new File(pathDocOut));
            report.process(context, out);

        } catch (IOException | XDocReportException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
