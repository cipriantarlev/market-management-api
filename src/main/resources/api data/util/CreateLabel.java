package ii.cipriantarlev.marketmanagementapi.utils;

import org.docx4j.jaxb.Context;
import org.docx4j.model.properties.table.tr.TrHeight;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.sharedtypes.STOnOff;
import org.docx4j.wml.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateLabel {

    public static void main(String[] args) throws Exception {
        new CreateLabel().generatePriceLabel();
    }

    public void generatePriceLabel() throws Exception {
        WordprocessingMLPackage.createPackage();
        ObjectFactory factory = Context.getWmlObjectFactory();
        WordprocessingMLPackage wordPackage = new WordprocessingMLPackage();
        MainDocumentPart mainDocumentPart = new MainDocumentPart();
        Body body = factory.createBody();
        Document wmlDocumentEl = factory.createDocument();

        generateTable(body, factory);
        setDocumentSizeAndOrientation(factory, body);

        wmlDocumentEl.setBody(body);
        mainDocumentPart.setJaxbElement(wmlDocumentEl);
        wordPackage.addTargetPart(mainDocumentPart);

        File exportFile = new File("helloWorld1.docx");
        wordPackage.save(exportFile);
    }

    private void setDocumentSizeAndOrientation(ObjectFactory factory, Body body) {
        SectPr sectPr = factory.createSectPr();
        SectPr.PgSz sectPrPgSz = factory.createSectPrPgSz();
        SectPr.PgMar sectPrPgMar = factory.createSectPrPgMar();

        sectPr.setRsidR("009F0020");
        sectPr.setRsidSect("00592D7E");
        sectPr.setPgSz(sectPrPgSz);
        sectPr.setPgMar(sectPrPgMar);

        sectPrPgSz.setW(BigInteger.valueOf(16838));
        sectPrPgSz.setH(BigInteger.valueOf(11906));
        sectPrPgSz.setOrient(STPageOrientation.LANDSCAPE);
        sectPrPgSz.setCode(BigInteger.valueOf(9));

        sectPrPgMar.setTop(BigInteger.valueOf(288));
        sectPrPgMar.setRight(BigInteger.valueOf(288));
        sectPrPgMar.setBottom(BigInteger.valueOf(288));
        sectPrPgMar.setLeft(BigInteger.valueOf(288));
        sectPrPgMar.setGutter(BigInteger.ZERO);

        body.setSectPr(sectPr);
    }

    private void generateTable(Body body, ObjectFactory factory) {
        Tbl tbl = new Tbl();
        TblPr tblPr = new TblPr();

        tblPr.setTblStyle(setTableStyle("TableGrid"));
        tblPr.setTblW(setTableWidth(BigInteger.ZERO, "auto"));
        tblPr.setTblBorders(setTableBorders(
                setCellBorder(STBorder.DOUBLE, BigInteger.valueOf(12), BigInteger.ZERO, "auto")));
        tblPr.setTblLook(setTableLook("04A0", STOnOff.ZERO, STOnOff.ONE));

        tbl.setTblPr(tblPr);
        tbl.setTblGrid(setTableGrid(BigInteger.valueOf(4043)));

        List<Object> rows = tbl.getContent();
        for (int i = 0; i < 7; i++) {
            Tr row = new Tr();
            setRowStyle(row, BigInteger.valueOf(2102));

            List<Object> cells = row.getContent();
            for (int j = 0; j < 4; j++) {
                Tc cell = new Tc();

                cell.setTcPr(setCellStyle("dxa", BigInteger.valueOf(4043)));

                P organizationParagraph = createParagraph(
                        "Market Cristina", factory, BigInteger.valueOf(16), JcEnumeration.CENTER);
                P delimiterParagraph = createParagraph(
                        "-----------------------------------------------------------------------------",
                        factory, BigInteger.valueOf(16), JcEnumeration.CENTER);
                P nameRomParagraph = createParagraph(
                        "Suc Naturalis 1L",
                        factory, BigInteger.valueOf(24), JcEnumeration.LEFT);
                P nameRusParagraph = createParagraph(
                        "Сок Naturalis 1Л",
                        factory, BigInteger.valueOf(22), JcEnumeration.LEFT);
                P priceParagraph = createParagraph(
                        "134.57",
                        factory, BigInteger.valueOf(64), JcEnumeration.CENTER);
                P barcodeParagraph = createParagraph(
                        "4840695069493092",
                        factory, BigInteger.valueOf(14), JcEnumeration.RIGHT);
                P dateTimeParagraph = createParagraph(
                        "03.06.2022 14:06",
                        factory, BigInteger.valueOf(12), JcEnumeration.RIGHT);

                cell.getContent().add(organizationParagraph);
                cell.getContent().add(delimiterParagraph);
                cell.getContent().add(nameRomParagraph);
                cell.getContent().add(nameRusParagraph);
                cell.getContent().add(priceParagraph);
                cell.getContent().add(barcodeParagraph);
                cell.getContent().add(dateTimeParagraph);



                cells.add(cell);
            }
            rows.add(row);
        }
        body.getContent().add(tbl);
    }

    private List<P> populatePriceLabel(ObjectFactory factory) {

        P organizationParagraph = createParagraph(
                "Market Cristina", factory, BigInteger.valueOf(16), JcEnumeration.CENTER);
        P delimiterParagraph = createParagraph(
                "-----------------------------------------------------------------------------",
                factory, BigInteger.valueOf(16), JcEnumeration.CENTER);
        P nameRomParagraph = createParagraph(
                "Suc Naturalis 1L",
                factory, BigInteger.valueOf(24), JcEnumeration.LEFT);
        P nameRusParagraph = createParagraph(
                "Сок Naturalis 1Л",
                factory, BigInteger.valueOf(22), JcEnumeration.LEFT);
        P priceParagraph = createParagraph(
                "134.57",
                factory, BigInteger.valueOf(64), JcEnumeration.CENTER);
        P barcodeParagraph = createParagraph(
                "4840695069493092",
                factory, BigInteger.valueOf(14), JcEnumeration.RIGHT);
        P dateTimeParagraph = createParagraph(
                "03.06.2022 14:06",
                factory, BigInteger.valueOf(12), JcEnumeration.RIGHT);

        return new ArrayList<>(List.of(organizationParagraph, delimiterParagraph, nameRomParagraph, nameRusParagraph,
                priceParagraph, barcodeParagraph, dateTimeParagraph));
    }

    private P createParagraph(String text, ObjectFactory factory, BigInteger sizeValue, JcEnumeration textAlignment) {
        P paragraph = factory.createP();
        PPr pPr = factory.createPPr();

        Jc jc = factory.createJc();
        jc.setVal(textAlignment);
        pPr.setJc(jc);

        ParaRPr rPr = factory.createParaRPr();

        HpsMeasure hpsMeasure = factory.createHpsMeasure();
        hpsMeasure.setVal(sizeValue);

        rPr.setSz(hpsMeasure);
        rPr.setSzCs(hpsMeasure);

        PPrBase.Spacing spacing = factory.createPPrBaseSpacing();
        spacing.setAfter(BigInteger.ZERO);

        pPr.setRPr(rPr);
        pPr.setSpacing(spacing);

        paragraph.setPPr(pPr);

        R run = factory.createR();
        Text t = factory.createText();
        t.setValue(text);

        RPr rpr = factory.createRPr();
        rpr.setSz(hpsMeasure);
        rpr.setSzCs(hpsMeasure);
        run.setRPr(rpr);

        run.getContent().add(t);
        paragraph.getContent().add(run);


        return paragraph;
    }

    private CTTblPrBase.TblStyle setTableStyle(String style) {
        CTTblPrBase.TblStyle tblStyle = new CTTblPrBase.TblStyle();
        tblStyle.setVal(style);

        return tblStyle;
    }

    private TblWidth setTableWidth(BigInteger width, String type) {
        TblWidth tblW = new TblWidth();
        tblW.setW(BigInteger.ZERO);
        tblW.setType("auto");

        return tblW;
    }

    private TblBorders setTableBorders(CTBorder border) {
        TblBorders tblBorders = new TblBorders();
        tblBorders.setTop(border);
        tblBorders.setLeft(border);
        tblBorders.setBottom(border);
        tblBorders.setRight(border);
        tblBorders.setInsideH(border);
        tblBorders.setInsideV(border);

        return tblBorders;
    }

    private CTBorder setCellBorder(STBorder value, BigInteger size, BigInteger space, String color) {
        CTBorder border = new CTBorder();
        border.setVal(value);
        border.setSz(size);
        border.setSpace(space);
        border.setColor(color);

        return border;
    }

    private CTTblLook setTableLook(String tblLookValue, STOnOff zero, STOnOff one) {
        CTTblLook tblLook = new CTTblLook();
        tblLook.setVal(tblLookValue);
        tblLook.setFirstRow(one);
        tblLook.setLastRow(zero);
        tblLook.setFirstColumn(one);
        tblLook.setLastColumn(zero);
        tblLook.setNoHBand(zero);
        tblLook.setNoVBand(one);

        return tblLook;
    }

    private TblGrid setTableGrid(BigInteger width) {
        TblGrid tblGrid = new TblGrid();
        TblGridCol gridCol = new TblGridCol();
        gridCol.setW(width);
        tblGrid.getGridCol().clear();
        tblGrid.getGridCol().add(gridCol);

        return tblGrid;
    }

    private void setRowStyle(Tr row, BigInteger height) {
        row.setRsidR("00F31552");
        row.setRsidTr("00F31552");
        TrPr trPr = new TrPr();
        CTHeight ctHeight = new CTHeight();
        ctHeight.setVal(height);
        TrHeight trHeight = new TrHeight(ctHeight);
        trHeight.set(trPr);
        row.setTrPr(trPr);
    }

    private TcPr setCellStyle(String type, BigInteger width) {
        TcPr tcPr = new TcPr();
        TblWidth tcW = new TblWidth();
        tcW.setType(type);
        tcW.setW(width);
        tcPr.setTcW(tcW);

        return tcPr;
    }
}
