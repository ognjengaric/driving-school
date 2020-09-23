package com.ognjengaric.demo.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ognjengaric.demo.domain.*;

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
            PdfPTable table = initTable(headers);

            users.forEach(user -> {

                String name = user.getName();
                String surname = user.getSurname();
                String auth = ((Role)user.getAuthorities().toArray()[0]).getName();

                String licence = "";
                if(user instanceof Candidate){
                    licence = ((Candidate) user).getCurrentLicence().toString();
                }

                List<String> values = Arrays.asList(name, surname, auth, licence);

                setTableDate(values, table);

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

    public static ByteArrayInputStream classesReport(List<DrivingClass> drivingClasses){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            List<String> headers = Arrays.asList("Instructor", "Candidate", "Start Time", "End Time");
            PdfPTable table = initTable(headers);

            drivingClasses.forEach(drivingClass -> {
                String instructor = drivingClass.getInstructor().getName()+ " " + drivingClass.getInstructor().getSurname();
                String candidate = drivingClass.getCandidate().getName() + " " + drivingClass.getCandidate().getSurname();
                String start_time = drivingClass.getStart_dt().toString();
                String end_time = drivingClass.getEnd_dt().toString();

                List<String> values = Arrays.asList(instructor, candidate, start_time, end_time);

                setTableDate(values, table);
            });

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();
        }
        catch (DocumentException e){
            System.out.println(e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream streetsReport(List<Street> streets){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            List<String> headers = Arrays.asList("Name", "Intensity", "Road Type");
            PdfPTable table = initTable(headers);

            streets.forEach(street -> {
                String name = street.getName();
                String intensity = street.getIntensity().toString();
                String roadType = street.getRoadType().toString();

                List<String> values = Arrays.asList(name, intensity, roadType);

                setTableDate(values, table);
            });

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();
        }
        catch (DocumentException e){
            System.out.println(e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream routesReport(List<Route> routes){
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            List<String> headers = Arrays.asList("Id", "Category Type", "Time" , "Distance");
            PdfPTable table = initTable(headers);

            routes.forEach(route -> {
                String id = route.getId().toString();
                String category = route.getCategoryType().toString();
                String time =   Integer.toString(route.getTime()/60) + "min";
                String distance = Integer.toString(route.getDistance());

                List<String> values = Arrays.asList(id, category, time, distance);

                setTableDate(values, table);
            });

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();
        }
        catch (DocumentException e){
            System.out.println(e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPCell setCell(String name, boolean isHeader){
        PdfPCell cell;

        if(isHeader){
            cell = new PdfPCell(new Phrase(name, headFont));
        } else {
            cell = new PdfPCell(new Phrase(name));
        }

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        return cell;
    }

    private static void setTableDate(List<String> tableDate, PdfPTable table){
        tableDate.stream()
                .map(e -> setCell(e, false))
                .forEach(table::addCell);
    }

    private static PdfPTable initTable(List<String> headers) throws DocumentException {
        PdfPTable table = new PdfPTable(headers.size());
        table.setWidthPercentage(80);

        int [] widths = new int[headers.size()];
        Arrays.fill(widths,3);

        table.setWidths(widths);

        headers.stream()
                .map(e-> setCell(e, true))
                .forEach(table::addCell);
        return table;
    }
}
