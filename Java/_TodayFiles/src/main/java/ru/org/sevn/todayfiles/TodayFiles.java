/*
 * Copyright 2020 Veronica Anokhina.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.org.sevn.todayfiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

public class TodayFiles {

    public static String getNoExt(final String n, final String e) {
        return n.substring(0, n.length() - e.length()).replace("\\", "/");
    }
    
    public static String getLink(final String n) {
        //return n.replace(" ", "\\ ");
        return n.replace(" ", "%20").replace("\\", "/");
    }
    
    public static void writeIndex(final File dir, final File dates, Comparator<File> order) throws IOException {
        final File[] dirs = dir.listFiles(f -> 
                !f.getName().startsWith(".") 
                        && !f.getName().startsWith("_") 
                        && f.isDirectory()
                        && !f.equals(dates)
        );
        for (final File d : dirs) {
            writeIndex(d, dates, order);
        }
        final File[] files = dir.listFiles(f -> 
                !f.getName().startsWith(".") 
                        && !f.getName().startsWith("_") 
                        && !f.getName().equals("README.md")
                        && !f.getName().equals("index.md")
                        && !f.equals(dates)
        );
        
        Arrays.sort(files, order);
        final StringBuilder sb = new StringBuilder();
        for (final File f : files) {
            if (f.isDirectory()) {
                sb.append("\n")
                        .append("[").append(f.getName()).append("]")
                        .append("(").append(getLink(f.getName())).append("/index.md").append(")").append("  ")
                        ;
            } else if (f.getName().endsWith(".md")) {
                sb.append("\n")
                        .append("[").append(getNoExt(f.getName(), ".md")).append("]")
                        .append("(").append(getLink(f.getName())).append(")").append("  ")
                        ;
            }
        }
        final File idx = new File(dir, "index.md");
        //System.out.println(">>>>" + dir.getName()+":"+sb.toString());
        writeIt(idx.toPath(), sb.toString().getBytes(StandardCharsets.UTF_8));
    }
    
    public static void main(final String[] args) throws IOException {
        LocalDate mindate = LocalDate.of(2022, 12, 4);
        Long mintime = Date.from(mindate.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime() - 1;
        LocalDate date = LocalDate.now();
        
        final File dir = new File("../..");
        if (!dir.exists()) {
            throw new IllegalArgumentException("Dir not found " + dir.getPath());
        }
        final File dates = new File(dir, "dates");
        
        writeIndex(dir, dates, getComparator(1));
        
        for (int dn = 0; dn < 10; dn ++, date = date.minusDays(1)) {
            final File fileToday = new File(dates, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date)+".md");

            final Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
            final Long today = Date.from(instant).getTime();
            final Long tomorrow = Date.from(date.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant()).getTime();
            if (today < mintime ) {
                continue;
            }

            final StringBuilder sbFileToday = new StringBuilder();
            try (Stream<Path> walk = Files.walk(dir.toPath())) {

                final Stream<Path> result = walk
                        .filter(f -> !f.equals(dates.toPath()))
                        .filter(f -> !f.getParent().equals(dates.toPath()))
                        .map(f -> {
                            //System.out.println("FL>" + new Date (f.toFile().lastModified()) + ":" + new Date(today) + ":" + f.getFileName());
                            return f;})
                        .filter(f -> f.toFile().lastModified() > today && f.toFile().lastModified() <= tomorrow)
                        .filter(f -> !f.getFileName().toString().equals("README.md"))
                        .filter(f -> !f.getFileName().toString().equals("index.md"))
                        .filter(f -> f.getFileName().toString().endsWith(".md"))
                        .sorted(getComparatorPath(1));

                result.forEach(p -> {
                    sbFileToday.append("\n")
                            .append("[").append(getNoExt(dir.toPath().relativize(p).toString(), ".md")).append("]")
                            .append("(").append(getLink(dates.toPath().relativize(p).toString())).append(")").append("  ")///////
                            ;
                });
                //System.out.println(sbFileToday.toString());

                System.out.println(">>>>>>>>>>>" + sbFileToday.toString().trim().length() +":" +fileToday.toPath());
                if (sbFileToday.toString().trim().length() != 0) {
                    writeIt(fileToday.toPath(), sbFileToday.toString().getBytes(StandardCharsets.UTF_8));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        writeIndex(dates, dates, getComparator(-1));
    }
    private static Comparator<Path> getComparatorPath(int order) {
        return (f1, f2) -> {
            return order * f1.toString().compareTo(f2.toString());
        };
    }

    private static Comparator<File> getComparator(int order) {
        Comparator<Path> fileComparator = getComparatorPath(order);
        return (f1, f2) -> {
            return fileComparator.compare(f1.toPath(), f2.toPath());
        };
    }
    
    private static void writeIt(Path path, byte[] bytes) throws IOException {
        //System.out.println(">>>>" + path+":" + new String(bytes, StandardCharsets.UTF_8));
        Files.write(path, bytes);
    }
}
