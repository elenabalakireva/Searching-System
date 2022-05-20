import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class BooleanSearchEngine implements SearchEngine {
    private Map<String, List<PageEntry>> map;


    public BooleanSearchEngine(Map<String, List<PageEntry>> mapOfEngine) throws IOException {
        File dir = new File(Main.PATH);
        File[] arrFiles = dir.listFiles();
        List<File> listOfFiles = Arrays.asList(arrFiles);

        for (File f : listOfFiles) {//пройдемся по всем файлам
            var doc = new PdfDocument(new PdfReader(f));

            for (int i = 1; i <= doc.getNumberOfPages(); i++) {       //пройдемся по всем страницам
                String text = PdfTextExtractor.getTextFromPage(doc.getPage(i)); //получим текст с одной страницы
                var words = text.split("\\P{IsAlphabetic}+");   //вычленим слова из текста в массив
                Map<String, Integer> mapForCount = new HashMap<>();
                String word;
                for (int j = 0; j < words.length; j++) {
                    word = words[j].toLowerCase(Locale.ROOT);
                    if (word.isEmpty()) {
                        continue;
                    }
                    mapForCount.put(word, mapForCount.getOrDefault(word, 0) + 1);  //cчетчик количества каждого слова на странице
                    PageEntry pageEntry = new PageEntry(f.getName(), i, mapForCount.get(word));
                    if (mapOfEngine.containsKey(word)) {
                        mapOfEngine.get(word).add(pageEntry);

                    } else {
                        mapOfEngine.put(word, new ArrayList<>());    //в мапу кладем слово
                        mapOfEngine.get(word).add(pageEntry);
                    }

                }
            }
        }

        this.map = mapOfEngine;
    }


    @Override
    public List<PageEntry> search(String word) {
        List<PageEntry> list = map.get(word).stream()
                .sorted(Comparator.comparingInt(PageEntry::getCount).reversed())
                .distinct()
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public String toString() {
        return "BooleanSearchEngine{" +
                "map=" + map +
                '}';
    }


}

