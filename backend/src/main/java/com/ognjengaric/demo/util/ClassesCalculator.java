package com.ognjengaric.demo.util;

import com.ognjengaric.demo.enums.LicenceCategory;
import com.ognjengaric.demo.enums.TrafficIntensityType;
import org.javatuples.Pair;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Component
public class ClassesCalculator {

    private ClassesCalculator(){
        setUp();
    }

    private final List<Pair<int[], int[]>> nightSchedule = new ArrayList<Pair<int[], int[]>>() {{
        add(Pair.with(new int[]{1, 1, 16, 0}, new int[]{1, 15, 7, 30}));
        add(Pair.with(new int[]{1, 16, 16, 30}, new int[]{1, 31, 7, 0}));
        add(Pair.with(new int[]{2, 1, 17, 0}, new int[]{2, 15, 7, 0}));
        add(Pair.with(new int[]{2, 16, 17, 30}, new int[]{2, 29, 6, 30}));
        add(Pair.with(new int[]{3, 1, 17, 30}, new int[]{3, 15, 6, 0}));
        add(Pair.with(new int[]{3, 16, 18, 0}, new int[]{3, 31, 6, 0}));
        add(Pair.with(new int[]{4, 1, 19, 0}, new int[]{4, 15, 6, 0}));
        add(Pair.with(new int[]{4, 16, 19, 30}, new int[]{4, 30, 6, 0}));
        add(Pair.with(new int[]{5, 1, 20, 0}, new int[]{5, 15, 5, 30}));
        add(Pair.with(new int[]{5, 16, 20, 0}, new int[]{6, 15, 5, 0}));
        add(Pair.with(new int[]{6, 16, 20, 30}, new int[]{7, 31, 5, 0}));
        add(Pair.with(new int[]{8, 1, 20, 0}, new int[]{8, 15, 5, 30}));
        add(Pair.with(new int[]{8, 16, 19, 30}, new int[]{8, 31, 6, 0}));
        add(Pair.with(new int[]{9, 1, 19, 0}, new int[]{9, 15, 6, 0}));
        add(Pair.with(new int[]{9, 16, 18, 30}, new int[]{9, 30, 6, 30}));
        add(Pair.with(new int[]{10, 1, 18, 0}, new int[]{10, 31, 7, 0}));
        add(Pair.with(new int[]{11, 1, 16, 0}, new int[]{12, 15, 6, 30}));
        add(Pair.with(new int[]{11, 16, 16, 0}, new int[]{12, 15, 7, 0}));
        add(Pair.with(new int[]{12, 16, 16, 0}, new int[]{12, 31, 7, 30}));
    }};

    private Map<LicenceCategory, Integer> AM = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values())
            put(cat, 7);
    }};

    private Map<LicenceCategory, Integer> A1 = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values())
            if(cat.equals(LicenceCategory.AM))
                put(cat, 7);
            else
                put(cat, 20);
    }};

    private Map<LicenceCategory, Integer> A2 = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values())
            if(cat.equals(LicenceCategory.AM))
                put(cat, 14);
            else if (cat.equals(LicenceCategory.A1))
                put(cat, 7);
            else
                put(cat, 30);
    }};


    private Map<LicenceCategory, Integer> A = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values())
            if(cat.equals(LicenceCategory.AM))
                put(cat, 20);
            else if (cat.equals(LicenceCategory.A1))
                put(cat, 14);
            else if (cat.equals(LicenceCategory.A2))
                put(cat, 7);
            else
                put(cat, 40);
    }};

    private Map<LicenceCategory, Integer> B1 = new HashMap<LicenceCategory, Integer>() {{
        LicenceCategory[] x15 = {LicenceCategory.AM, LicenceCategory.A1, LicenceCategory.M};
        LicenceCategory[] x10 = {LicenceCategory.A2, LicenceCategory.A, LicenceCategory.F};
        for(LicenceCategory cat : LicenceCategory.values()){
            if(Arrays.asList(x15).contains(cat))
                put(cat, 15);
            else if(Arrays.asList(x10).contains(cat))
                put(cat, 20);
            else
                put(cat, 20);
        }
    }};


    private Map<LicenceCategory, Integer> B = new HashMap<LicenceCategory, Integer>() {{
        LicenceCategory[] x35 = {LicenceCategory.AM, LicenceCategory.A1};
        LicenceCategory[] x30 = {LicenceCategory.A2, LicenceCategory.A};
        LicenceCategory[] x20 = {LicenceCategory.B1, LicenceCategory.F};
        for(LicenceCategory cat : LicenceCategory.values()){
            if(Arrays.asList(x35).contains(cat))
                put(cat, 35);
            else if(Arrays.asList(x30).contains(cat))
                put(cat, 30);
            else if(Arrays.asList(x20).contains(cat))
                put(cat, 20);
            else
                put(cat, 40);
        }
    }};

    private Map<LicenceCategory, Integer> BE = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values()){
            put(cat, 7);
        }
    }};

    private Map<LicenceCategory, Integer> C1 = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values()){
            put(cat, 10);
        }
    }};

    private Map<LicenceCategory, Integer> C1E = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values()){
            put(cat, 7);
        }
    }};

    private Map<LicenceCategory, Integer> C = new HashMap<LicenceCategory, Integer>() {{
        LicenceCategory[] x7 = {LicenceCategory.C1, LicenceCategory.C1E};
        for(LicenceCategory cat : LicenceCategory.values()){
            if(Arrays.asList(x7).contains(cat))
                put(cat, 7);
            else
                put(cat, 15);
        }
    }};


    private Map<LicenceCategory, Integer> CE = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values()){
            put(cat, 7);
        }
    }};

    private Map<LicenceCategory, Integer> D1 = new HashMap<LicenceCategory, Integer>() {{
        LicenceCategory[] x7 = {LicenceCategory.C, LicenceCategory.CE};
        for(LicenceCategory cat : LicenceCategory.values()){
            if(Arrays.asList(x7).contains(cat))
                put(cat, 7);
            else
                put(cat, 15);
        }
    }};

    private Map<LicenceCategory, Integer> D1E = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values()){
            put(cat, 7);
        }
    }};

    private Map<LicenceCategory, Integer> D = new HashMap<LicenceCategory, Integer>() {{
        LicenceCategory[] x7 = {LicenceCategory.C, LicenceCategory.D1, LicenceCategory.D1E};
        for(LicenceCategory cat : LicenceCategory.values()){
            if(Arrays.asList(x7).contains(cat))
                put(cat, 7);
            else
                put(cat, 10);
        }
    }};

    private Map<LicenceCategory, Integer> DE = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values()){
            put(cat, 7);
        }
    }};

    private Map<LicenceCategory, Integer> F = new HashMap<LicenceCategory, Integer>() {{
        LicenceCategory[] x10 = {LicenceCategory.AM, LicenceCategory.A1, LicenceCategory.A2, LicenceCategory.A};
        for(LicenceCategory cat : LicenceCategory.values()){
            if(Arrays.asList(x10).contains(cat))
                put(cat, 10);
            else if(cat.equals(LicenceCategory.M))
                put(cat, 15);
            else if(cat.equals(LicenceCategory.F))
                put(cat, 20);
            else
                put(cat, 7);
        }
    }};

    private Map<LicenceCategory, Integer> M = new HashMap<LicenceCategory, Integer>() {{
        for(LicenceCategory cat : LicenceCategory.values()){
            put(cat, 3);
        }
    }};

    private Map<LicenceCategory, HashMap<LicenceCategory, Integer>> numClasses = new HashMap<>();

    private void setUp(){
        for(Field f : this.getClass().getDeclaredFields()){
            try {
                LicenceCategory l = LicenceCategory.valueOf(f.getName());
                numClasses.put(l, (HashMap<LicenceCategory, Integer>) f.get(this));
            } catch (Exception exception){
                System.out.println("Error");
            }
        }
    }

    public int getTotal(LicenceCategory current, List<LicenceCategory> owned){
        HashMap<LicenceCategory, Integer> map = numClasses.get(current);

        if(owned.isEmpty())
            return map.values().stream()
                    .max(Comparator.comparing(Integer::valueOf))
                    .get();
        else
            return owned.stream()
                    .map(map::get)
                    .min(Comparator.comparing(Integer::valueOf))
                    .get();

    }

    public int getDrivingRange(LicenceCategory current, List<LicenceCategory> owned){
        LicenceCategory[] x1 = {LicenceCategory.BE, LicenceCategory.C1E, LicenceCategory.CE, LicenceCategory.D1,
                LicenceCategory.D1E, LicenceCategory.DE, LicenceCategory.F, LicenceCategory.M};

        if(Arrays.asList(x1).contains(current))
            return 1;
        else if(getTotal(current, owned) < 10)
            return 1;
        else
            return 2;
    }

    public int getIntensity(TrafficIntensityType intensity, LicenceCategory current, List<LicenceCategory> owned){
        int total = getTotal(current, owned);

        if(total < 20)
            return 2;
        else if(intensity.equals(TrafficIntensityType.LOW))
            return 4;
        else
            return 6;
    }

    public int getRural(LicenceCategory current, List<LicenceCategory> owned){
        int total = getTotal(current, owned);

        if(total < 10)
            return 1;
        else
            return 2;
    }

    public int getWithLoad(LicenceCategory current){
        LicenceCategory[] withLoad = {LicenceCategory.BE, LicenceCategory.C1E, LicenceCategory.CE, LicenceCategory.C1,
                LicenceCategory.C, LicenceCategory.D1E, LicenceCategory.DE, LicenceCategory.F};

        if(Arrays.asList(withLoad).contains(current))
            return 3;
        else
            return 0;
    }

    public int getNight(LicenceCategory current, List<LicenceCategory> owned){
        int total = getTotal(current, owned);

        if(total < 10)
            return 1;
        else if(current.equals(LicenceCategory.M))
            return 0;
        else
            return 2;
    }

    public boolean isNight(DateTime start, DateTime end){
        Interval classInterval = new Interval(start, end);

        for(Pair<int[], int[]> el : nightSchedule) {
            DateTime s = new DateTime(start.getYear(), el.getValue0()[0], el.getValue0()[1], 0, 0, start.getZone());
            DateTime e = new DateTime(end.getYear(), el.getValue1()[0], el.getValue1()[1], 0, 0, end.getZone());
            Interval i = new Interval(s, e);

            if (i.contains(classInterval)) {
                DateTime s1 = new DateTime(start.getYear(), start.getMonthOfYear(), start.getDayOfMonth(), el.getValue0()[2], el.getValue0()[3]);
                DateTime e1 = new DateTime(end.getYear(), end.getMonthOfYear(), end.getDayOfMonth(), el.getValue1()[2], el.getValue1()[3]);

                s1.withZone(start.getZone());
                e1.withZone(end.getZone());

                if(s1.getDayOfMonth() == e1.getDayOfMonth())
                    e1 = e1.plusDays(1);

                Interval i1 = new Interval(s1, e1);

                if (i1.contains(classInterval))
                    return true;
            }

        };

        return false;
    }
}