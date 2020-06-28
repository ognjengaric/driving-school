package com.ognjengaric.demo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Role;
import com.ognjengaric.demo.domain.User;

import javax.persistence.Table;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class PdfGenerator {

    private  static Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

    public static ByteArrayInputStream usersReport(List<User> users){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{

            List<String> headers = Arrays.asList("Name", "Surname", "Role", "Current Licence");
            PdfPTable table = initTable(headers.size());

            headers.stream()
                    .map(e-> setHeaderCell(e, true))
                    .forEach(table::addCell);

            users.forEach(user -> {;

                String name = user.getName();
                String surname = user.getSurname();
                String auth = ((Role)user.getAuthorities().toArray()[0]).getName();

                String licence = "";
                if(user instanceof Candidate){
                    licence = ((Candidate) user).getCurrentLicence().toString();
                }

                List<String> values = Arrays.asList(name, surname, auth, licence);

                values.stream()
                        .map(e -> setHeaderCell(e, false))
                        .forEach(table::addCell);

            });

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException e){
            System.out.println(e.getMessage());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPCell setHeaderCell(String name, boolean isHeader){
        PdfPCell cell;

        if(isHeader){
            cell = new PdfPCell(new Phrase(name, headFont));
        } else {
            cell = new PdfPCell(new Phrase(name));
        }

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        return cell;
    }

    private static PdfPTable initTable(int rows) throws DocumentException {
        PdfPTable table = new PdfPTable(rows);
        table.setWidthPercentage(80);

        int [] widths = new int[rows];
        Arrays.fill(widths,3);

        table.setWidths(widths);
        return table;
    }
}
